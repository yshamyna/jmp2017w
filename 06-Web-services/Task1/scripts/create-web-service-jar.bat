rmdir /s /q /d target
mkdir target
cd webservice

xcopy /y src\FileShareService.wsdl ..\target
jar cvmf META-INF/manifest.mf FileShareService.jar -C ../target .