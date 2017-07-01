<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Monitoring road surface conditions using inertial measurement units.</title>

<meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no' />
<meta charset=utf-8 />

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- Latest compiled and minified CSS -->
<link href='https://api.mapbox.com/mapbox-gl-js/v0.33.1/mapbox-gl.css'
	rel='stylesheet' />

<link href='https://www.mapbox.com/base/latest/base.css'
	rel='stylesheet' />
<link rel='stylesheet'
	href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v2.1.0/mapbox-gl-geocoder.css'
	type='text/css' />

<style>
html, body, .container-full {
	height: 100%;
	cursor:default;
}


#myNavbar{
	border-color: #E7E7E7;
}

#item > li > a, 
#item > li > a:focus {
	 font-size: 16px;
	 font-weight: bold;
	 color: #FFFFFF;
}

#item > li > a:hover {
	color: yellow;
	 font-size: 18px;
	-webkit-transition-duration: 0.8s; 
	transition-duration: 0.8s;
	cursor:pointer;
} 


#button-nav, 
#button-nav:focus{
	background-color: #800000;
	border-color: white;
	-webkit-transition-duration: 0.8s; 
	transition-duration: 0.8s;
}

#button-nav:hover{
	background-color: #b30000;
}

.navbar {
    background-color: #800000;
    border-color: #E7E7E7;
}

.navbar{
    padding-bottom: 0px;
    margin-bottom: 0px;
}
.footer{
	height:2%;
}

 #map-container {
	height: calc(100% - 10%);
}
#map{	
	height: 100%;
}

 #locations {
	position: absolute;
	
}

.mapboxgl-ctrl-compass {
display: none !important;
}

 #geocoder {
 padding-top: 5px;
 margin-left: 10px;
	
}


input[type=text]:hover, textarea:hover {
  box-shadow: 0 0 2px black;
  border: 2px solid yellow;
  border-radius:2px;
}


.mapboxgl-popup-content{
	 border: 1px solid  #800000;
	  box-shadow: 0 0 2px black;
}
.popup{
	max-width: 600px;

}

@media (max-width: 736px) {
.mapboxgl-ctrl-geocoder ul {
position: relative;
}
}

.mapboxgl-popup {
	opacity: 0.95;
	border: none;
	font: 12px/20px 'Helvetica Neue', Arial, Helvetica, sans-serif;
}






</style>
</head>
<body>
<div class="container-full">
  <!-- Navigation -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
   
      <button id="button-nav" type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
	 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav" id="item">  
         <li><a id="simple">Simple Points</a></li> 
         <li><a id="critical">Critical Points</a></li> 
         <li><a id="iri">IRI</a></li>    
      
     </ul>
      <ul class="nav navbar-nav navbar-right">
      <li id="geocoder" style=""></li>
    </ul>
    </div>
    
  </div>
</nav>
	
	
	
<textarea id="data" style="display:none;"><%=request.getAttribute("result")%></textarea>
  <!-- Main Content-->
  <div class="container-full" id="map-container">
  <div id="map"></div>
  </div>
</div> <!-- /container -->

		
		<script type="text/javascript" src="js/geojson.min.js"></script>
		<script
			src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v2.1.0/mapbox-gl-geocoder.min.js'></script>
		<script src='https://api.mapbox.com/mapbox-gl-js/v0.33.1/mapbox-gl.js'></script>		
		<script type="text/javascript" src="js/home.js"></script>	
</body>
</html>