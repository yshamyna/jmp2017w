package com.jmp2017w.webservice;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.BindingType;

/**
 * Service endpoint interface. Provide methods to manage files.
 */
@WebService
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
interface FileShareService
{
    /**
     * Adds a file to file storage.
     * @param file a file to store
     * @return uuid of the file as string
     */
    @WebMethod
    String create(@XmlMimeType("application/octet-stream") DataHandler file);

    /**
     * Gets a file by uuid,
     * @param uuid uuid of a file
     * @return a file
     */
    @WebMethod
    @XmlMimeType("application/octet-stream")
    DataHandler read(String uuid);

    /**
     * Updates a file in file storage.
     * @param uuid uuid of a file that should be replaced
     * @param newFile new file for @uuid
     * @return <code>true</code> if file did exist for specified <tt>uuid</tt> and was replaced;
     *         <code>false</code> if there was nothing to replace
     */
    @WebMethod
    boolean update(String uuid, @XmlMimeType("application/octet-stream") DataHandler newFile);

    /**
     * Deletes file from file storage.
     * @param uuid uuid of a file
     * @return <code>true</code> if file did exist and was removed; <code>false</code> if there was nothing to remove
     */
    @WebMethod
    boolean delete(String uuid);
}