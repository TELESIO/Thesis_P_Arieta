$(document).ready(function(){
	var current_layer = 'points_value';
	var previous_layer = '';
	var first_time = true;
	mapboxgl.accessToken = 'pk.eyJ1IjoicGFzcXVhbGVhcmlldGEiLCJhIjoiY2oyazZ3bDl0MDAwYzJ3cnkxNjg5Z3h0MyJ9.ojRjeCplUQ2fjfhmY5FIDQ'; 
		var map = new mapboxgl.Map({
		attributionControl: false,
		container: 'map',
		style: 'mapbox://styles/mapbox/streets-v9',
		center: [16,35],
		 maxZoom: 22,minZoom: 3,
		 
		}).addControl(new mapboxgl.AttributionControl({
	        compact: true
	    }));;
		
	
		
		map.dragRotate.disable();
		map.touchZoomRotate.disableRotation();
		

		var geocoder = new MapboxGeocoder({
			  accessToken: mapboxgl.accessToken
			});

		document.getElementById('geocoder').appendChild(geocoder.onAdd(map));
		
		
		var nav = new mapboxgl.NavigationControl();
		map.addControl(nav, 'top-left');
		
		$("#critical" ).click(function() {
	         $.ajax({
	             url:'map?action=CriticalPoints',
	             type:'get',
	             cache:false,
	             success:function(valori){
	            	 previous_layer = current_layer;	
	            	 current_layer = 'critical';
	            	 response(valori);	
      	             },
	             error:function(){
	            	 alert('error');
	             }
	          }
	     );		
		});
		
		
		$("#iri" ).click(function() {
	         $.ajax({
	             url:'map?action=IRI',
	             type:'get',
	             cache:false,
	             success:function(valori){
	            	 previous_layer = current_layer;	
	            	 current_layer = 'iri';
	            	 response(valori);	
     	             },
	             error:function(){
	            	 alert('error');
	             }
	          }
	     );		
		});
		
		
		$("#simple" ).click(function() {
	         $.ajax({
	             url:'map?action=SimplePoints',
	             type:'get',
	             cache:false,
	             success:function(valori){
	            	 previous_layer = current_layer;	
	            	 current_layer = 'points_value';
	            	 response(valori);	
     	             },
	             error:function(){
	            	 alert('error');
	             }
	          }
	     );		
		});
		


		
		function response(responseJson){
		try{
			if (map.getLayer(previous_layer)) {
			    map.removeLayer(previous_layer);
			    map.removeSource(previous_layer);
			}
		}
		catch(err){
			alert(err);
		}
			var point = $.parseJSON(responseJson);
			var puntiNuovi = GeoJSON.parse(point, {Point: ['latitude', 'longitude'], include: ['color', 'date', 'value']});	
			map.addLayer({
					'id': current_layer,
					'type': 'circle',
					'source': {
					type: 'geojson',
					data: puntiNuovi
					},
					'paint' : {
					 'circle-color' : {
					  property:'color',
					  type: 'identity'
					},

					 'circle-radius':  {
						 'stops': [[5, 6],[6, 5.9],[7, 5.8],[8, 5.7],[9, 5.6],[10, 5.5],[11, 5.4],[12, 5.3],[13, 5.2],[14, 5.1],[15, 5],[16, 4.9],[17, 4.8],[18,4.7],[19,4.6],[20,4.5],[21,4.4],[22,4.3]]},
					  'circle-opacity' : 0.9
					}
					});
			
		};
		
			map.on('load', function () {
				response(document.getElementById('data').value);
			});
		


			map.on('click', function(e) {
		    var features = map.queryRenderedFeatures(e.point, { layers: [current_layer] });
		    // se non ci sono punti, esci.
		    if (!features.length) {
		        return;
		    }

		    var feature = features[0];

		    // Crea il popup con alcune informazioni presenti nelle proprietà del punto
		    var popup = new mapboxgl.Popup()
		        .setLngLat(feature.geometry.coordinates)
		        .setHTML('<div id="mapboxgl-popup" class="popup"> <h5> Detail: </h5>' +
		            '<ul class="list-group">' +
		            '<li class="list-group-item"><strong> Date: </strong>' + feature.properties['date'] + " </li>"  +
		            '<li class="list-group-item"><strong> Value: </strong>' + feature.properties['value'] + " </li>"  +'</ul> </div>')
	        .addTo(map);
		});
		
		
		//cambia il cursore quando si passa su un punto.
		map.on('mousemove', function(e) {
			var features = map.queryRenderedFeatures(e.point, { layers: [current_layer] });
			if (undefined !== features && features.length) {
				map.getCanvas().style.cursor = (features.length) ? 'pointer' : '';
			} else {
				map.getCanvas().style.cursor =  '';
			}
			
		});
	});
