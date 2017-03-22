function [displacement] = forward_euler(AccelerationY, Time)

%%Questa Funzione utilizza il Forward-Euler-Method per effettuare
%%il processo di doppia integrazione, per ottenere dai di Accelerazione
%%Verticale lo spostamento.





%%Definisco per ogni dato l'intervallo di registrazione poichè il tempo di registrazione 
%% non risulta esser sempre costante.

deltaT = zeros(numel(Time),1);
for index = 1:numel(Time)-1
    deltaT(index) =  (Time(index+1)-Time(index));
end

deltaT(numel(deltaT), 1 ) = (Time(numel(deltaT))-Time(numel(Time)-1));

%%inizializzo i vettori.
velocity = zeros(numel(deltaT),1);
displacement = zeros(numel(deltaT), 1);
velocity(1,1)=0.0;
displacement(1,1)=0.0;


%%Viene effettuato il processo di integrazione
for index = 1 : numel(deltaT)-1
    velocity(index+1,1) = velocity(index,1)+AccelerationY(index,1) * deltaT(index,1);
    displacement(index+1,1) = displacement(index,1)+velocity(index,1) * deltaT(index,1);
end

    velocity(numel(velocity),1) = velocity(numel(velocity)-1,1)+AccelerationY(numel(velocity)-1,1) * deltaT(numel(velocity)-1,1);
    displacement(numel(displacement),1) = displacement(numel(displacement)-1,1)+velocity(numel(displacement)-1,1) * deltaT(numel(displacement)-1,1);


%%Lo stesso risultato si ottiene utilizzando la funzione cumtrapz di Matlab
% Velocity=cumtrapz(Time, AccelerationY);
% displacement=cumtrapz(Time, Velocity);



%% Per vedere il risultato dell'integrazione decommentare qui sotto.
% plot (Time, displacement)
% legend('Displacement')
% title('Vertical Displacement')
% xlabel('Time')
% ylabel('Displacement')

clearvars velocity deltaT;
