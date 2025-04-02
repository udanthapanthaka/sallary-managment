package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.AttendanceDTO;
import lk.ijse.backend.entity.Attendance;
import lk.ijse.backend.repository.AttendanceRepository;
import lk.ijse.backend.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AttendanceDTO saveAttendance(AttendanceDTO attendanceDTO) {
        attendanceDTO.setId(null);//balnna aye
        attendanceRepository.save(modelMapper.map(attendanceDTO , Attendance.class));
        return attendanceDTO;
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        List<Attendance> attendanceList = attendanceRepository.findAll();
        List<AttendanceDTO> attendanceDTOList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            AttendanceDTO attendanceDTO = modelMapper.map(attendance, AttendanceDTO.class);
            attendanceDTOList.add(attendanceDTO);

        }
        return attendanceDTOList;
    }


    @Override
    public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO) {
        if (attendanceRepository.existsById(attendanceDTO.getId())) {
            attendanceRepository.save(modelMapper.map(attendanceDTO , Attendance.class));

        }
        throw new RuntimeException("Attendance not found");
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

}
