package com.demo.spring.service;

import com.demo.spring.model.Clip;

public interface ClipService {

	void save(Clip clip);

	boolean delete(Clip clip);

	void edit(Clip clip);

	Clip get(long id);
}
