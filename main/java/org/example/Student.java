package org.example;

/** Student essentially just acts as a struct **/
public class Student
{
    private String givenName;

    private String surname;
    private String UoB;
    private String role;
    private String year;

    public Student(String givenName, String surname, String ub, String role, String year)
    {
        this.givenName = givenName;
        this.surname = surname;
        for(int i = 0; i < ub.length(); i++)
        {
            char c = ub.charAt(i);
            if(Character.isAlphabetic(c))
            {
                System.out.println("Error! UoB number for Student: " + this.givenName + " is invalid format!");
                return;
            }
        }
        this.UoB = ub;
        this.role = role;

        if(year.length() != 4)
        {
            System.out.println("Error! Year: " + year + " incorrect format!");
            return;
        }
        else
        {
            this.year = year;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", UoB='" + UoB + '\'' +
                ", role='" + role + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String parse() {
        return "name=" + givenName +
                "=surname=" + surname +
                "=UoB=" + UoB  +
                "=role=" + role  +
                "=year=" + year;
    }


    public String getGivenName()
    {
        return this.givenName;
    }

    public String getSurname() {
        return surname;
    }

    public String getUoB() {
        return UoB;
    }
    public String getRole()
    {
        return this.role;
    }

    public String getYear() {
        return year;
    }
}
