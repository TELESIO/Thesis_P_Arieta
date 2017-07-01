package model;

import java.util.Date;

public class PointOnMap {
	
	private double value;
	private String color;
	private Date date;
	private double latitude;
	private double longitude;
	
	
	public PointOnMap() {
		super();
	}
	
	public PointOnMap(double value, String color, Date date, double latitude, double longitude) {
		super();
		this.value = value;
		this.color = color;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double d) {
		this.value = d;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PointOnMap [value=" + value + ", color=" + color + ", date=" + date + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	
	
	
	
}
