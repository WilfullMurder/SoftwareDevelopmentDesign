package org.example;//JacobFarrow 20007972

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class YearlyOfficers
{
    /**
     * class needs to:
     * reorganise records from awc.txt
     * into 3 text files according to year of student association
     * i.e awc21, awc22, awc23
     * and then counting the number of officers in the output files
     */

    private final String PREFIX = "awc";
    private final String[] YEARS = {"21", "22", "23"};
    private final String SUFFIX = ".txt";
    private int studentCount;
    private String fileName;
    private ArrayList<Student> studentsData = new ArrayList<>();
    private DataGenerator dataGenerator = new DataGenerator();
    private Write write = new Write();
    private Read read = new  Read();



    /**
     * WARNING!
     * TEXT FILE FILESIZE CONFIGURED LIMIT IS 2.56MB, TEXT FILES CANNOT EXCEED 197.1MB!
     * WARNING!
     * STUDENT COUNT SHOULD NOT EXCEED 300,000!!!
     * @param filename name of initial file
     * @param count number of students in initial file
     */
    public YearlyOfficers(String filename, int count)
    {
        if(filename.isEmpty() || filename == null)
        {
            emptyFileName();
            return;
        }

        if(count < 10)
        {
            poorTurnout(count);
            return;
        }

        this.fileName = filename;
        this.studentCount = count;
    }

    public void run()
    {
        openFiles(this.fileName);
    }

    /**
     * get absolute path of project and append with file name
     * @param filename name of file
     * @return absolute path of file
     */
    private String getFilePath(String filename)
    {
        Path p = Paths.get("");
        String s = p.toAbsolutePath().toString();
        s+= "\\src\\main\\java\\org\\example\\";
        s+=filename;
        return s;
    }

    /**
     * seems a bit dense to open all 4 files concurrently
     */
    /**
     *
     * @param filename
     */
    private void openFiles(String filename)
    {
        //opens and writes to the initial file "awc.txt"
        write.writeDataToFile(getFilePath(fileName), dataGenerator.generateData(this.studentCount));

        //pulls data from file into list and closes files
        this.studentsData = read.ReadFileData(getFilePath(filename));

        System.out.println("beginning separation...");
        Instant current = Instant.now();
        //initialises, declares and fills student lists by year
        ArrayList<Student> year21 = separateOfficers(this.studentsData, "2021");
        ArrayList<Student> year22 = separateOfficers(this.studentsData, "2022");
        ArrayList<Student> year23 = separateOfficers(this.studentsData, "2023");

        Instant previous = current;
        current = Instant.now();
        Long gap = ChronoUnit.MILLIS.between(previous, current);
        System.out.println("Time taken for separation: " + gap + " ms");

        closeFiles(year21, PREFIX+YEARS[0]+SUFFIX);
        closeFiles(year22, PREFIX+YEARS[1]+SUFFIX);
        closeFiles(year23, PREFIX+YEARS[2]+SUFFIX);

    }

    private ArrayList<Student> separateOfficers(ArrayList<Student> studentArrayList, String year)
    {
        System.out.println("Sorting by year: " + year);
        Instant current = Instant.now();
        
        ArrayList<Student> thisYearsList = new ArrayList<>();

        for(int i = 0; i < studentArrayList.size(); i++)
        {
            if(studentArrayList.get(i).getYear().equals(year)) {
                thisYearsList.add(studentArrayList.get(i));
            }
        }
        //clear any matches so we don't loop again
        this.studentsData.removeAll(thisYearsList);



        Instant previous = current;
        current = Instant.now();
        Long gap = ChronoUnit.MILLIS.between(previous, current);
        System.out.println("Time taken for sorting: " + gap + " ms");

        return thisYearsList;
    }

    private void closeFiles(ArrayList<Student> data, String filePath)
    {
        System.out.println("Total students year " + data.get(0).getYear() + " : " + data.size());
        write.writeDataToFile(getFilePath(filePath), data);
        data.clear();
    }

    private void emptyFileName()
    {
        System.out.println("error! filename is empty or null!!");
    }

    private void poorTurnout(int count)
    {
        System.out.println("error! count: " + count + " too low!" );
    }

}
