package lk.ijse.backend.service;

import lk.ijse.backend.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(String id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();
}
