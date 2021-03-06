package team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="SENTTOBACK")
public class Senttoback {
    @Id
    @SequenceGenerator(name="SENTTOBACK_SEQ",sequenceName="SENTTOBACK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTTOBACK_SEQ")
    @Column(name="SENTTOBACK_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="SENTTOBACK_NAME")
    private @NonNull String name;

    @OneToMany(orphanRemoval = true, mappedBy = "onSenttoback")
    @JsonManagedReference
    private Collection<Cancelsent> haveCancelSenttoBack;

}