package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cheques")
@Builder
@ToString
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ch_gen" )
    @SequenceGenerator(name = "ch_gen",sequenceName = "ch_seq",allocationSize = 1)
    private long id;
    private double priceAverage;
    private ZonedDateTime createdAt;
    @ManyToMany
    @JsonIgnore
    private List<MenuItem>menuItems;
    @JsonIgnore
    @ManyToOne
    private User user;
}
