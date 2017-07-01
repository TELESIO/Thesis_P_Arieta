package model;

public class NearPoint {

	private PointOnMap point1;
	private PointOnMap point2;
	public NearPoint(PointOnMap point1, PointOnMap point2) {
		super();
		this.point1 = point1;
		this.point2 = point2;
	}
	public PointOnMap getPoint1() {
		return point1;
	}
	public void setPoint1(PointOnMap point1) {
		this.point1 = point1;
	}
	public PointOnMap getPoint2() {
		return point2;
	}
	public void setPoint2(PointOnMap point2) {
		this.point2 = point2;
	}
	
	
	
}
