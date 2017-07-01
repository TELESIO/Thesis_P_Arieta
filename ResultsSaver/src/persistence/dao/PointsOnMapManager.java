package persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.PointOnMap;

public interface PointsOnMapManager {
	public List<PointOnMap> getPointsOnMap();

	public PointOnMap getPoint(double latitude, double longitude);

	public void insertPointsOnMap(ArrayList<PointOnMap> points);

	public void insertPointOnMap(PointOnMap point);
	
	public List<PointOnMap> findPointsIntoSegment(double latitude, double longitude);
	
	public int getNumberOfPoints();

	public void updatePoint(double latitude, double longitude, Date data, double value, String color);
}
