package model.entity;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class User extends Entity{
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String surname;
    private int roleId;
    private boolean blocked;
//    private Set<Cruise> cruises;

    public User(){
//        this.cruises = new HashSet<Cruise>();
    }

    public User(int id, String login, String password, String firstName, String surname, int roleId, boolean blocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.roleId = roleId;
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

//    public Set<Cruise> getCruises() {
//        return cruises;
//    }

//    public void setCruises(Set<Cruise> cruises) {
//        this.cruises = cruises;
//    }
}
