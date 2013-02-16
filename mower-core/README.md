Mower-core
==========

Permet de piloter une ou plusieurs tondeuses à l'aide d'un fichier texte.
La classe Main de l'application com.mowitnow.mower.MowerApplication peut charger plusieurs fichiers fournis en argument.
Si aucun argument n'est passé en paramètre, l'application chargera par défaut le fichier enonce.txt situé dans src/main/resources.

Un ensemble de tests unitaire est disponible pour vérifier le bon fonctionnement de l'application.
Par défaut le logger de l'application est en niveau INFO ce qui n'affiche que la position finale des tondeuses.

Les tests utilisent un logger en DEBUG qui fournit plus d'informations sur l'éxecution de l'application.

Utilisation
===========

    mvn package
	java -jar tondeuse-core-1.0-SNAPSHOT.one-jar.jar
ou
    java -jar tondeuse-core-1.0-SNAPSHOT.one-jar.jar "fichier1" "fichier2"...