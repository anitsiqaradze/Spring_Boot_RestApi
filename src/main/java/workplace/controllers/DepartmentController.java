package workplace.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import workplace.dto.DepartmentLocationInfo;
import workplace.entities.Department;
import workplace.entities.Employee;
import workplace.services.DepartmentService;
import workplace.services.EmployeeService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {


    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService){
        this.departmentService = departmentService;
        this.employeeService = employeeService;

    }


    @GetMapping("/all")
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/summary")
    public List<DepartmentLocationInfo> getDepartmentSummaries(){
        return departmentService.getDepartmentSummaries();
    }


    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) throws Exception {
        return departmentService.getById(id);
    }

   
    
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable Long id) {
        return employeeService.getByDepartment(id);
    }

    // example url /departments/filter?city=London
    @GetMapping("/filter")
    public List<Department> filter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {

        if (city != null) {
            return departmentService.filterByCity(city);
        } else if (country != null) {
            return departmentService.filterByCountry(country);
        }
        return List.of();
    }
}
