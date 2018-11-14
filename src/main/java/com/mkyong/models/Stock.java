package com.mkyong.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_ID")
	private int id;
	
	@Column(name = "STOCK_CODE", unique = true, nullable = false)
	private String code;

	@Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
	private StockDetail detail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
	private List<StockDailyRecord> dailyRecords;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable( 
//			joinColumns = {@JoinColumn(name = "STOCK_ID")},
//			inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")}
//			)

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "stock_category", joinColumns = { 
			@JoinColumn(name = "STOCK_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false) })
	private Set<Category> categories = new HashSet<>();

	public Stock(int Id, String Code, String Name, StockDetail Detail,
			List<StockDailyRecord> DailyRecords) {
		super();
		this.id = Id;
		this.code = Code;
		this.name = Name;
		this.detail = Detail;
		this.dailyRecords = DailyRecords;
	}
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stock(int Id, String Code, String Name) {
		super();
		this.id = Id;
		this.code = Code;
		this.name = Name;
	}
	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", name=" + name + ", detail=" + detail + ", dailyRecords="
				+ dailyRecords + ", categories=" + categories + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StockDetail getDetail() {
		return detail;
	}
	public void setDetail(StockDetail detail) {
		this.detail = detail;
	}
	public List<StockDailyRecord> getDailyRecords() {
		return dailyRecords;
	}
	public void setDailyRecords(List<StockDailyRecord> dailyRecords) {
		this.dailyRecords = dailyRecords;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
