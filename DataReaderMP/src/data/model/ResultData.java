package data.model;

import data.model.atomicData.Geolocalization;

public class ResultData {
    private Geolocalization geolocalization;
    private double resultant_value;

    public ResultData() {
    }

    public ResultData(Geolocalization geolocalization, double resultant_value) {
	this.geolocalization = geolocalization;
	this.resultant_value = resultant_value;
    }

    @Override
    public String toString() {
	return "ResultData [geolocalization=" + geolocalization + ", resultant_value=" + resultant_value + "]";
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
