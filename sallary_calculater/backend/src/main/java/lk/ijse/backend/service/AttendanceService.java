package lk.ijse.backend.service;

import lk.ijse.backend.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceService {
    AttendanceDTO saveAttendance(AttendanceDTO attendanceDTO);

    List<AttendanceDTO> getAllAttendance();

    AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO);

    void deleteAttendance(Long id);
}
