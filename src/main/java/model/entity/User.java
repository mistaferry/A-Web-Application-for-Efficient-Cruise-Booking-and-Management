package model.entity;

import java.util.HashSet;
import java.util.Set;

public class User extends Entity{
    private static final long serialVersionUID = 1L;
    private String login;
    private String password;
    private String firstName;
    private String surname;
    private int roleId;
    private boolean blocked;
    private Set<Cruise> cruises;

    public User(){
        this.cruises = new HashSet<Cruise>();
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

    public Set<Cruise> getCruises() {
        return cruises;
    }

    public void setCruises(Set<Cruise> cruises) {
        this.cruises = cruises;
    }
}
