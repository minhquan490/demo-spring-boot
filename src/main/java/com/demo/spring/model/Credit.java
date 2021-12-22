package com.demo.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

@SuppressWarnings("serial")
@Entity
@Table(name = "credit")
public class Credit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_id")
	private long id;

	@Column(name = "credit_number", nullable = false)
	private String creditNumber;

	@Column(name = "pass_code")
	private String passCode;

	public Credit() {
		super();
	}

	public Credit(long id, String creditNumber, String passCode) {
		super();
		this.id = id;
		this.creditNumber = creditNumber;
		this.passCode = sha256(passCode);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = sha256(passCode);
	}

	private String sha256(String password) {
		String sha256 = "";
		if (password.length() < 64 && password.length() > 4) {
			sha256 = DigestUtils.sha256Hex(password);
		} else {
			password = sha256;
		}
		return sha256;
	}
}
