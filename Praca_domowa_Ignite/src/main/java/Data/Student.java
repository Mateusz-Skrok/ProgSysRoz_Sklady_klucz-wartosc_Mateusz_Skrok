package Data;


import java.io.Serializable;

public class Student implements Serializable {


    private String name;
    private String surname;
    private int indexNumber;
    private int age;


    public Student(String name,String surname,int indexNumber, int age) {
        this.name = name;
        this.surname=surname;
        this.indexNumber=indexNumber;
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student imie: " + name + " nazwisko: "+surname+" numer indexu: "+ indexNumber + " wiek: " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthyear() {
        return age;
    }

    public void setBirthyear(int birthyear) {
        this.age = birthyear;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }


}
