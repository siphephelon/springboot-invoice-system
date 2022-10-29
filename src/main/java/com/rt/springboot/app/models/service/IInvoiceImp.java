package com.rt.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.rt.springboot.app.models.dao.IInvoiceDao;
import com.rt.springboot.app.models.entity.Client;
import com.rt.springboot.app.models.entity.Invoice;

public class IInvoiceImp implements IInvoiceService{

	@Autowired
	private IInvoiceDao invoiceDao;
	
	/*----- Method List -----*/
	@Override
	@Transactional(readOnly = true)
	public List<Invoice> findAll() {
		return (List<Invoice>) invoiceDao.findAll();
	}
		
	/*----- Paginator -----*/
	@Transactional(readOnly = true)
	public Page<Invoice> findAll1(Pageable pageable) {
		return invoiceDao.findAll(pageable);
	}
	

	@Override
	public Page<Invoice> findAll(Pageable pageRequest) {
		// TODO Auto-generated method stub
		//return Page<Invoice> findAll(pageRequest);
		return null;
		
	}
	

}
