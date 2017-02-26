@echo off

rmdir /s /q application
md application\plugins
xcopy /y scripts\run-application.bat application

md target\lib

xcopy /y modules\appcore\resources\log4j.properties target
xcopy /y modules\appcore\lib\log4j-1.2.17.jar target\lib
cd target
jar xf lib/log4j-1.2.17.jar
rmdir /s /q META-INF
cd ../
javac -classpath ./target/lib/log4j-1.2.17.jar -d ./target modules/appcore/src/com/jmp2017w/service/*.java modules/appcore/src/com/jmp2017w/Runner.java
cd target
rmdir /s /q lib
cd ../
jar cvmf modules/appcore/META-INF/manifest.mf application/application.jar -C ./target .

javac -classpath ./target -d ./target modules/plugins/hello-plugin/src/com/jmp2017w/service/HelloPlugin.java
jar cvf application/hello-plugin.jar -C ./target com/jmp2017w/service/HelloPlugin.class

javac -classpath ./target -d ./target modules/plugins/goodbye-plugin/src/com/jmp2017w/service/GoodbyePlugin.java
jar cvf application/goodbye-plugin.jar -C ./target com/jmp2017w/service/GoodbyePlugin.class

javac -classpath ./target -d ./target modules/plugins/hru-plugin/src/com/jmp2017w/service/HowAreYouPlugin.java
jar cvf application/hru-plugin.jar -C ./target com/jmp2017w/service/HowAreYouPlugin.class

rmdir /s /q target