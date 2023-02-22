package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Read
{

    public ArrayList<Student> ReadFileData(String filePath) {

        ArrayList<Student> data = new ArrayList<>();
        HashMap<String, String> dataMap = new HashMap<>();
        String[] words = null;
        String line = "";

        try{
            Scanner reader = new Scanner(new File(filePath));
            while(reader.hasNextLine())
            {
                /**
                 * Using Scanner can end up spawning a lot of objects for every line.
                 * and generate a decent amount of garbage for the GC with large files.
                 * nearly three times slower than using split(). ~ need to check this claim
                 */
                line = reader.nextLine();

                /**
                 * Default regex is whitespace so
                 * Split regex is "=" due to student data formatting
                 * Student: return "name=" + givenName +
                 *                 "=surname=" + surname +
                 *                 "=UoB=" + UoB  +
                 *                 "=role=" + role  +
                 *                 "=year=" + year;
                 */
                words = line.split("=");

                /**
                 * Format is essentially key:value pair in array format.
                 * ipso facto each element at an odd index is a value, stored in order of requirement
                 * i.e. words[0] = "name", words[1] = givenName;
                 */
                Student s = new Student(words[1], words[3], words[5], words[7], words[9]);

                if(!data.contains(s))
                {
                    data.add(s);
                }
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return data;
    }

}
