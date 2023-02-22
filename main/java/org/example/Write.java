package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class Write
{


    /**
     * create file if necessary
     * @param filePath desired path of file to create
     * @return the File in question
     */
    private File createFile(String filePath)
    {
        String path = filePath;
        File f = new File(path);

        try {
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File " + f.getName() + " successfully created");
            } else {
                System.out.println("File " + f.getName() + " already exists! File will be overwritten!");
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return f;
    }



    public void writeDataToFile(String filename, ArrayList<Student> data)
    {
        File f = createFile(filename);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            Instant start = Instant.now();
            for(int i = 0; i < data.size(); i++)
            {
                bw.write(data.get(i).parse());
                bw.newLine();
            }
            bw.close();
            Instant end = Instant.now();
            Long gap = ChronoUnit.MILLIS.between(start, end);
            System.out.println("Time taken to write " + f.getName() + ": " + gap + " ms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
