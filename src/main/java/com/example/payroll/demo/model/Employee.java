package com.example.payroll.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    private @Id @GeneratedValue Long id;
    private String name;
    private String role; 
    
    //
    public Employee() {}

    // Boa prática: construtor com no máximo 5/6 atributos
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public boolean checkComplete() {
        return new EmployeeChecker().isComplete(this);
    }
    
    public class EmployeeChecker {
        public boolean isComplete(Employee emp) {
            if (emp.name == null || emp.name.isEmpty()) {
                return false;
            }

            if (emp.role == null || emp.role.isEmpty()) {
                return false;
            }

            return true;
        }
    }
}
