package data.model.atomicData;

public class Gravity {

    @Override
    public String toString() {
	return "Gravity [gravity_x=" + gravity_x + ", gravity_y=" + gravity_y + ", gravity_z=" + gravity_z + "]";
    }

    double gravity_x;
    double gravity_y;
    double gravity_z;

    public Gravity() {
    }

    public Gravity(double gravity_x, double gravity_y, double gravity_z) {
	this.gravity_x = gravity_x;
	this.gravity_y = gravity_y;
	this.gravity_z = gravity_z;
    }

    public double getGravity_x() {
	return gravity_x;
    }

    public void setGravity_x(double gravity_x) {
	this.gravity_x = gravity_x;
    }

    public double getGravity_y() {
	return gravity_y;
    }

    public void setGravity_y(double gravity_y) {
	this.gravity_y = gravity_y;
    }

    public double getGravity_z() {
	return gravity_z;
    }

    public void setGravity_z(double gravity_z) {
	this.gravity_z = gravity_z;
    }

}
