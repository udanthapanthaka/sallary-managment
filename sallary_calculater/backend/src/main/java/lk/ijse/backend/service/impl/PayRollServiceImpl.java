package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.EmployeeDTO;
import lk.ijse.backend.dto.PayrollDTO;
import lk.ijse.backend.entity.Employee;
import lk.ijse.backend.entity.Payroll;
import lk.ijse.backend.repository.PayRollRepository;
import lk.ijse.backend.service.EmployeeService;
import lk.ijse.backend.service.PayRollService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayRollServiceImpl implements PayRollService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PayRollRepository payRollRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PayrollDTO calculateAndSavePayroll(Long employeeId) {
        // Get employee details
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        // Calculate payroll components
        Double baseSalary = employee.getSalary();
        Double otSalary = calculateOvertime(employeeId); // Implement this
        Double leaveDeductions = calculateLeaveDeductions(employeeId); // Implement this
        Double bonus = calculateBonus(employeeId); // Implement this

        // Calculate net salary
        Double netSalary = baseSalary + otSalary + bonus - leaveDeductions;

        // Create and save payroll
        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setBaseSalary(baseSalary);
        payroll.setBonus(bonus);
        payroll.setNetSalary(netSalary);
        payroll.setPayDate(new Date());

        Payroll savedPayroll = payRollRepository.save(payroll);
        return modelMapper.map(savedPayroll, PayrollDTO.class);
    }

    @Override
    public List<PayrollDTO> generateMonthlyPayroll() {
        List<PayrollDTO> payrollDTOs = new ArrayList<>();
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();

        for(EmployeeDTO employeeDTO : allEmployees) {
            PayrollDTO payrollDTO = calculateAndSavePayroll(employeeDTO.getId());
            payrollDTOs.add(payrollDTO);
        }

        return payrollDTOs;
    }

    @Override
    public PayrollDTO getPayrollById(Long payrollId) {
        Payroll payroll = payRollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));
        return modelMapper.map(payroll, PayrollDTO.class);
    }

    @Override
    public List<PayrollDTO> getPayrollsByEmployee(Long employeeId) {
        List<Payroll> payrolls = payRollRepository.findByEmployeeId(employeeId);
        return payrolls.stream()
                .map(payroll -> modelMapper.map(payroll, PayrollDTO.class))
                .collect(Collectors.toList());
    }

    // Helper methods
    private Double calculateOvertime(Long employeeId) {
        // Implement overtime calculation logic
        // Could query an overtime service or repository
        return 0.0; // Placeholder
    }

    private Double calculateLeaveDeductions(Long employeeId) {
        // Implement leave deduction logic
        // Could query a leave service or repository
        return 0.0; // Placeholder
    }

    private Double calculateBonus(Long employeeId) {
        // Implement bonus calculation logic
        // Could be based on performance, attendance, etc.
        return 0.0; // Placeholder
    }
}