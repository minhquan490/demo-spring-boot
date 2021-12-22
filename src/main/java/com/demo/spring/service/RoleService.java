package com.demo.spring.service;

import org.hibernate.HibernateException;

import com.demo.spring.model.Role;

public interface RoleService {

	void save(Role role) throws HibernateException;

	void edit(Role role) throws HibernateException;

	boolean delete(Role role) throws HibernateException;

	Role get(long userId) throws HibernateException;
}
