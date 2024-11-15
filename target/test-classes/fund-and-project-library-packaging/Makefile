tests:
	javac -d ./ ../src/*.java
	javac *.java
	jar -cvfm tests.jar Manifest.txt *.class
	java -jar tests.jar

clean: 
	rm *.class
	rm *.jar
