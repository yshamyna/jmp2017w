package com.jmp2017w.webservice;

import javax.activation.DataHandler;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * File share service.
 */
@WebService(wsdlLocation = "FileShareService.wsdl", serviceName = "FileShareService",
            endpointInterface = "com.jmp2017w.webservice.FileShareService")
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class FileShareServiceImpl implements FileShareService
{
    private Map<String, DataHandler> fileStorage = new HashMap<String, DataHandler>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String create(DataHandler file)
    {
        UUID uuid = UUID.randomUUID();
        fileStorage.put(uuid.toString(), file);
        return uuid.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataHandler read(String uuid)
    {
        return fileStorage.get(uuid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(String uuid, DataHandler newFile)
    {
        boolean isUpdateOperationSuccessful = false;
        if (fileStorage.get(uuid) != null)
        {
            fileStorage.put(uuid, newFile);
            isUpdateOperationSuccessful = true;
        }
        return isUpdateOperationSuccessful;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String uuid)
    {
        boolean isDeleteOperationSuccessful = false;
        if (fileStorage.get(uuid) != null)
        {
            fileStorage.remove(uuid);
            isDeleteOperationSuccessful = true;
        }
        return isDeleteOperationSuccessful;
    }
}