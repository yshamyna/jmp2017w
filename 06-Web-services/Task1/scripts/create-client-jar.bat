rmdir /s /q target
md target\lib

xcopy /y client\lib\FileShareService-stub.jar target\lib
rem xcopy /y client\src\file1.txt target
rem xcopy /y client\src\file2.txt target
cd target
jar xf lib/FileShareService-stub.jar
rmdir /s /q META-INF
rmdir /s /q lib
cd ../
javac -classpath ./target -d ./target client/src/com/jmp2017w/Client.java

cd client
jar cvmf META-INF/manifest.mf client.jar -C ../target .