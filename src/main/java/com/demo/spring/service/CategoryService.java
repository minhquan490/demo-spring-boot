package com.demo.spring.service;

import com.demo.spring.model.Category;

public interface CategoryService {

	void save(Category category);

	void edit(Category category);

	boolean delete(Category category);

	Category get(long id);

	Category get(String name);
}
