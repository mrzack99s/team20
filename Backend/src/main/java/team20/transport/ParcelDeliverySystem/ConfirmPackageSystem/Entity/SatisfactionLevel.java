package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.*;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;

@Data
@Entity
@NoArgsConstructor
@Table(name="SATISFACTIONLEVEL")
public class SatisfactionLevel {
    @Id
    @SequenceGenerator(name="SATISFACTIONLEVEL_SEQ",sequenceName="SATISFACTIONLEVEL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SATISFACTIONLEVEL_SEQ")
    @Column(name="SATISFACTIONLEVEL_ID",unique = true, nullable = true)
    private @NonNull Long satisfactionlevel_id;

    private @NonNull String satisfactionlevel_name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ConfirmPackage.class)
    @JoinColumn(name = "CONFIRM_PACKGAGE_ID")
    @JsonManagedReference
    private Collection<ConfirmPackage> confirm;

}