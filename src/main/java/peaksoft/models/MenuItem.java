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
@Table(name = "menuItems")
@Builder
@ToString
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_gen" )
    @SequenceGenerator(name = "m_gen",sequenceName = "m_seq",allocationSize = 1)
    private long id;
    private String name;
    private String image;
    private double price;
    private String description;
    private boolean isVegetarian;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Category category;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private StopList stopList;
    @ManyToMany(mappedBy = "menuItems", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Cheque>cheques;
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
}
