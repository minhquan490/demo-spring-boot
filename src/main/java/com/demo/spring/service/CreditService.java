package com.demo.spring.service;

import com.demo.spring.model.Credit;

public interface CreditService {

	void save(Credit credit);

	void edit(Credit credit);

	boolean delete(Credit credit);
}
