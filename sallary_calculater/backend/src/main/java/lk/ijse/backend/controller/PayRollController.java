package lk.ijse.backend.controller;

import lk.ijse.backend.dto.PayrollDTO;
import lk.ijse.backend.service.PayRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/payroll")
@CrossOrigin
public class PayRollController {

    @Autowired
    private PayRollService payRollService;

    @PostMapping("/calculate/{employeeId}")
    public ResponseEntity<PayrollDTO> calculateEmployeePayroll(@PathVariable Long employeeId) {
        PayrollDTO payrollDTO = payRollService.calculateAndSavePayroll(employeeId);
        return ResponseEntity.ok(payrollDTO);
    }

    @PostMapping("/calculate-monthly")
    public ResponseEntity<List<PayrollDTO>> calculateMonthlyPayroll() {
        List<PayrollDTO> payrollDTOs = payRollService.generateMonthlyPayroll();
        return ResponseEntity.ok(payrollDTOs);
    }

    @GetMapping("/{payrollId}")
    public ResponseEntity<PayrollDTO> getPayroll(@PathVariable Long payrollId) {
        PayrollDTO payrollDTO = payRollService.getPayrollById(payrollId);
        return ResponseEntity.ok(payrollDTO);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollDTO>> getEmployeePayrolls(
            @PathVariable Long employeeId,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) Integer year) {

        List<PayrollDTO> payrollDTOs = payRollService.getPayrollsByEmployee(employeeId);

        if (month != null && year != null) {
            payrollDTOs = payrollDTOs.stream()
                    .filter(p -> isPayrollInMonthYear(p, month, year))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(payrollDTOs);
    }

    private boolean isPayrollInMonthYear(PayrollDTO payroll, String month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(payroll.getPayDate());
        return cal.get(Calendar.MONTH) == Month.valueOf(month.toUpperCase()).getValue() &&
                cal.get(Calendar.YEAR) == year;
    }
}