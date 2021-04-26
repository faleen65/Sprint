package com.cg.ima.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author Aditya
 *
 */
@Entity
@Table(name = "res_test")

public class Resource {
	@Id
	@SequenceGenerator(name = "res", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res")
	private int resId;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private String category; // Service/Product/Help
	@Column(name = "date_res")
	@NotNull
	private LocalDate date;
	@NotNull
	private String type; // Buy/Sell/Rent/Hire
	@NotNull
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empid")
	@NotNull
	private Employee emp;

	public Resource(Resource resource) {
		this.resId = resource.resId;
		this.title = resource.title;
		this.description = resource.description;
		this.category = resource.category;
		this.date = resource.date;
		this.type = resource.type;
		this.price = resource.price;
		this.emp = resource.emp;
	}

	@Override
	public String toString() {
		return "Resource [resId=" + resId + ", title=" + title + ", description=" + description + ", category="
				+ category + ", date=" + date + ", type=" + type + ", price=" + price + "Emp=" + emp + "]";
	}

	public Resource() {
		super();
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Resource(String title, String description, String category, LocalDate date, String type, double price,
			Employee emp) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.date = date;
		this.type = type;
		this.price = price;
		this.emp = emp;
	}

}
