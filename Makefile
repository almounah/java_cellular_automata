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

all: testGUI testBallsSim testConway testImmigration testSchelling

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestGUI.java

testBallsSim:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestBallsSimulator.java

testConway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestConwaySimulator.java

testImmigration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestImmigrationSimulator.java

testSchelling:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestSchellingSimulator.java

testAgentsSim:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/fr/tests/TestAgentsSim.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar fr/tests/TestGUI

exeBallsSims:
	java -classpath bin:bin/gui.jar fr/tests/TestBallsSimulator

exeConway:
	java -classpath bin:bin/gui.jar fr/tests/TestConwaySimulator

exeImmigration:
	java -classpath bin:bin/gui.jar fr/tests/TestImmigrationSimulator

exeSchelling:
	java -classpath bin:bin/gui.jar fr/tests/TestSchellingSimulator

exeAgentsSim:
	java -classpath bin:bin/gui.jar fr/tests/TestAgentsSim

clean:
	rm -rf bin/fr

