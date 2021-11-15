# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)


all: simulator

testText:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestText.java
	java -classpath bin:bin/gui.jar fr/tests/TestText balls
	java -classpath bin:bin/gui.jar fr/tests/TestText events

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestGUI.java
	java -classpath bin:bin/gui.jar fr/tests/TestGUI

simulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestSimulator.java



exeBallsSims:
	java -classpath bin:bin/gui.jar fr/tests/TestSimulator balls

exeConway:
	java -classpath bin:bin/gui.jar fr/tests/TestSimulator conway

exeImmigration:
	java -classpath bin:bin/gui.jar fr/tests/TestSimulator immigration

exeSchelling:
	java -classpath bin:bin/gui.jar fr/tests/TestSimulator schelling

exeAgentsSim:
	java -classpath bin:bin/gui.jar fr/tests/TestSimulator boids

clean:
	rm -rf bin/fr

