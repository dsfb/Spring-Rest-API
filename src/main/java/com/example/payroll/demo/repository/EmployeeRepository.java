package com.example.payroll.demo.repository;

import com.example.payroll.demo.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public default boolean findByName(String name) {
        List<Employee> empList = this.findAll();
        for (Employee emp : empList) {
            if (emp.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
