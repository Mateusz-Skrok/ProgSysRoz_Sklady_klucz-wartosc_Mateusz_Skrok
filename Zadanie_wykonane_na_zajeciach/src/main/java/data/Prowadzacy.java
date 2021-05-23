package data;

import java.io.Serializable;

public class Prowadzacy implements Serializable {

    private static final long serialVersionUID = 3L;
    private String name;
    private String surname;
    private String pHD;

    public Prowadzacy(String name, String surname, String pHD){
        this.name=name;
        this.surname=surname;
        this.pHD=pHD;
    }

    @Override
    public String toString(){
        return "data.Prowadzacy imie: " + name + " nazwisko: "+surname+" tytuł naukowy: "+ pHD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getpHD() {
        return pHD;
    }

    public void setpHD(String pHD) {
        this.pHD = pHD;
    }
}
