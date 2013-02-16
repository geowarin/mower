Mower-core
==========

Permet de piloter une ou plusieurs tondeuses � l'aide d'un fichier texte.
La classe Main de l'application com.mowitnow.mower.MowerApplication peut charger plusieurs fichiers fournis en argument.
Si aucun argument n'est pass� en param�tre, l'application chargera par d�faut le fichier enonce.txt situ� dans src/main/resources.

Un ensemble de tests unitaire est disponible pour v�rifier le bon fonctionnement de l'application.
Par d�faut le logger de l'application est en niveau INFO ce qui n'affiche que la position finale des tondeuses.

Les tests utilisent un logger en DEBUG qui fournit plus d'informations sur l'�xecution de l'application.

Utilisation
===========

    mvn package
	java -jar tondeuse-core-1.0-SNAPSHOT.one-jar.jar
ou
    java -jar tondeuse-core-1.0-SNAPSHOT.one-jar.jar "fichier1" "fichier2"...