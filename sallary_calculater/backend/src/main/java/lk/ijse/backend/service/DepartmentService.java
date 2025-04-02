package lk.ijse.backend.service;

import lk.ijse.backend.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    void deleteDepartment(Long id);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);
}
