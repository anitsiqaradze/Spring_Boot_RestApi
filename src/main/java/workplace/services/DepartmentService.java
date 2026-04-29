package workplace.services;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import workplace.dto.DepartmentLocationInfo;
import workplace.entities.Department;
import workplace.repositories.DepartmentsRepository;

@Service

public class DepartmentService {

    private final DepartmentsRepository departmentRepository;

    public DepartmentService(DepartmentsRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

     public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) throws Exception {
        return  departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEPARTMENT_NOT_FOUND"));
    }

    public List<DepartmentLocationInfo> getDepartmentSummaries(){
        return departmentRepository.findDepartmentSummaries();
    }

    public List<Department> filterByCity(String city) {
        return departmentRepository.findByCity(city);
    }

    public List<Department> filterByCountry(String countryName) {
        return departmentRepository.findByCountryName(countryName);
    }
    
    
}
