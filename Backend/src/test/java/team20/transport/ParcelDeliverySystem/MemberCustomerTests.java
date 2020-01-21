package team20.transport.ParcelDeliverySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;

import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberCustomerRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberTypeRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberLevelRepository;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MemberCustomerTests {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
    @Autowired
    MemberLevelRepository memberLevelRepository;
    @Autowired
    MemberCustomerRepository memberCustomerRepository;

    Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6011642_testCorrectDataInput(){

        Employee employee = new Employee();
        employee.setName("B6011642");
        employee.setEmail("B6011642@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        MemberType memberType = new MemberType();
        memberType.setType("Business");
        memberType = memberTypeRepository.saveAndFlush(memberType);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Premium");
        memberLevel = memberLevelRepository.saveAndFlush(memberLevel);


        MemberCustomer memberCustomer = new MemberCustomer();
        Date tim = new Date();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setRegDate(tim);
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

        MemberCustomer checkFoundData = memberCustomerRepository.findById(memberCustomer.getId()).get();

        assertEquals("mem Test",checkFoundData.getMemName());
        assertEquals("0987654321",checkFoundData.getTel());
        assertEquals(employee,checkFoundData.getCreateBy());
        assertEquals(tim,checkFoundData.getRegDate());
        assertEquals(memberType,checkFoundData.getMemberType());
        assertEquals(memberLevel,checkFoundData.getMemberLevel());

    }

    @Test
    void b6011642_testMemNameMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6011642");
        employee.setEmail("B6011642@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        MemberType memberType = new MemberType();
        memberType.setType("Business");
        memberType = memberTypeRepository.saveAndFlush(memberType);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Premium");
        memberLevel = memberLevelRepository.saveAndFlush(memberLevel);


        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName(null);
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);

        Set<ConstraintViolation<MemberCustomer>> result = validator.validate(memberCustomer);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MemberCustomer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("MemName", v.getPropertyPath().toString()); 
    }

    @Test
    void b6011642_testTelMustBeNumberOnly(){

        Employee employee = new Employee();
        employee.setName("B6011642");
        employee.setEmail("B6011642@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        MemberType memberType = new MemberType();
        memberType.setType("Business");
        memberType = memberTypeRepository.saveAndFlush(memberType);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Premium");
        memberLevel = memberLevelRepository.saveAndFlush(memberLevel);


        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("09876x4321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);

        Set<ConstraintViolation<MemberCustomer>> result = validator.validate(memberCustomer);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MemberCustomer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("Tel", v.getPropertyPath().toString());

    }

    @Test
    void b6011642_testTelMustLenght10(){


        Employee employee = new Employee();
        employee.setName("B6011642");
        employee.setEmail("B6011642@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        MemberType memberType = new MemberType();
        memberType.setType("Business");
        memberType = memberTypeRepository.saveAndFlush(memberType);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Premium");
        memberLevel = memberLevelRepository.saveAndFlush(memberLevel);


        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("098765432");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);

        Set<ConstraintViolation<MemberCustomer>> result = validator.validate(memberCustomer);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MemberCustomer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("Tel", v.getPropertyPath().toString());
    }

    @Test
    void b6011642_testEmailMustBeEmailForm(){

        Employee employee = new Employee();
        employee.setName("B6011642");
        employee.setEmail("B6011642@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        MemberType memberType = new MemberType();
        memberType.setType("Business");
        memberType = memberTypeRepository.saveAndFlush(memberType);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Premium");
        memberLevel = memberLevelRepository.saveAndFlush(memberLevel);


        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("ccgmail.com");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);

        Set<ConstraintViolation<MemberCustomer>> result = validator.validate(memberCustomer);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MemberCustomer> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString()); 
    }


}