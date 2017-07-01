package handler.mt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.NearPoint;
import model.PointOnMap;
import persistence.dao.PointsOnMapManager;
import utils.Barrier;
import utils.color.ColorGenerator;

public class FileHandlerWorker extends Thread {
	private Barrier barrier;
	private ColorGenerator color_generator;
	private PointsOnMapManager databaseType;
	private ArrayList<PointOnMap> to_insert;
	private ArrayList<PointOnMap> to_update;
	private ArrayList<PointOnMap> point_to_handle;

	public ArrayList<PointOnMap> getTo_insert() {
		return to_insert;
	}

	public ArrayList<PointOnMap> getTo_update() {
		return to_update;
	}

	public FileHandlerWorker(ArrayList<PointOnMap> point_to_handle, Barrier barrier, ColorGenerator color_generator,
			PointsOnMapManager databaseType) {
		this.point_to_handle = point_to_handle;
		this.barrier = barrier;
		this.color_generator = color_generator;
		this.databaseType = databaseType;
		this.to_insert = new ArrayList<>();
		this.to_update = new ArrayList<>();

	}

	public void checkPointsIntersectionWithExistingPoints() {
		for (PointOnMap p : point_to_handle) {
			PointOnMap existing_Point = databaseType.getPoint(p.getLatitude(), p.getLongitude());
			if (existing_Point == null) {
				List<PointOnMap> points_founded = databaseType.findPointsIntoSegment(p.getLatitude(), p.getLongitude());
				if (points_founded.size() > 0) {
					for (PointOnMap found : points_founded) {
						double newValue = (p.getDisplacement() + found.getDisplacement()) / 2;
						String color = color_generator.getColor(newValue);
						to_update.add(new PointOnMap(newValue, found.getLatitude(), found.getLongitude(), p.getDate(),
								color));
					}
				} else {
					to_insert.add(p);
				}
			} else {
				double newValue = (existing_Point.getDisplacement() + p.getDisplacement()) / 2;
				String color = color_generator.getColor(newValue);
				to_update.add(new PointOnMap(newValue, existing_Point.getLatitude(), existing_Point.getLongitude(),
						p.getDate(), color));

			}
		}
	}

	@Override
	public void run() {
		checkPointsIntersectionWithExistingPoints();
		
		for (PointOnMap insert : getTo_insert())
			databaseType.insertPointOnMap(insert);
		for (PointOnMap update : getTo_update()) 
			databaseType.updatePoint(update.getLatitude(), update.getLongitude(), update.getDate(),
					update.getDisplacement(), update.getColor());
		barrier.done();
	}
}
