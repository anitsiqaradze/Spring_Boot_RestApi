package workplace.services;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import workplace.dto.DepartmentInfo;
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

    public List<DepartmentInfo> getDepartmentSummaries(){
        return departmentRepository.findDepartmentSummaries();
    }

    
    
}
