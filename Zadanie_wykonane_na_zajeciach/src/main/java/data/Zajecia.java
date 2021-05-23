package data;

import data.Prowadzacy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zajecia implements Serializable {

    private static final long serialVersionUID = 2L;
    private String name;
    private int grade;
    private int roomNumber;
    private List<Prowadzacy> prowadzacyList;

    public Zajecia(String name,int roomNumber){
        this.name=name;
        this.roomNumber=roomNumber;
        this.prowadzacyList=new ArrayList<Prowadzacy>();
    }

    @Override
    public String toString(){
        return "data.Zajecia nazwa: " + name+" numer pokoju: "+roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Prowadzacy> getProwadzacyList() {
        return prowadzacyList;
    }

    public void setProwadzacyList(List<Prowadzacy> prowadzacyList) {
        this.prowadzacyList = prowadzacyList;
    }
}
