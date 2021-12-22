package com.demo.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "category")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private long id;

	@Column(name = "name_category", nullable = false)
	private String nameCategory;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "clip_category", joinColumns = { @JoinColumn(name = "clip_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Set<Clip> clips = new HashSet<Clip>();

	public Category() {
		super();
	}

	public Category(long id, String nameCategory, Set<Clip> clips) {
		super();
		this.id = id;
		this.nameCategory = nameCategory;
		this.clips = clips;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Set<Clip> getClips() {
		return clips;
	}

	public void setClips(Set<Clip> clips) {
		this.clips = clips;
	}
}