function [Latitude, Longitude] = redefineLatitudeAndLongitude (Latitudine, Longitudine)


Latitude = zeros(1, 1);
Longitude = zeros(1, 1);
i = 1;
    

last_latlon = [Latitudine(numel(Latitudine),1), Longitudine(numel(Longitudine),1)];
curr_latlon = [Latitudine(numel(Latitudine)-1,1), Longitudine(numel(Longitudine)-1,1)];
found = false;
index = numel(Latitudine)-1;

while(found == false)
  if(isequal(last_latlon,curr_latlon))
    index = index - 1;
      curr_latlon = [Latitudine(index,1), Longitudine(index,1)];
  else
       found = true;
  end      
end

for y = 1:numel(Longitudine)    
  latlon1 = [Latitudine(i,1), Longitudine(i,1)];
  latlon2 = [Latitudine(y,1), Longitudine(y,1)];
  
  if(y > index) 
       y = numel(Latitudine);
       latlon2 = [Latitudine(y,1), Longitudine(y,1)];
  end
  
  distance = lldistkm(latlon1,latlon2);   

    if(distance ~= 0)    
       LatitudeDifference = Latitudine(y,1) - Latitudine(i,1);
       LongitudineDifference = Longitudine(y,1) - Longitudine(i,1);
       steps = y - i;
   
       Longitude(i,1) = Longitudine(i,1);
       LongitudeToAdd = LongitudineDifference / steps;
       for ix = i+1:y-1
        Longitude(ix,1) = Longitude(ix-1,1)+LongitudeToAdd;
       end       
       Longitude(y,1) = Longitudine(y,1);
       
       Latitude(i,1) = Latitudine(i,1);
       LatitudeToAdd = LatitudeDifference / steps;
       for ix = i+1:y-1
           Latitude(ix,1) = Latitude(ix-1,1)+LatitudeToAdd;
       end
       Latitude(y,1) = Latitudine(y,1);   
    
    i = y;
    end
end


    