package lk.ijse.backend.controller;

import lk.ijse.backend.dto.EmployeeDTO;
import lk.ijse.backend.service.EmployeeService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "save")
    public ResponseUtil saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        return new ResponseUtil(201, "employee saved successfully", null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO);
        return new ResponseUtil(201, "employee updated successfully", null);
    }
    @GetMapping(path = "getAll")
    public ResponseUtil getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        return new ResponseUtil(200, "employee list", employeeDTOList);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
    }
}
