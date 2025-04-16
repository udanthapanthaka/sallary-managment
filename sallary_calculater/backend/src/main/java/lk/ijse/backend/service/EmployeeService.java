package lk.ijse.backend.service;

import lk.ijse.backend.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);//from payroll
}
