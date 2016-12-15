package data.model.atomicData;

public class Orientation {

    double azimuth;
    double pitch;
    double roll;

    public Orientation() {
    }

    public Orientation(double azimuth, double pitch, double roll) {
	this.azimuth = azimuth;
	this.pitch = pitch;
	this.roll = roll;
    }

    @Override
    public String toString() {
	return "Orientation [azimuth=" + azimuth + ", pitch=" + pitch + ", roll=" + roll + "]";
    }

    public double getAzimuth() {
	return azimuth;
    }

    public void setAzimuth(double azimuth) {
	this.azimuth = azimuth;
    }

    public double getRoll() {
	return roll;
    }

    public void setRoll(double roll) {
	this.roll = roll;
    }

    public double getPitch() {
	return pitch;
    }

    public void setPitch(double pitch) {
	this.pitch = pitch;
    }
}
