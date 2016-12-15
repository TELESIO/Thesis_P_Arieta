package data.model.atomicData;

public class Geolocalization {
    @Override
    public String toString() {
	return "Geolocalization [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    double latitude;
    double longitude;

    public Geolocalization(double latitude, double longitude) {
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public Geolocalization() {
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

}
