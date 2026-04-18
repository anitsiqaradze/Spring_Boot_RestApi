package workplace.services;


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


    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EMPLOYEE_NOT_FOUND"));
    }

    @Transactional
    public Employee saveEmployee(Employee data, Long id) throws Exception {
        //GeneralUtil.checkRequiredProperties(data, Arrays.asList("firstName", "lastName", "phone", "salary"));
        //Employee employee = new Employee();

        
        if (id != null) {
            data = getById(id);
        }

       // GeneralUtil.getCopyOf(data, employee);
        data.setHireDate(new Date());

        Department department = this.departmentService.getById(1L);

        data.setDepartment(department);

        return employeeRepository.save(data);
    }

    public List<Employee> getByDepartment(Long departmentId){
        return employeeRepository.findAllByDepartment(departmentId);
    }

    
    public Page<Employee> search(SearchEmployee searchEmployee, Paging p){
        Pageable pageable = PageRequest.of(p.getPage() -1 , p.getSize(), Sort.by(Sort.Direction.ASC, "id"));
        String searchText = searchEmployee.getSearchText() != null ? "%" + searchEmployee.getSearchText()+"%":null;
        return employeeRepository.searchEmployees(searchText, pageable);
    }



}