package workplace.services;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import workplace.entities.Employee;
import workplace.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }


    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EMPLOYEE_NOT_FOUND"));
    }


}