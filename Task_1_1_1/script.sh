javac -d bin src/main/java/ru/nsu/chernikov/Main.java
jar cfm heapsort.jar manifest.mf -C bin .
java -jar heapsort.jar
javadoc -d bin/doc -sourcepath src/main/java ru.nsu.chernikov