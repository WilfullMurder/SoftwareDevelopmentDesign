package org.example;

/** Student essentially just acts as a struct **/
public class Student
{
    private String givenName;

    private String surname;
    private String UoB;
    private String role;

    public Student()
    {

    }

    public Student(String givenName, String surname, String ub, String role)
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

    }

    @Override
    public String toString() {
        return "Student{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", UoB='" + UoB + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String parse() {
        return "name," + givenName +
                ",surname," + surname +
                ",UoB," + UoB  +
                ",role," + role;
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
    public String getYear()
    {
        return this.getUoB().substring(0,2);
    }

    public void setYear(String newYear)
    {
        String sub1 = this.UoB.substring(2);
        this.UoB = newYear += sub1;

    }

}
