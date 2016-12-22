package data.model;

import data.model.atomicData.DateTime;
import data.model.atomicData.Geolocalization;

public class ResultData {

    private Geolocalization geolocalization;
    private double resultant_value;
    private DateTime date_of_registration;

    public ResultData() {
    }

    public ResultData(Geolocalization geolocalization, double resultant_value, DateTime date_of_registration) {
	super();
	this.geolocalization = geolocalization;
	this.resultant_value = resultant_value;
	this.date_of_registration = date_of_registration;
    }

    public DateTime getDate_of_registration() {
	return date_of_registration;
    }

    public void setDate_of_registration(DateTime date_of_registration) {
	this.date_of_registration = date_of_registration;
    }

    @Override
    public String toString() {
	return "ResultData [geolocalization=" + geolocalization + ", resultant_value=" + resultant_value
		+ ", date_of_registration=" + date_of_registration + "]";
    }

    public Geolocalization getGeolocalization() {
	return geolocalization;
    }

    public void setGeolocalization(Geolocalization geolocalization) {
	this.geolocalization = geolocalization;
    }

    public double getResultant_value() {
	return resultant_value;
    }

    public void setResultant_value(double resultant_value) {
	this.resultant_value = resultant_value;
    }

}
