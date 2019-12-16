package team20.transport.ParcelDeliverySystem.CancelSent.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.Entity.Employee;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name="CANCELSENT")
public class Cancelsent {
    @Id
    @SequenceGenerator(name="CANCELSENT_SEQ",sequenceName="CANCELSENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CANCELSENT_SEQ")
    @Column(name = "CANCELSENT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CANCEL_PRICE")
    private @NonNull Long price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "STATUS_ID", insertable = true)
    @JsonManagedReference
    private Status onStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Howtopay.class)
    @JoinColumn(name = "HOWTOPAY_ID", insertable = true)
    @JsonManagedReference
    private Howtopay onHowtoPay;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Senttoback.class)
    @JoinColumn(name = "SENTTOBACK_ID", insertable = true)
    @JsonManagedReference
    private Senttoback onSenttoback;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonManagedReference
    private Employee createBy;

}