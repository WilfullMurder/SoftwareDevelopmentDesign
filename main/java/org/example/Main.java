package org.example;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class Main {





    public static void main(String[] args) {
        Instant previous = null, current;
        Long gap;
        current = Instant.now();
        int studentCount = 30;
        YearlyOfficers yo = new YearlyOfficers("awc.txt", studentCount);
        yo.run();

        previous = current;
        current = Instant.now();
        gap = ChronoUnit.MILLIS.between(previous, current);

        if(gap > 1000)
        {
            gap /= 1000;
            System.out.println("Solution completed: " + gap + " s");
        }
        else
        {
            System.out.println("Solution completed: " + gap + " ms");
        }

    }
}