mkdir target
avac -classpath ./target -d ./target src/com/jmp2017w/nonmemoryleak/tasks/*.java src/com/jmp2017w/nonmemoryleak/Runner.java
xcopy /y resources\* target
jar cvmf META-INF/manifest.mf nonmemoryleak.jar -C ./target .