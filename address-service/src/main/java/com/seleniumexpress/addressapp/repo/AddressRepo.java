package com.seleniumexpress.addressapp.repo;

import com.seleniumexpress.addressapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepo extends JpaRepository<Address,Integer> {
//address based on employee id
@Query(value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state, ea.zip " +
        "FROM address ea " +
        "JOIN employe e ON ea.employee_id = e.id " +
        "WHERE ea.employee_id = :employeeId", nativeQuery = true)
Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
