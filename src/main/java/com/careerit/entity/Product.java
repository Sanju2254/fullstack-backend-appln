package com.careerit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pid;
	private String pname;
	private double pcost;
	private int pqty;
	private String pimage;
	private String category;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long id, String pid, String pname, double pcost, int pqty, String pimage, String category) {
		super();
		this.id = id;
		this.pid = pid;
		this.pname = pname;
		this.pcost = pcost;
		this.pqty = pqty;
		this.pimage = pimage;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPcost() {
		return pcost;
	}
	public void setPcost(double pcost) {
		this.pcost = pcost;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", pid=" + pid + ", pname=" + pname + ", pcost=" + pcost + ", pqty=" + pqty
				+ ", pimage=" + pimage + ", category=" + category + "]";
	}
	
}
