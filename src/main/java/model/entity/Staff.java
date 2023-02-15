package model.entity;

public class Staff extends Entity{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String surname;

    public Staff() {
    }

    public Staff(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Staff(long id, String firstName, String surname) {
        super(id);
        this.firstName = firstName;
        this.surname = surname;
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
