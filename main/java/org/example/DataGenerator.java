package org.example;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/***WARNING!WARNING!WARNING!WARNING!WARNING!WARNING!WARNING!***/
/***TEXT FILE FILESIZE CONFIGURED LIMIT IS 2.56MB, TEXT FILES CANNOT EXCEED 197.1MB!***/
/***WARNING!WARNING!WARNING!WARNING!WARNING!WARNING!WARNING!***/

public class DataGenerator
{
    private final String[] GIVEN_NAMES = new String[] {"Amy", "Amirah", "Alice", "Alicia", "Babette", "Bethany", "Bryoni", "Caitrionagh", "Camelia", "Celine", "Daena", "Daphne", "Delphinah",
            "Ealasidde", "Ekatrina", "Eleanor", "Fabiannah","Fang Hua","Fanny", "Gabriella","Gail","Giselle", "Habibah","Harmony","Hippolyta",
            "Ianthe","Ileane","Ives", "Jaana","Jemimah","Julietta","Kacey","Kamiko","Kiranpal","Labdhi","Leisel","Lyneth", "Ma","Mehitabel","Muriel",
            "Nada","Neiva","Nicolette", "Oba","Odetta","Omaka", "Paavani","Petunia","Portia", "Qadira","Qianna","Quinella", "Rabi","Renora","Rieko",
            "Sa","Sebille","Sheilagh", "Taa","Teagan","Titania", "Uberta","Udaya","Usagi", "Vachya","Vedah","Vivienne", "Wachiwi","Wendeline","Whitney",
            "Xalbadora","Xenobia","Xiu Mei", "Yaa","Yemina","Yolanada", "Zaafirah","Zelda","Zsuzsanna"};

    private final String[] SURNAMES = new String[] {"Ab Adam", "Ainscough", "Akess", "Al-Qanawati", "Baalham", "Bacherach", "Beesly","Birss", "Caffyn", "Campilyon", "Chue","Czentner",
            "D'Hoe", "Dacre", "Docwra","Duignan", "Eabry", "Edkyns", "Elsam","Eyres", "Faelor", "Farquhar", "Fernihaugh","Fitzharris",
            "Gaddesden", "Ghisnes", "Gimblet","Goetz", "Habatell", "Haide", "Hertzbrunn","Howys", "I'Anson","Ihde","Inskippe","Isam",
            "Jack","Jeffcoat","Jinby","Jupp", "Kalwa","Kehir","Keough","Kilpatrick", "La Lane","Le Lean","Lediard","Lickford",
            "Maarafi","McCarty","Mewboorn","Mimet","Naggs","Newf","Nokes","Nutt", "O'Donnchadha","Olmsted","Orgle","Oystin",
            "Packman","Pedwardine","Phillipides","Puttuck","Quatermaine","Quatrucci","Questead","Quirke", "Radmoore","Realf","Rhavan","Roandeau",
            "Saban","Scantelberry","Selous","Shiloo", "Tabb","Tabourdeaux","Tetlow","Treagust", "Ubank","Udal","Ulfe","Uvedale","Van Der Bilt","Van Engers","Viccermane","Von Fumetti",
            "Wackinshaw","Weinister","Wharfer","Wilderspine", "Yair","Yapp","Yearker","Yoxal", "Zacksfield","Zealand","Zehender","Zionzee",};

    private final String[] ROLES = new String[] {"Treasurer", "Secretary", "etc."};
    private final int[] YEARS = new int[] {2021, 2022, 2023};
    private Random random = new Random();

    /**
     * generate new list of student data
     * @param count total amount of students to generate
     * @return ArrayList<Student> ~ list of type Student of length count
     */
    public ArrayList<Student> generateData(int count)
    {

        ArrayList<Student> students = new ArrayList<>();
        Instant start = Instant.now();
        for(int i =0; i < count; i++)
        {
            students.add(new Student(getGivenName(),getSurname(),generateUB(),getRole(),getYear()));
        }
        System.out.println("Total students generated: " + students.size());
        Instant end = Instant.now();
        Long gap = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Time taken for generation: " + gap + " ms");
        return students;
    }


    //SHOULD PROBS RENAME THESE TO GEN_X OR GET_RAN_X OR SOMETHING
    private String getGivenName()
    {
        return this.GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)];
    }
    private String getSurname()
    {
        return this.SURNAMES[random.nextInt(SURNAMES.length)];
    }
    private String getRole()
    {
        return this.ROLES[random.nextInt(ROLES.length)];
    }
    private String getYear()
    {
        return Integer.toString(this.YEARS[random.nextInt(YEARS.length)]);
    }

    private String generateUB()
    {
        String s = "";
        for(int i  = 0; i < 8; i++)
        {
            s += Integer.toString(random.nextInt(10));
        }
        return s;
    }



}
