function [LinearAccelerometerX, LinearAccelerometerY, LinearAccelerometerZ] = reorientedAcceleration(AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, Pitch_Angle, Roll_Angle)

LinearAccelerometerX = zeros(length(AccelerometroLineareX),1);
LinearAccelerometerY = zeros(length(AccelerometroLineareX),1);
LinearAccelerometerZ = zeros(length(AccelerometroLineareX),1);



for i =1 : numel(AccelerometroLineareZ)
LinearAccelerometerX(i,1) = cos(Pitch_Angle(i,1))*AccelerometroLineareX(i,1) + sin(Pitch_Angle(i,1))*sin(Roll_Angle(i,1))*AccelerometroLineareY(i,1)+cos(Roll_Angle(i,1))*sin(Pitch_Angle(i,1))*AccelerometroLineareZ(i,1);
LinearAccelerometerY(i,1) = cos(Roll_Angle(i,1))*AccelerometroLineareY(i,1) - sin(Roll_Angle(i,1))*AccelerometroLineareZ(i,1);    
LinearAccelerometerZ(i,1) = -sin(Pitch_Angle(i,1))*AccelerometroLineareX(i,1) + cos(Pitch_Angle(i,1))*sin(Roll_Angle(i,1))*AccelerometroLineareY(i,1)+cos(Pitch_Angle(i,1))*cos(Roll_Angle(i,1))*AccelerometroLineareZ(i,1);
end