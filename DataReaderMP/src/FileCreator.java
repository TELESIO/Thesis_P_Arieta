import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import data.manipulation.ColorGenerator;
import data.model.ResultData;

public class FileCreator {

    private HashMap<Integer, ResultData> result;

    public FileCreator(HashMap<Integer, ResultData> result) {
	this.result = result;
    }

    public void createFile() {
	try {
	    PrintWriter writer = new PrintWriter("index.html", "UTF-8");
	    writer.println("<!DOCTYPE html>");
	    writer.println("<html>");
	    writer.println("<head>");
	    writer.println(" <style> #mymap {width:90%; height:600px}  </style>");
	    writer.println("<script src=" + '"'
		    + "http://maps.google.com/maps/api/js?key=AIzaSyAnb1Or3S0GwU1T3EBkcVCaHtxh8Rfniyg" + '"'
		    + "></script>");
	    writer.println(" <script>");

	    writer.println("function init() {");
	    writer.println("var mapDiv = document.getElementById(" + '"' + "mymap" + '"' + ");");

	    writer.println("var mapOptions = {");
	    writer.println("center : new google.maps.LatLng (39.35564, 16.227043),");
	    writer.println("zoom : 15,");
	    writer.println("mapTypeId: google.maps.MapTypeId.ROADMAP");
	    writer.println(" };");
	    writer.println("var map = new google.maps.Map(mapDiv, mapOptions);");

	    for (Map.Entry<Integer, ResultData> resaa : result.entrySet()) {
		double lat = resaa.getValue().getGeolocalization().getLatitude();
		double lon = resaa.getValue().getGeolocalization().getLongitude();
		ColorGenerator g = new ColorGenerator();
		double value = resaa.getValue().getResultant_value();
		String color = g.getColor(value);
		// System.out.println(value);
		writer.println("var circle = new google.maps.Circle ({");
		writer.println("map: map,");
		writer.println("center: new google.maps.LatLng (" + lat + "," + lon + "),");
		writer.println(" radius: 1,");
		writer.println("strokeColor: " + '"' + color.toString() + '"' + "});");
	    }

	    writer.println("} ");
	    writer.println("window.onload = init; ");
	    writer.println(" </script>");
	    writer.println("</head>");
	    writer.println("<body>");
	    writer.println("<h2>Maps </h2>");
	    writer.println("<div id=" + "mymap" + "></div>");
	    writer.println("<div id=" + "info" + "></div>");
	    writer.println("</body>");
	    writer.println("</html>");

	    writer.close();
	} catch (

	IOException e) {
	    // do something
	}
    }

}
