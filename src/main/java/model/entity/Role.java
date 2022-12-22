package model.entity;

public enum Role{
    CLIENT, ADMIN, MANAGER;

    public static Role getRole(User user){
        int roleId = user.getRoleId();
        return Role.values()[--roleId];
    }

    public String getName(){
        return name().toLowerCase();
    }
}
