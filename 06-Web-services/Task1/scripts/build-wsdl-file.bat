md tmp\wsgen
cd webservice/src
wsgen -verbose -cp ../../target com.jmp2017w.webservice.FileShareServiceImpl com.jmp2017w.webservice.FileShareService -wsdl -d ../../tmp/wsgen -s ../../tmp/wsgen