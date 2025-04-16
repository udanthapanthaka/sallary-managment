package lk.ijse.backend.controller;

import lk.ijse.backend.dto.LeaveDTO;
import lk.ijse.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
@CrossOrigin
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/getAll")
    public ResponseEntity<List<LeaveDTO>> getAllLeaves() {
        List<LeaveDTO> leaves = leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves); // 200 OK
    }

    @PostMapping("/save")
    public ResponseEntity<String> addLeave(@RequestBody LeaveDTO leaveDTO) {
        leaveService.saveLeave(leaveDTO);
        return ResponseEntity.status(201).body("Leave Saved Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLeave(@RequestBody LeaveDTO leaveDTO) {
        try {
            leaveService.updateLeave(leaveDTO);
            return ResponseEntity.ok("Leave Updated Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.ok("Leave Deleted Successfully");
    }
}
