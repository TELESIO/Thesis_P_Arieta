package model;

import java.util.Date;

public class PointOnMap {
	protected Double displacement;
	protected Double latitude;
	protected Double longitude;
	protected Date date;
	String color;

	public PointOnMap() {
		super();
	}

	public PointOnMap(Double displacement, Double latitude, Double longitude, Date date, String color) {
		this.displacement = displacement;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.color = color;
	}
	
	public boolean equals(PointOnMap p) {
		if(this.latitude == p.latitude && this.longitude == p.longitude && this.date == p.date && this.displacement == p.displacement && this.color == p.color)
			return true;
		return false;
	}	
	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	

	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RawPoint [displacement=" + displacement + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", date=" + date + "]";
	}

	

}
