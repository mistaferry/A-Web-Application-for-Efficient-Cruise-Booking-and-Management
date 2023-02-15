package model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Staff{
    private static final long serialVersionUID = 1L;
    private long id;
    private String firstName;
    private String surname;

    public Staff() {
    }

    public Staff(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Staff(long id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
