# Helpdesk Entreprise - Projet Java

Simulation d'un centre de support informatique interne.

## Structure du projet
- src/model/Affichable.java - Interface d'affichage
- src/model/Personne.java - Classe abstraite commune
- src/model/Appelant.java - Employe qui contacte le support
- src/model/Technicien.java - Agent support Niveau 1 2 ou 3
- src/model/Appel.java - Ticket central classe principale
- src/Main.java - Point d entree du programme

## Concepts POO utilises
Heritage, classe abstraite, interface, polymorphisme, enum

## Comment lancer
Compiler : javac src/model/*.java src/Main.java
Executer : java -cp src Main
