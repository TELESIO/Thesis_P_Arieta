package handler.mt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.NearPoint;
import model.PointOnMap;
import utils.color.ColorGenerator;

public class NearestPointHandler {
	public static final double R = 6371 * 1000; // in metro
	Set<PointOnMap> point_to_remove;
	List<NearPoint> point_to_adjust;
	ArrayList<PointOnMap> to_insert;
	ColorGenerator color_generator;

	public NearestPointHandler(ArrayList<PointOnMap> to_insert, ColorGenerator color_generator) {
		this.to_insert = to_insert;
		this.color_generator = color_generator;
		point_to_remove = new HashSet<PointOnMap>();
		point_to_adjust = new ArrayList<NearPoint>();
	}

	private static double haversine(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}

	private void findNearestPointsIntoDataSetOfFile() {
		for (int i = 0; i < to_insert.size(); i++) {
			PointOnMap p = to_insert.get(i);
			for (int y = i + 1; y < to_insert.size(); y++) {
				PointOnMap p2 = to_insert.get(y);

				double distance = haversine(p.getLatitude(), p.getLongitude(), p2.getLatitude(), p2.getLongitude());
				if (distance <= 5) {
					point_to_remove.add(p);
					point_to_remove.add(p2);
					point_to_adjust.add(new NearPoint(p, p2));

				}
			}
		}
	}

	private ArrayList<PointOnMap> handleNearestPointsIntoDataSetOfFile() {
		ArrayList<PointOnMap> to_insert = new ArrayList<PointOnMap>();
		for (NearPoint p : point_to_adjust) {
			PointOnMap p1 = p.getPoint1();
			PointOnMap p2 = p.getPoint2();

			double val = 0;
			if(p1.getDisplacement() > 0 && p2.getDisplacement()<0 || p2.getDisplacement()>0 && p1.getDisplacement()<0)
				val = (Math.abs(p1.getDisplacement()) + Math.abs(p2.getDisplacement()))/2;
			else	
			 val = (p1.getDisplacement() + p2.getDisplacement()) / 2;
			
			String colors = color_generator.getColor(val);
			double latitude = (p1.getLatitude() + p2.getLatitude()) / 2;
			double longitude = (p1.getLongitude() + p2.getLongitude()) / 2;
			to_insert.add(new PointOnMap(val, latitude, longitude, p1.getDate(), colors));
		}
		
		return to_insert;
	}

	public ArrayList<PointOnMap> handle() {
		// trovo i punti troppo vicini tra di loro.
		findNearestPointsIntoDataSetOfFile();
		// se vi sono punti troppo vicini, vengono rimossi entrambi
		to_insert.removeAll(point_to_remove);
		to_insert.addAll(handleNearestPointsIntoDataSetOfFile());
		return to_insert;
	}

}
