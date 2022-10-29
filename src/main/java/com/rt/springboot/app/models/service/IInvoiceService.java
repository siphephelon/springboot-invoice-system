package com.rt.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rt.springboot.app.models.entity.Invoice;

public interface IInvoiceService {

	Page<Invoice> findAll(Pageable pageRequest);

	List<Invoice> findAll();

	Page<Invoice> findAll1(Pageable pageable);

}
