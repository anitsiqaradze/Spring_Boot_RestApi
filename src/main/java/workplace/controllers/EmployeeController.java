package workplace.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import workplace.dto.AddEmployee;
import workplace.dto.EmployeeContactInfo;
import workplace.dto.RequestData;
import workplace.dto.SearchEmployee;
import workplace.entities.Employee;
import workplace.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) throws Exception {
        return employeeService.getById(id);
    }

    @PostMapping("/add")
    public Employee add(@RequestBody AddEmployee addEmployee) throws Exception {
        return employeeService.saveEmployee(addEmployee, null);
    }

    @PutMapping("/{id}")
    public Employee edit(@PathVariable Long id, @RequestBody AddEmployee addEmployee) throws Exception{
        return employeeService.saveEmployee(addEmployee, id);

    }

   @PostMapping("/search")
    public Page<Employee> search(@RequestBody RequestData<SearchEmployee> rd) {
        return employeeService.search(rd.getData(), rd.getPaging());
    }

    @PostMapping("/searchContactInfo")
    public EmployeeContactInfo searchContactInfo(@RequestBody RequestData<SearchEmployee> rd){
        return employeeService.getEmployeeContacts(rd.getData());
    }


    
    


}
