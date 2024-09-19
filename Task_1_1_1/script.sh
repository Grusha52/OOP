javac -d bin -cp /Users/kenig.nsk/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.10.0/2fe4ba3d31d5067878e468c96aa039005a9134d3/junit-jupiter-api-5.10.0.jar:\
/Users/kenig.nsk/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-engine/5.10.0/90587932d718fc51a48112d33045a18476c542ad/junit-jupiter-engine-5.10.0.jar \
src/main/java/ru/nsu/chernikov/Main.java src/test/java/ru/nsu/chernikov/HeapsortTests.java
echo "Manifest-Version: 1.0" > Manifest.mf
echo "main-class: ru.nsu.chernikov.Main" >> Manifest.mf
echo "class-path: bin/ru/nsu/chernikov" >> Manifest.mf
jar cfm heapsort.jar Manifest.mf -C bin .
java -jar heapsort.jar
javadoc -d bin/doc -sourcepath src/main/java ru.nsu.chernikov
java -jar libs/junit-platform-console-standalone-1.11.0.jar execute --class-path bin/ --scan-classpath
echo "Finished!!!!"