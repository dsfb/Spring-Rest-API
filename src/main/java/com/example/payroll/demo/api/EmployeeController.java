package com.example.payroll.demo.api;

import com.example.payroll.demo.exception.EmployeeExistentException;
import com.example.payroll.demo.exception.EmployeeIncompleteException;
import com.example.payroll.demo.exception.EmployeeNotFoundException;
import com.example.payroll.demo.model.Employee;
import com.example.payroll.demo.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@Api(value = "Employees API REST")
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // recuperar informação da url
    @ApiOperation(value = "Recupera informações da url",
            response= Employee.class,
            notes= "Recupera todas as informações da url")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tudo certo", response = Employee.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = Employee.class)
        }
    )
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    // inserir um employee
    @ApiOperation(value = "Insere um employee",
            response= Employee.class,
            notes= "Insere um novo employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tudo certo", response = Employee.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = Employee.class)
        }
    )
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        if (newEmployee.checkComplete()) {
            if (repository.findByName(newEmployee.getName())) {
                throw new EmployeeExistentException();
            }

            return repository.save(newEmployee);
        } 

        throw new EmployeeIncompleteException();
    }

    // itens individuais

    // recuperar informação de um employee específico, /employees/{tal}
    @ApiOperation("Recupera informação de um employee especifíco")
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // alterar informações de um employee específico
    @ApiOperation("Altera informações de um employee específico")
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        if (!newEmployee.checkComplete()) {
            throw new EmployeeIncompleteException();
        }

        // caso encontre o id pega o valor de name e role e atualiza salvando os dados do emplooye,
        // senão ele cria um novo employee.
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        })
        .orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    // método sem retorno que executa a ação de apagar o employee específico
    @ApiOperation("Deleta um employee")
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

}
