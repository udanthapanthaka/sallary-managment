package lk.ijse.backend.controller;

import lk.ijse.backend.dto.AttendanceDTO;
import lk.ijse.backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/save")
    public ResponseEntity<String> addAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.saveAttendance(attendanceDTO);
        return ResponseEntity.status(201).body("Attendance saved successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.updateAttendance(attendanceDTO);
        return ResponseEntity.ok("Attendance updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> attendanceList = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceList);
    }
}
