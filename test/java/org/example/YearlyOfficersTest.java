package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class YearlyOfficersTest
{
    DataGenerator dataGenerator = new DataGenerator();
    Write write = new Write();
    Read read = new Read();
    ArrayList<Student> students = dataGenerator.generateData(10);

    YearlyOfficers yearlyOfficers;

    @RepeatedTest(8)
    @Order(0)
    void testOfficerClass(RepetitionInfo info)
    {
        //first test case is actual minimum test case
        String file = "testOfficers.txt"; //to required file
        String folder = "test"; //in required folder
        int count = 10; //with the min amount of students

        //x - 1 because RepetitionInfo returns 1 as first for some reason?!
        //@TODO: I might look at ParameterizedTests for this



        yearlyOfficers = new YearlyOfficers(file, folder, count);

        assertAll("Null checks",
                ()->assertNotNull(yearlyOfficers),
                ()->assertNotNull(yearlyOfficers.getFileName()),
                ()->assertNotNull(yearlyOfficers.getStudentCount()),
                ()->assertTrue((yearlyOfficers.getStudentCount() > 9)));

        yearlyOfficers.run();
    }

    @RepeatedTest(4)
    @Order(1)
    void SeparateOfficersTest(RepetitionInfo info)
    {
        String f = "SecondTest.txt";
        int count = 300;

        switch(info.getCurrentRepetition()-1)
        {
            case 1:
                f = "ThirdTest.txt";
                count = 3000;
                break;
            case 2:
                f = "FourthTest.txt";
                count = 30000;
                break;
            case 3:
                f = "FifthTest.txt";
                count = 300000;
                break;
            //not called because it takes ~41 minutes pmsl
            case 4:
                f = "SixthTest.txt";
                count = 3000000;
                break;
        }


        String finalF = f;
        int finalCount = count;
        assertAll("writing data",
                ()-> assertNotNull(finalF),
                ()-> assertNotNull(finalCount),
                ()-> assertTrue(finalCount>9));

        write.writeDataToFile(getFilePath(f), dataGenerator.generateData(count));

        this.students = read.ReadFileData(getFilePath(f));
        assertNotNull(this.students);

        ArrayList<Student> year21 = testSeparateOfficers("21");
        ArrayList<Student> year22 = testSeparateOfficers("22");
        ArrayList<Student> year23 = testSeparateOfficers("23");

        ArrayList<Student> finalYear21 = year21;
        ArrayList<Student> finalYear22 = year22;
        ArrayList<Student> finalYear23 = year23;

        assertAll("Separating officers",
                ()-> assertNotNull(finalYear21),
                ()-> assertNotNull(finalYear22),
                ()-> assertNotNull(finalYear23));

        String y = addYear(f,"21");
        year21 = CloseFiles(year21, y);
        y = addYear(f,"22");
        year22 = CloseFiles(year22, y);
        y = addYear(f,"23");
        year23 = CloseFiles(year23, y);


        ArrayList<Student> finalYear_21 = year21;
        ArrayList<Student> finalYear_22 = year22;
        ArrayList<Student> finalYear_23 = year23;

        assertAll("Files closed",
                ()-> assertNull(finalYear_21),
                ()-> assertNull(finalYear_22),
                ()-> assertNull(finalYear_23));

    }

    private String addYear(String toAdd, String year)
    {
        String sub1 = toAdd.substring(0, toAdd.length()-4);
        sub1+=year+".txt";
        return sub1;
    }

    ArrayList<Student> testSeparateOfficers(String year)
    {
        ArrayList<Student> thisYearsList = new ArrayList<>();
        assertAll("Initial null checks",
                ()->assertNotNull(students),
                ()->assertNotNull(year));

        for(int i = 0; i < students.size(); i++)
        {
            String check = students.get(i).getUoB().substring(0,2);
            assertAll("null checks",
                    ()-> assertNotNull(check),
                    ()-> assertNotNull(year));
                if(check.equals(year)) {
                    thisYearsList.add(students.get(i));
                }


        }
        assertNotNull(thisYearsList);
        //clear any matches so we don't loop again
        this.students.removeAll(thisYearsList);
        return thisYearsList;
    }

    ArrayList<Student> CloseFiles(ArrayList<Student> data, String filePath)
    {
        System.out.println("Total students year " + data.get(0).getYear() + " : " + data.size());
        write.writeDataToFile(getFilePath(filePath), data);
        data.clear();
        return null;
    }

    private String getFilePath(String filename)
    {
        Path p = Paths.get("");
        String s = p.toAbsolutePath().toString();
        s+= "\\src\\test\\java\\org\\example\\";
        s+=filename;
        return s;
    }


}