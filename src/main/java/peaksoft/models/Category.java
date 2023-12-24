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
@Table(name = "categories")
@Builder
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c_gen" )
    @SequenceGenerator(name = "c_gen",sequenceName = "c_seq",allocationSize = 1)
    private long id;
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<SubCategory>subCategories;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<MenuItem>menuItems;
}
