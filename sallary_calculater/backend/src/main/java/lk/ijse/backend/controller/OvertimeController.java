package lk.ijse.backend.controller;

import lk.ijse.backend.dto.OverTimeDTO;
import lk.ijse.backend.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/overtime")
@CrossOrigin
public class OvertimeController {

    @Autowired
    private OvertimeService overtimeService;

    @PostMapping("/save")
    public ResponseEntity<OverTimeDTO> saveOvertime(@RequestBody OverTimeDTO overtimeDTO) {
        OverTimeDTO savedOvertime = overtimeService.saveOvertime(overtimeDTO);
        return ResponseEntity.status(201).body(savedOvertime);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OverTimeDTO>> getAllOvertime() {
        List<OverTimeDTO> overtimeList = overtimeService.getAllOvertime();
        return ResponseEntity.ok(overtimeList);
    }

    @PutMapping("/update")
    public ResponseEntity<OverTimeDTO> updateOvertime(@RequestBody OverTimeDTO overtimeDTO) {
        OverTimeDTO updatedOvertime = overtimeService.updateOvertime(overtimeDTO);
        return ResponseEntity.ok(updatedOvertime);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOvertime(@PathVariable Long id) {
        overtimeService.deleteOvertime(id);
        return ResponseEntity.ok("Overtime record deleted successfully");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<OverTimeDTO>> getOvertimeByEmployee(@PathVariable Long employeeId) {
        List<OverTimeDTO> overtimeList = overtimeService.getOvertimeByEmployee(employeeId);
        return ResponseEntity.ok(overtimeList);
    }


    @GetMapping("/employee/{employeeId}/totalPay")
    public ResponseEntity<Map<String, Double>> getTotalOvertimePay(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        Double totalPay = overtimeService.calculateTotalOvertimePayForEmployee(
                employeeId, startDate, endDate);

        return ResponseEntity.ok(Map.of("totalOvertimePay", totalPay));
    }
}