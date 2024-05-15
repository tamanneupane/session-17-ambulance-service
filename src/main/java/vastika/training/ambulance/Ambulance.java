package vastika.training.ambulance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Table(name = "ambulance_tbl")
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ambulance_id")
    private Long ambulanceId;

    @Column(name = "vehicle_number")
    @Setter
    private String vehicleNumber;

    @Column(name = "latitude")
    @Setter
    private double latitude;

    @Column(name = "longitude")
    @Setter
    private double longitude;

    @Column(name = "hospital_name")
    @Setter
    private String hospitalName;

    @Column(name = "city")
    @Setter
    private String city;

    @Column(name = "availability")
    @Setter
    @JsonProperty(value = "available")
    private Boolean isAvailable;

}
