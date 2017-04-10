
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import data.Data;


public class FileCreator {

    private ArrayList<Data> result;

    public FileCreator(ArrayList<Data> result) {
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

	    for (Data d : result) {
			double lat = d.getLatitude();
			double lon = d.getLongitude();			
			ColorGenerator g = new ColorGenerator();
			double value = d.getDisplacement();
				
				if(value > 0.04){	

				String color = g.getColor(value);
				writer.println("var circle = new google.maps.Circle ({");
				writer.println("map: map,");
				writer.println("center: new google.maps.LatLng (" + lat + "," + lon + "),");
				writer.println(" radius: 1,");
				writer.println("strokeColor: " + '"' + color.toString() + '"' + ",");
				writer.println("fillColor: " + '"' + color.toString() + '"' + "});");
			
					if(value>0.6){
					writer.println("marker = new google.maps.Marker({");
					writer.println("map: map,");
					writer.println("animation: google.maps.Animation.DROP,");
				    writer.print("position: {lat: "+lat+", lng: "+lon+"}});");
					}
				}
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
	}
    }

}
