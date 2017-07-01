package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PointOnMap;
import persistence.dao.PointsOnMapManager;

public class PointOnMapJDBC implements PointsOnMapManager {

	String table;
	public PointOnMapJDBC(String table) {
		this.table = table;
	}

	
	@Override
	public PointOnMap getPoint(double latitude, double longitude) {
		PointOnMap point = null;
		Connection connection = null;
		try {
			connection = DBPoolConnection.getConn();
			String query = "SELECT * "
					+ "FROM " + table + " " +
					 "WHERE Latitude="+latitude+" and Longitude=" +longitude ;
			PreparedStatement pr_stm = connection.prepareStatement(query);
			ResultSet result_set = pr_stm.executeQuery();
	
			while (result_set.next()) {
				point = new PointOnMap();
				point.setLatitude(result_set.getDouble("Latitude"));
				point.setLongitude(result_set.getDouble("Longitude"));
				point.setColor(result_set.getString("Color"));
				point.setDisplacement(result_set.getDouble("Value"));
				point.setDate(result_set.getDate("Date"));
			}
		} catch (SQLException e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return point;
	}


	@Override
	public void insertPointsOnMap(ArrayList<PointOnMap> points) {
		Connection connection = null;
		try {
			connection = DBPoolConnection.getConn();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		String insert = "INSERT into " + table + " " + "(Latitude,Longitude,Color,Date,Value)" + " values (?,?,?,?,?)";
		PreparedStatement statement;
	
		try {
			statement = connection.prepareStatement(insert);
			for (PointOnMap p : points) {
				statement = connection.prepareStatement(insert);
				statement.setDouble(1, p.getLatitude());
				statement.setDouble(2, p.getLongitude());
				statement.setString(3, p.getColor());
				statement.setDate(4, (Date) p.getDate());
				statement.setDouble(5, p.getDisplacement());
				if (statement.executeUpdate() == 0)
					System.out.println("andato male");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}


	@Override
	public List<PointOnMap> findPointsIntoSegment(double latitude, double longitude) {
		List<PointOnMap> points = new ArrayList<PointOnMap>();
		
		Connection connection = null;
		try {
			connection = DBPoolConnection.getConn();
			String query = "SELECT*, ((6371 * 1000 ) * acos (cos ( radians(" + latitude
					+ ") )* cos( radians( Latitude ) )* cos( radians( Longitude ) - radians(" + longitude
					+ ") )+ sin ( radians(" + latitude
					+ ") ) * sin( radians( Latitude ) ))  ) AS distance FROM " + table + " HAVING distance <= 9 ORDER BY distance";
	
			PreparedStatement pr_stm = connection.prepareStatement(query);
			ResultSet result_set = pr_stm.executeQuery();
	
			while (result_set.next()) {
				PointOnMap point = new PointOnMap();
				point.setLatitude(result_set.getDouble("Latitude"));
				point.setLongitude(result_set.getDouble("Longitude"));
				point.setColor(result_set.getString("Color"));
				point.setDisplacement(result_set.getDouble("Value"));
				point.setDate(result_set.getDate("Date"));
				points.add(point);
	
			}
		} catch (SQLException e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return points;
	}


	@Override
	public List<PointOnMap> getPointsOnMap() {

		List<PointOnMap> points = new ArrayList<PointOnMap>();
		Connection connection = null;
		try {
			connection = DBPoolConnection.getConn();
			String query = "select * from " + table;
			PreparedStatement pr_stm = connection.prepareStatement(query);
			ResultSet result_set = pr_stm.executeQuery();

			while (result_set.next()) {

				PointOnMap point = new PointOnMap();
				point.setLatitude(result_set.getDouble("Latitude"));
				point.setLongitude(result_set.getDouble("Longitude"));
				point.setColor(result_set.getString("Color"));
				point.setDisplacement(result_set.getDouble("Value"));
				point.setDate(result_set.getDate("Date"));
				points.add(point);

			}
		} catch (SQLException e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return points;
	}


	@Override
	public void updatePoint(double latitude, double longitude, java.util.Date data, double value, String color) {
		
		try {
			Connection conn = DBPoolConnection.getConn();
			String query ="UPDATE " + table + " set Value=?, Color=?, Date=? where Latitude=? and Longitude=?";
			PreparedStatement stmnt = conn.prepareStatement(query);
			stmnt.setDouble(1, value);
			stmnt.setString(2, color);
			stmnt.setDate(3, (Date) data);
			stmnt.setDouble(4, latitude);
			stmnt.setDouble(5, longitude);	
			stmnt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
		}
		
		
		
	}


	@Override
	public void insertPointOnMap(PointOnMap point) {
		
		try {
			Connection connection = DBPoolConnection.getConn();
			String query = "INSERT into "+ table +" (Latitude,Longitude,Color,Date,Value)" + " values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
		
			statement.setDouble(1, point.getLatitude());
			statement.setDouble(2, point.getLongitude());
			statement.setString(3, point.getColor());
			statement.setDate(4, (Date) point.getDate());
			statement.setDouble(5, point.getDisplacement());	
			
			
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
		}
		
		
	}


	@Override
	public int getNumberOfPoints() {
			System.out.println("sono qua dentro");
		int to_return = 0;
		try {
			Connection conn = DBPoolConnection.getConn();
			String query = "SELECT count(*) from " + table;
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next())
				to_return = result.getInt(1);
		
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		System.out.println(to_return);
		return to_return;
		
	}

}
