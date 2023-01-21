package model.entity;

public enum Role{
    CUSTOMER, ADMIN, MANAGER;

    public static Role getRoleByUser(User user){
        int roleId = user.getRoleId();
        return Role.values()[--roleId];
    }

    public static Role getRoleByRoleId(int id){
        return Role.values()[--id];
    }

    public String getName(){
        return name().toLowerCase();
    }
}
