package objects;

import java.util.Date;

public class Person {

    private String name;
    private String lastName;
    private String patronymicName;
    private Date birthDate;

    public Person() {
    }

    public Person(String name, String lastName, String patronymicName, Date birthDate) {
//        super для чего?
        super();
        this.name = name;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
