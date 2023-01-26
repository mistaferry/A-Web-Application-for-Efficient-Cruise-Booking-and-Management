package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class City extends Entity{
    private static final long serialVersionUID = 1L;
    private String name;
    private String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public City() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
