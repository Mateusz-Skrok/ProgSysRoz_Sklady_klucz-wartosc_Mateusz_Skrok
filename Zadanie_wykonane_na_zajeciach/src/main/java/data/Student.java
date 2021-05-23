package data;

import com.hazelcast.collection.IList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private int indexNumber;
    private int age;
    private List<Zajecia> zajeciaList;


    public Student(String name,String surname,int indexNumber, int age) {
        this.name = name;
        this.surname=surname;
        this.indexNumber=indexNumber;
        this.age = age;
        zajeciaList = new ArrayList<Zajecia>();
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

    @Override
    public String toString(){
        return "data.Student imie: " + name + " nazwisko: "+surname+" numer indexu: "+ indexNumber + " wiek: " + age;
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

    public List<Zajecia> getZajeciaList() {
        return zajeciaList;
    }

    public void setZajeciaList(List<Zajecia> zajeciaList) {
        this.zajeciaList = zajeciaList;
    }
}
