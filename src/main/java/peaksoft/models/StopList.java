package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stopLists")
@Builder
@ToString
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "st_gen" )
    @SequenceGenerator(name = "st_gen",sequenceName = "st_seq",allocationSize = 1)
    private long id;
    private String reason;
    private ZonedDateTime date;
    @OneToOne
    @JsonIgnore
    private MenuItem menuItem;
}
