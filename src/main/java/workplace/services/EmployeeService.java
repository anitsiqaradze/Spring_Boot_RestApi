package workplace.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import workplace.dto.AddEmployee;
import workplace.dto.EmployeeContactInfo;
import workplace.dto.EmployeeInfo;
import workplace.dto.Paging;
import workplace.dto.RequestData;
import workplace.dto.SearchEmployee;
import workplace.entities.Department;
import workplace.entities.Employee;
import workplace.repositories.EmployeeRepository;
import workplace.utilities.GeneralUtil;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }


    public List<EmployeeInfo> getAll() {
        return employeeRepository.findAllWithDetails();
    }

    public Employee getById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EMPLOYEE_NOT_FOUND"));
    }


    @Transactional
    public Employee saveEmployee(AddEmployee data, Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(data,  Arrays.asList("firstName", "lastName", "phone", "salary"));

        Employee employee = new Employee();

        if(id != null){
            employee = getById(id);
        }

        GeneralUtil.getCopyOf(data, employee);
        employee.setHireDate(new Date());

        Department department = this.departmentService.getById(data.getDepartmentId());
        employee.setDepartment(department);

        Employee manager = getById(data.getManagerId());
        employee.setManager(manager);
    

        return employeeRepository.save(employee);



    }

    public List<Employee> getByDepartment(Long departmentId){
        return employeeRepository.findAllByDepartment(departmentId);
    }

    
    public Page<Employee> search(SearchEmployee searchEmployee, Paging p){
        Pageable pageable = PageRequest.of(p.getPage() -1 , p.getSize(), Sort.by(Sort.Direction.ASC, "id"));
        String searchText = searchEmployee.getSearchText() != null ? "%" + searchEmployee.getSearchText()+"%":null;
        return employeeRepository.searchEmployees(searchText, pageable);
    }


    public EmployeeContactInfo getEmployeeContacts(SearchEmployee searchEmployee){
        return employeeRepository.findByPhoneOrEmail(searchEmployee.getPhone(), searchEmployee.getEmail(), EmployeeContactInfo.class);
    }

}