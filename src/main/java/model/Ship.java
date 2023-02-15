package model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Ship{
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private int numberOfPorts;
    private List<Staff> staff;
    private List<City> route;
    private int capacity;

    public Ship() {
    }

    public Ship(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ship(String name, int numberOfPorts, List<Staff> staff, List<City> route, int capacity) {
        this.name = name;
        this.numberOfPorts = numberOfPorts;
        this.staff = staff;
        this.route = route;
        this.capacity = capacity;
    }

    public Ship(long id, String name, int numberOfPorts, List<Staff> staff, List<City> route, int capacity) {
        this.id = id;
        this.name = name;
        this.numberOfPorts = numberOfPorts;
        this.staff = staff;
        this.route = route;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
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
