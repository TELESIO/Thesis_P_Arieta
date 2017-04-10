package data;

public class Data {
	
	private Double displacement;
	private Double latitude;
	private Double longitude;
	
	@Override
	public String toString() {
		return "Data [displacement=" + displacement + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}


	
	    public Data(Double displacement, Double latitude, Double longitude) {
	    this.displacement = displacement;
	    this.latitude = latitude;
		this.longitude = longitude;
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


		public Double getDisplacement() {
			return displacement;
		}


		public void setDisplacement(Double displacement) {
			this.displacement = displacement;
		}

		
	    
	    
	    
}
