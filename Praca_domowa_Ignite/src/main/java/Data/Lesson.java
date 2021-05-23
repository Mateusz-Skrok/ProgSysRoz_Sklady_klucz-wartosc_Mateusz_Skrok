package Data;

import java.io.Serializable;

public class Lesson implements Serializable {
    private String name;
    private int grade;
    private int roomNumber;
    private String teacher;

    public Lesson(String name,int roomNumber,String teacher){
        this.name=name;
        this.roomNumber=roomNumber;
        this.teacher=teacher;
    }

    @Override
    public String toString(){
        return "Zajecia nazwa: " + name+" numer pokoju: "+roomNumber+" prowadzacy: "+teacher;
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


}
