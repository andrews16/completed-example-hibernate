package com.mkyong.models;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="stock_details")
public class StockDetail {
	
	@GenericGenerator(name = "generator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "stock")
	)
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "STOCK_ID")
	private int stockId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Stock stock;
	@Column(name = "COM_NAME", nullable = false, length = 100)
	private String compName;
	@Column(name = "COMP_DESC", nullable = false, length = 100)
	private String compDesc;
	@Column(name = "REMARK", nullable = false)
	private String remark;
	@Temporal(TemporalType.DATE)
	@Column(name = "LISTED_DATE", nullable = false, length = 10)
	private Date listedDate;
	public StockDetail() {
		super();
		
	}
	public StockDetail(int stockId, Stock stock, String compName, String compDesc, String remark, Date listedDate) {
		super();
		this.stockId = stockId;
		this.stock = stock;
		this.compName = compName;
		this.compDesc = compDesc;
		this.remark = remark;
		this.listedDate = listedDate;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getListedDate() {
		return listedDate;
	}
	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}
	@Override
	public String toString() {
		return "StockDetail [stockId=" + stockId + ", stock=" + stock + ", compName=" + compName + ", compDesc="
				+ compDesc + ", remark=" + remark + ", listedDate=" + listedDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compDesc == null) ? 0 : compDesc.hashCode());
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((listedDate == null) ? 0 : listedDate.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + stockId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDetail other = (StockDetail) obj;
		if (compDesc == null) {
			if (other.compDesc != null)
				return false;
		} else if (!compDesc.equals(other.compDesc))
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (listedDate == null) {
			if (other.listedDate != null)
				return false;
		} else if (!listedDate.equals(other.listedDate))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (stockId != other.stockId)
			return false;
		return true;
	}
	
}
