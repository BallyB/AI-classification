
Détails sur le fonctionnement du programme sur la régression linéaire multiple :

Le lancement de l'application est la classe ApplicationDemo.java :

elle définit un nouveau "TirednessProcessor" :
TirednessProcessor tp = new TirednessProcessor(1, 10);

Les paramètres sont l'échelle de notation de la fatigue (ici de 1 à 10)

elle ajoute ensuite les différents échantillons de calibrations (Distance, temps, cadence, fatigue)
Ces échantillons seront rentrés par l'utilisateur.

Ensuite, elle récupère la prédiction de la fatigue associé à un nouvel échantillon :
tp.getTirednessLevel(10,60,50)
