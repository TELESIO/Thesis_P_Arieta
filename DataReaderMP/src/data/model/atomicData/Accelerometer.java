package data.model.atomicData;

public class Accelerometer {

    @Override
    public String toString() {
	return "Accelerometer [acceleration_x=" + acceleration_x + ", acceleration_y=" + acceleration_y
		+ ", acceleration_z=" + acceleration_z + ", resultant_acceleration=" + resultant_acceleration + "]";
    }

    double acceleration_x;
    double acceleration_y;
    double acceleration_z;
    double resultant_acceleration;

    public Accelerometer() {
    }

    public Accelerometer(double acceleration_x, double acceleration_y, double acceleration_z) {
	this.acceleration_x = acceleration_x;
	this.acceleration_y = acceleration_y;
	this.acceleration_z = acceleration_z;

	this.resultant_acceleration = Math
		.sqrt(Math.pow(acceleration_x, 2) + Math.pow(acceleration_y, 2) + Math.pow(acceleration_z, 2));

    }

    public double getAcceleration_x() {
	return acceleration_x;
    }

    public void setAcceleration_x(double acceleration_x) {
	this.acceleration_x = acceleration_x;
	this.setResultant_acceleration();
    }

    public double getAcceleration_y() {
	return acceleration_y;
    }

    public void setAcceleration_y(double acceleration_y) {
	this.acceleration_y = acceleration_y;
	this.setResultant_acceleration();
    }

    public double getAcceleration_z() {
	return acceleration_z;
    }

    public void setAcceleration_z(double acceleration_z) {
	this.acceleration_z = acceleration_z;
	this.setResultant_acceleration();
    }

    public double getResultant_acceleration() {
	return resultant_acceleration;
    }

    public void setResultant_acceleration() {
	this.resultant_acceleration = Math
		.sqrt(Math.pow(acceleration_x, 2) + Math.pow(acceleration_y, 2) + Math.pow(acceleration_z, 2));
    }

}
