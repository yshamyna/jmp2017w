package com.jmp2017w;

import com.jmp2017w.webservice.FileShareService;
import com.jmp2017w.webservice.FileShareService_Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 * Client application that uses the web-service.
 */
public class Client
{
    private FileShareService service;

    /**
     * Instantiates client.
     */
    public Client()
    {
        service = new FileShareService_Service().getFileShareServiceImplPort();

        BindingProvider bp = (BindingProvider) service;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);
    }

    /**
     * Client's main method.
     * @param args arguments
     */
    public static void main(String... args) throws Exception
    {
        String userDir = System.getProperty("user.dir");
        Client client = new Client();

        File firstFile = getFileByName(userDir + "\\file1.txt");
        System.out.println("Creating a file.");
        String uuid = client.create(firstFile);
        System.out.println("First file UUID: " + uuid);
        client.getFileFromServiceAndDisplayFirstLine(uuid);

        System.out.println("Replacing file with UUID " + uuid);
        File secondFile = getFileByName(userDir + "\\file2.txt");
        client.update(uuid, secondFile);
        client.getFileFromServiceAndDisplayFirstLine(uuid);

        System.out.println("Remove file with UUID " + uuid);
        client.delete(uuid);
        client.getFileFromServiceAndDisplayFirstLine(uuid);
    }

    /**
     * Gets file from the service and displays first line from the file.
     * @param uuid UUID
     */
    private void getFileFromServiceAndDisplayFirstLine(String uuid)
    {
        System.out.println("Reading file from service by UUID" + uuid);
        DataHandler dataHandler = read(uuid);
        displayFirstLineFromFile(dataHandler);
    }

    /**
     * Stores the file on webservice side.
     * @param file a file
     * @return UUID of the file; <code>NULL</code> if file does not exist and creation process was failed
     */
    public String create(File file)
    {
        DataHandler dataHandler = wrapFileIntoDataHandler(file);
        String uuid = null;
        if (dataHandler != null)
        {
            uuid = service.create(dataHandler);
        }
        return uuid;
    }

    /**
     * Gets a file from service by UUID
     * @param uuid UUID
     * @return {@link DataHandler} stored on service side file; <code>NULL</code> if requested file does not exist for some reasons
     */
    public DataHandler read(String uuid)
    {
        DataHandler dataHandler = service.read(uuid);
        return dataHandler;
    }

    /**
     * Updates one file with UUID by other file.
     * @param uuid UUID of a file that should be replaced
     * @param file new file
     */
    public void update(String uuid, File file)
    {
        DataHandler dataHandler = wrapFileIntoDataHandler(file);
        service.update(uuid, dataHandler);
    }

    /**
     * Deletes a file by UUID.
     * @param uuid UUID of a file that should be removed from service.
     */
    public void delete(String uuid)
    {
        boolean isDeleteOperationSuccessful = service.delete(uuid);
        if (isDeleteOperationSuccessful)
        {
            System.out.println("File with UUID '" + uuid + "' was removed successfully");
        }
        else
        {
            System.out.println("File with UUID '" + uuid + "' has not been removed for some reasons.");
        }
    }

    /**
     * Gets a file by name.
     * @param name file name
     * @return a file
     */
    private static File getFileByName(String name)
    {
        File file = new File(name);
//        ClassLoader classLoader = Client.class.getClassLoader();
//        URL url = classLoader.getResource(name);
//        File file = null;
//        if (url != null)
//        {
//            try
//            {
//                file = new File(url.toURI());
//            }
//            catch (URISyntaxException e)
//            {
//                System.out.println("Error while getting file: " + name);
//            }
//        }
//        else
//        {
//            System.out.println("Unable to get file " + name);
//        }
        return file;
    }

    /**
     * Wraps {@link File} into {@link DataHandler}
     * @param file a file
     * @return {@link DataHandler}
     */
    private static DataHandler wrapFileIntoDataHandler(File file)
    {
        DataHandler dataHandler = null;
        if (file != null)
        {
            DataSource source = new FileDataSource(file);
            dataHandler = new DataHandler(source);
        }
        return dataHandler;
    }

    /**
     * Displays first line of a file.
     * @param dataHandler {@link DataHandler}
     */
    private static void displayFirstLineFromFile(DataHandler dataHandler)
    {
        if (dataHandler != null)
        {
            Scanner scanner = null;
            try
            {
                DataSource source = dataHandler.getDataSource();
                if (source != null)
                {
                    InputStream in = source.getInputStream();
                    scanner = new Scanner(in);
                    if (scanner.hasNextLine())
                    {
                        System.out.println(scanner.nextLine());
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Unable to read a file");
            }
            finally
            {
                if (scanner != null)
                {
                    scanner.close();
                }
            }
        }
        else
        {
            System.out.println("File is absent and cannot be read.");
        }

    }
}