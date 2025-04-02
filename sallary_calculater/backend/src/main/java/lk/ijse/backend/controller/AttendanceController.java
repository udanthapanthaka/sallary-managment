package lk.ijse.backend.controller;

import lk.ijse.backend.dto.AttendanceDTO;
import lk.ijse.backend.repository.AttendanceRepository;
import lk.ijse.backend.service.AttendanceService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(path = "save")
    public ResponseUtil addAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.saveAttendance(attendanceDTO);
        return new ResponseUtil(201,"attendance saved successfully", null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updateAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.updateAttendance(attendanceDTO);
        return new ResponseUtil(201,"attendance updated successfully", null);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendance(id);
        return new ResponseUtil(201,"attendance deleted successfully", null);
    }

    @GetMapping
    public ResponseUtil getAllAttendance() {
        return new ResponseUtil(200,"attendance list retrieved successfully", attendanceService.getAllAttendance());
    }
}
