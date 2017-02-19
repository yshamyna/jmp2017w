mkdir target
javac -classpath ./target -d ./target src/com/jmp2017w/memoryleak/tasks/*.java src/com/jmp2017w/memoryleak/Runner.java
xcopy /y resources\* target
jar cvmf META-INF/manifest.mf memoryleak.jar -C ./target .