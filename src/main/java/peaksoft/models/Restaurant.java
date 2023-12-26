package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
@Builder
@ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_gen")
    @SequenceGenerator(name = "r_gen", sequenceName = "r_seq", allocationSize = 1)
    private long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private String service;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<User> users;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<MenuItem> menuItems;

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRestType(String restType) {
        this.restType = restType;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = users.size();
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
