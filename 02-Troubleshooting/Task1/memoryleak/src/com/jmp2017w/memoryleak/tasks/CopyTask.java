package com.jmp2017w.memoryleak.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class is responsible for copying data.
 */
public class CopyTask extends Task
{
    private String fileContent;

    /**
     * Copies zipped file. New file will have name <tt>copy.zip</tt>.
     * @param zipFilename name of zipped file
     * @throws IOException in case of any I/O errors
     * @throws IllegalStateException if zipped file name is empty or NULL
     */
    @Override
    public void execute(String zipFilename) throws IOException
    {
        fileContent = "";
        super.execute(zipFilename);
        File file = createFileWithContent("file.txt", fileContent);
        packFile(file);
        file.delete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performActionWithLine(String line)
    {
        fileContent = fileContent + line + "\n";
    }

    /**
     * Creates file and populate it.
     * @param filename name of file
     * @param content content of a file
     * @return file object
     * @throws IOException in case of any I/O errors
     */
    private File createFileWithContent(String filename, String content) throws IOException
    {
        File file = new File(filename);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        return file;
    }

    /**
     * Packs file.
     * @param file a file to be zipped
     * @throws IOException in case of any I/O errors
     */
    private void packFile(File file) throws IOException
    {
        byte[] buffer = new byte[1024];
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("copy.zip"));
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zipOutputStream.putNextEntry(zipEntry);
        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

        int length;
        while ((length = fileInputStream.read(buffer)) > 0)
        {
            zipOutputStream.write(buffer, 0, length);
        }
    }
}