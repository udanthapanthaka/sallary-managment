package lk.ijse.backend.controller;

import lk.ijse.backend.dto.DepartmentDTO;
import lk.ijse.backend.service.DepartmentService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "getAll")
    public ResponseUtil getAllDepartments() {
        return new ResponseUtil(200, "Department List", departmentService.getAllDepartments());
    }

    @PostMapping(path = "save")
    public ResponseUtil addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.saveDepartment(departmentDTO);
        return new ResponseUtil(201, "Department Saved Successfully", null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(departmentDTO);
        return new ResponseUtil(201, "Department Updated Successfully", null);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseUtil(201, "Department Deleted Successfully", null);
    }
}
