package com.mkyong.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock_daily_records")
public class StockDailyRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECORD_ID")
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	private Stock stock;
	
	@Column(name = "PRICE_OPEN", precision = 6)
	private float priceOpen;
	@Column(name = "PRICE_CLOSE", precision = 6)
	private float priceClose;
	@Column(name = "PRICE_CHANGE", precision = 6)
	private float priceChange;
	@Column(name = "VOLUME")
	private long volume;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", nullable = true, unique = false)
	private Date date;
	public StockDailyRecord() {
		super();
	}
	public StockDailyRecord(int recordId, Stock stock, float priceOpen, float priceClose, float priceChange,
			Long volume, Date date) {
		super();
		this.recordId = recordId;
		this.stock = stock;
		this.priceOpen = priceOpen;
		this.priceClose = priceClose;
		this.priceChange = priceChange;
		this.volume = volume;
		this.date = date;
	}
	@Override
	public String toString() {
		return "StockDailyRecord [recordId=" + recordId + ", stock=" + stock + ", priceOpen=" + priceOpen
				+ ", priceClose=" + priceClose + ", priceChange=" + priceChange + ", volume=" + volume + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(priceChange);
		result = prime * result + Float.floatToIntBits(priceClose);
		result = prime * result + Float.floatToIntBits(priceOpen);
		result = prime * result + recordId;
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + (int) (volume ^ (volume >>> 32));
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
		StockDailyRecord other = (StockDailyRecord) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(priceChange) != Float.floatToIntBits(other.priceChange))
			return false;
		if (Float.floatToIntBits(priceClose) != Float.floatToIntBits(other.priceClose))
			return false;
		if (Float.floatToIntBits(priceOpen) != Float.floatToIntBits(other.priceOpen))
			return false;
		if (recordId != other.recordId)
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public float getPriceOpen() {
		return priceOpen;
	}
	public void setPriceOpen(float priceOpen) {
		this.priceOpen = priceOpen;
	}
	public float getPriceClose() {
		return priceClose;
	}
	public void setPriceClose(float priceClose) {
		this.priceClose = priceClose;
	}
	public float getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(float priceChange) {
		this.priceChange = priceChange;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
