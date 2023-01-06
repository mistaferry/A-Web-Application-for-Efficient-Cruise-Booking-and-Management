package model.entity;

import java.util.List;
import java.util.Set;

public class Ship extends Entity{
    private static final long serialVersionUID = 1L;
    private String name;
    private int numberOfPorts;
    private Set<Staff> staff;
    private List<City> route;
    private int capacity;

    public Ship() {
    }

//    public Ship(long id, String name, int numberOfPorts, Set<Staff> staff, List<City> route, int capacity) {
//        super(id);
//        this.name = name;
//        this.numberOfPorts = numberOfPorts;
//        this.staff = staff;
//        this.route = route;
//        this.capacity = capacity;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public List<City> getRoute() {
        return route;
    }

    public void setRoute(List<City> route) {
        this.route = route;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }
}
