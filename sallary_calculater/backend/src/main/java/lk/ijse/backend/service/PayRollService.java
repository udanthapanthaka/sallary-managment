package lk.ijse.backend.service;

import lk.ijse.backend.dto.PayrollDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PayRollService {
    PayrollDTO calculateAndSavePayroll(Long employeeId);

    List<PayrollDTO> generateMonthlyPayroll();

    PayrollDTO getPayrollById(Long payrollId);

    List<PayrollDTO> getPayrollsByEmployee(Long employeeId);
}
