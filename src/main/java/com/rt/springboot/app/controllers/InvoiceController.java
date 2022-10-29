package com.rt.springboot.app.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rt.springboot.app.models.entity.Client;
import com.rt.springboot.app.models.entity.Invoice;
import com.rt.springboot.app.models.entity.ItemInvoice;
import com.rt.springboot.app.models.entity.Product;
import com.rt.springboot.app.models.service.IClientService;
import com.rt.springboot.app.models.service.IInvoiceService;
import com.rt.springboot.app.util.paginator.PageRender;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {


	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IClientService clientService;

	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private MessageSource messageSource;

	private final Logger log = LoggerFactory.getLogger(getClass());

	
	/* ----- View Invoice[id] ----- */
	@GetMapping("/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash, Locale locale) {

		Invoice invoice = clientService.fetchInvoiceByIdWithClientWithInvoiceItemWithProduct(id);
		
		if (invoice == null) {
			flash.addAttribute("error", messageSource.getMessage("text.factura.flash.db.error", null, locale));
			return "redirect:/list";
		}

		model.addAttribute("invoice", invoice);
		model.addAttribute("title", String.format(messageSource.getMessage("text.factura.ver.titulo", null, locale), invoice.getDescription()));

		return "invoice/view";
	}
	
	/* ----- List Invoices ----- */
	@Secured("ROLE_USER")
	@GetMapping("/listi")
	public String listinvoice(@RequestParam(name = "page", defaultValue = "0")int page, Model model,
			Authentication authentication, HttpServletRequest request, Locale locale) {
		// 2 Ways of seeing Roles
				// 1st Way
		if (authentication != null) {
			logger.info("Hello authenticated user, Your username is: " + authentication.getName());
		}

		// 2nd Way(static)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			logger.info("Using Static form 'SecurityContextHolder.getContext().getAuthentication();': User authenticated, username: "
					+ auth.getName());
		}

		// 3 Ways of assigning Roles
		// 1st Way
		if (hasRole("ROLE_ADMIN")) {
			logger.info("Hello " + auth.getName() + " you have access");
		} else {
			logger.info("Hello " + auth.getName() + " No user access");
		}

		// 2nd Way
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		if (securityContext.isUserInRole("ADMIN")) {
			logger.info("Shape using SecurityContextHolderAwareRequestWrapper: Hello " + auth.getName() + " You have access");
		} else {
			logger.info("Shape using SecurityContextHolderAwareRequestWrapper: Hello " + auth.getName()	+ " No user access");
		}
		
		// 3rd Way
		if (request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Forma usando HttpServletRequest: Hola " + auth.getName() + " tienes acceso");
		} else {
			logger.info("Forma usando HttpServletRequest: Hola " + auth.getName() + " NO tienes acceso");
		}

		Pageable pageRequest = PageRequest.of(page, 10);
		//Page<Client> clients = clientService.findAll(pageRequest);
		Page<Invoice> invoices = invoiceService.findAll(pageRequest);
		//PageRender<Client> pageRender = new PageRender<>("/list", clients);
		PageRender<Invoice> pageRender = new PageRender<>("/list", invoices);

				model.addAttribute("title", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
				//model.addAttribute("clients", clients);
				model.addAttribute("invoices", invoices);
				model.addAttribute("page", pageRender);
		
		return "/listinvoice";
	}

	/* ----- Create Invoice for Client[id] ----- */
	@GetMapping("/form/{clientId}")
	public String create(@PathVariable(value = "clientId") Long clientId, Map<String, Object> model,
			RedirectAttributes flash, Locale locale) {

		Client client = clientService.findOne(clientId);
		
		if (client == null) {
			flash.addAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
			return "redirect:/list";
		}

		Invoice invoice = new Invoice();
		invoice.setClient(client);

		model.put("invoice", invoice);
		model.put("title", messageSource.getMessage("text.factura.form.titulo", null, locale));

		return "invoice/form";
	}

	/* ----- Autocomplete for Finding Products (autocomplete-products.js)----- */
	@GetMapping(value = "/load-products/{term}", produces = { "application/json" })
	public @ResponseBody List<Product> loadProducts(@PathVariable String term) {
		return clientService.findByName(term);
	}

	/* ----- Save Invoice ----- */
	@PostMapping("/form")
	public String save(@Valid Invoice invoice, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "amount[]", required = false) Integer[] amount, RedirectAttributes flash,
			SessionStatus status, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("title", messageSource.getMessage("text.factura.form.titulo", null, locale));
			return "invoice/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("title", messageSource.getMessage("text.factura.form.titulo", null, locale));
			model.addAttribute("error", messageSource.getMessage("text.factura.flash.lineas.error", null, locale));
			return "invoice/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Product product = clientService.findProductById(itemId[i]);

			ItemInvoice line = new ItemInvoice();
			line.setAmount(amount[i]);
			line.setProduct(product);
			invoice.addItemInvoice(line);

			log.info("ID: " + itemId[i].toString() + ", amount: " + amount[i].toString());
		}

		clientService.saveInvoice(invoice);
		status.setComplete();

		flash.addFlashAttribute("success", messageSource.getMessage("text.factura.flash.crear.success", null, locale));

		return "redirect:/view/" + invoice.getClient().getId();
	}
	
	/* ----- Delete Invoice ----- */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {
		Invoice invoice = clientService.findInvoiceById(id);

		if (invoice != null) {
			clientService.deleteInvoice(id);
			flash.addAttribute("success", messageSource.getMessage("text.factura.flash.eliminar.success", null, locale));
			return "redirect:/view/" + invoice.getClient().getId();
		}

		flash.addFlashAttribute("error", messageSource.getMessage("text.factura.flash.db.error", null, locale));
		return "redirect:/list/";
	}
	
	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) { return false; }
		Authentication auth = context.getAuthentication();

		if (auth == null) { return false; }
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		// contains(GrantedAuthority) returns true or false if has the collection element or not
		return authorities.contains(new SimpleGrantedAuthority(role));

	}

}
