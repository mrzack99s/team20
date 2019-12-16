package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="EMPOLYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPOLYEE_SEQ",sequenceName="EMPOLYEE_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPOLYEE_SEQ")
    @Column(name="EMPOLYEE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ShippingState.class)
    @JoinColumn(name = "SHIPPINGSTATE_ID")
    @JsonBackReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberCustomer.class)
    @JoinColumn(name = "MEMBER_CUSTOMER_ID")
    @JsonBackReference
    private Collection<MemberCustomer> RegisterMember;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ConfirmPackage.class)
    @JoinColumn(name = "CONFIRM_PACKGAGE_ID")
    @JsonBackReference
    private Collection<ConfirmPackage> confirm;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGE_ID")
    @JsonBackReference
    private Collection<Packaging> send;
}