package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
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
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<User> users;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<MenuItem> menuItems;

    public String hasVacancy() {
        if (users.size() < 15) {
            numberOfEmployees = users.size();
            return String.valueOf(numberOfEmployees);
        } else {
            return "Нет вакансий в ресторане";
        }
    }
}
