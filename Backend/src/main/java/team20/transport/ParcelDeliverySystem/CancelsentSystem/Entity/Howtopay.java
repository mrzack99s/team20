package team20.transport.ParcelDeliverySystem.CancelSent.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="HOWTOPAY")
public class Howtopay {
    @Id
    @SequenceGenerator(name="HOWTOPAY_SEQ",sequenceName="HOWTOPAY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HOWTOPAY_SEQ")
    @Column(name="HOWTOPAY_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="HOWTOPAY_NAME")
    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Cancelsent.class)
    @JoinColumn(name = "CANCELSENT_ID")
    @JsonBackReference
    private Collection<Cancelsent> haveCancelHowtopay;
}