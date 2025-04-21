package lk.ijse.backend.service;

import lk.ijse.backend.dto.OverTimeDTO;

import java.util.Date;
import java.util.List;

public interface OvertimeService {
    OverTimeDTO saveOvertime(OverTimeDTO overtimeDTO);

    List<OverTimeDTO> getAllOvertime();

    OverTimeDTO updateOvertime(OverTimeDTO overtimeDTO);

    void deleteOvertime(Long id);

    List<OverTimeDTO> getOvertimeByEmployee(Long employeeId);

//    List<OverTimeDTO> getOvertimeByEmployeeAndDateRange(Long employeeId, Date startDate, Date endDate);

    Double calculateTotalOvertimePayForEmployee(Long employeeId, Date startDate, Date endDate);
}