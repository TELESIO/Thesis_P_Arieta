package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PointOnMap;
import persistence.dao.DAOPointOnMap;

public class PointOnMapDAO_JDBC implements DAOPointOnMap {

	
	
	private Db_connection db_connection;
	
	public PointOnMapDAO_JDBC(final Db_connection db_connection) {
		this.db_connection = db_connection;
	}
	
	@Override
	public List<PointOnMap> getPointOnMap() {
	
		
		List<PointOnMap> points = new ArrayList<PointOnMap>();
		Connection connection = db_connection.getConnection();
		
		
		try {
				String query = "select * from point_on_map";
			PreparedStatement pr_stm =connection.prepareStatement(query);
			ResultSet result_set = pr_stm.executeQuery();
			
			while(result_set.next()){
			
				PointOnMap point = new PointOnMap();
				point.setLatitude(result_set.getDouble("Latitude"));
				point.setLongitude(result_set.getDouble("Longitude"));
				point.setColor(result_set.getString("Color"));
				point.setValue(result_set.getDouble("Value"));
				point.setDate(result_set.getDate("Date"));
				points.add(point);
			
			}
		} catch (SQLException e) {
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
	
		return points;
	}

}
