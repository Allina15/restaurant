package peaksoft.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subcategories")
@Builder
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_gen" )
    @SequenceGenerator(name = "s_gen",sequenceName = "s_seq",allocationSize = 1)
    private long id;
    private String name;
    @ManyToOne
    @JsonIgnore
    private Category category;
}
