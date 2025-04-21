package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.OverTimeDTO;
import lk.ijse.backend.entity.Employee;
import lk.ijse.backend.entity.OverTime;
import lk.ijse.backend.repository.EmployeeRepository;
import lk.ijse.backend.repository.OvertimeRepository;
import lk.ijse.backend.service.OvertimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeRepository overtimeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OverTimeDTO saveOvertime(OverTimeDTO overtimeDTO) {
        overtimeDTO.setId(null);

        if (overtimeDTO.getTotalOvertimePay() == null || overtimeDTO.getTotalOvertimePay() == 0) {
            Double totalPay = overtimeDTO.getOvertimeHours() * overtimeDTO.getOvertimeRate();
            overtimeDTO.setTotalOvertimePay(totalPay);
        }

        OverTime overtime = modelMapper.map(overtimeDTO, OverTime.class);

        if (overtimeDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(overtimeDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + overtimeDTO.getEmployeeId()));
            overtime.setEmployee(employee);
        }

        OverTime savedOvertime = overtimeRepository.save(overtime);
        OverTimeDTO savedOvertimeDTO = modelMapper.map(savedOvertime, OverTimeDTO.class);
        savedOvertimeDTO.setEmployeeId(savedOvertime.getEmployee().getId());

        return savedOvertimeDTO;
    }

    @Override
    public List<OverTimeDTO> getAllOvertime() {
        List<OverTime> overtimeList = overtimeRepository.findAll();
        List<OverTimeDTO> overtimeDTOList = new ArrayList<>();

        for (OverTime overtime : overtimeList) {
            OverTimeDTO overtimeDTO = modelMapper.map(overtime, OverTimeDTO.class);
            overtimeDTO.setEmployeeId(overtime.getEmployee().getId());
            overtimeDTOList.add(overtimeDTO);
        }

        return overtimeDTOList;
    }

    @Override
    public OverTimeDTO updateOvertime(OverTimeDTO overtimeDTO) {
        if (overtimeRepository.existsById(Long.valueOf(overtimeDTO.getId()))) {
            if (overtimeDTO.getTotalOvertimePay() == null || overtimeDTO.getTotalOvertimePay() == 0) {
                Double totalPay = overtimeDTO.getOvertimeHours() * overtimeDTO.getOvertimeRate();
                overtimeDTO.setTotalOvertimePay(totalPay);
            }


            OverTime overtime = modelMapper.map(overtimeDTO, OverTime.class);

            if (overtimeDTO.getEmployeeId() != null) {
                Employee employee = employeeRepository.findById(overtimeDTO.getEmployeeId())
                        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + overtimeDTO.getEmployeeId()));
                overtime.setEmployee(employee);
            }

            OverTime updatedOvertime = overtimeRepository.save(overtime);
            OverTimeDTO updatedOvertimeDTO = modelMapper.map(updatedOvertime, OverTimeDTO.class);
            updatedOvertimeDTO.setEmployeeId(updatedOvertime.getEmployee().getId());

            return updatedOvertimeDTO;
        }
        throw new RuntimeException("Overtime record not found with id: " + overtimeDTO.getId());
    }

    @Override
    public void deleteOvertime(Long id) {
        overtimeRepository.deleteById(id);
    }

    @Override
    public List<OverTimeDTO> getOvertimeByEmployee(Long employeeId) {
        List<OverTime> overtimeList = overtimeRepository.findByEmployeeId(employeeId);
        List<OverTimeDTO> overtimeDTOList = new ArrayList<>();

        for (OverTime overtime : overtimeList) {
            OverTimeDTO overtimeDTO = modelMapper.map(overtime, OverTimeDTO.class);
            overtimeDTO.setEmployeeId(overtime.getEmployee().getId());
            overtimeDTOList.add(overtimeDTO);
        }

        return overtimeDTOList;
    }

    @Override
    public Double calculateTotalOvertimePayForEmployee(Long employeeId, Date startDate, Date endDate) {
        List<OverTime> overtimeList = overtimeRepository.findByEmployeeIdAndOvertimeDateBetween(
                employeeId, startDate, endDate);

        return overtimeList.stream()
                .mapToDouble(OverTime::getTotalOvertimePay)
                .sum();
    }
}