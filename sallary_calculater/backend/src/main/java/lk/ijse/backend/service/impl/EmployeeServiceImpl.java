package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.EmployeeDTO;
import lk.ijse.backend.entity.Employee;
import lk.ijse.backend.repository.EmployeeRepository;
import lk.ijse.backend.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId(null);
        employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
        return employeeDTO;
    }
    @Override
    public void deleteEmployee(Long id) {
     employeeRepository.deleteById(id);
    }


    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(employeeDTO.getId())) {
            employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
            return employeeDTO;
        }
        throw new RuntimeException("Employee not found");
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        return null;
    }
}
