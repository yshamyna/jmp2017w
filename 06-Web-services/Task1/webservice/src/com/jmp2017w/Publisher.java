package com.jmp2017w;

import com.jmp2017w.webservice.FileShareServiceImpl;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

/**
 * The endpoint publisher.
 */
public class Publisher
{
    private static final int PORT = 9876;
    private static final String URL = "http://localhost:" + PORT + "/file-share-service";

    /**
     * The main method.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        Publisher publisher = new Publisher();
        publisher.deploy();
    }

    /**
     * Deploys the web-service.
     */
    private void deploy()
    {
        Endpoint endpoint = Endpoint.create(new FileShareServiceImpl());
        endpoint.publish(URL);
        ((SOAPBinding) endpoint.getBinding()).setMTOMEnabled(true);
        System.out.println(URL);
    }
}