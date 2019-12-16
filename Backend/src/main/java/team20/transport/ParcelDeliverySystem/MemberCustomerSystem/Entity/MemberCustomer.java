package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="MEMBER_CUSTOMER")
public class MemberCustomer {

    @Id
    @SequenceGenerator(name="MEMBER_CUSTOMER_SEQ",sequenceName="MEMBER_CUSTOMER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_CUSTOMER_SEQ")
    @Column(name="MEMBER_CUSTOMER_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private String MemName;
    private long Tel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee CreateBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberType.class)
    @JoinColumn(name = "MEMBER_TYPE_ID", insertable = true)
    private MemberType MemberType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberLevel.class)
    @JoinColumn(name = "MEMBER_LEVEL_ID", insertable = true)
    private MemberLevel MemberLevel;

 //

}