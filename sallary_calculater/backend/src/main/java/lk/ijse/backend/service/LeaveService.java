package lk.ijse.backend.service;

import lk.ijse.backend.dto.LeaveDTO;

import java.util.List;

public interface LeaveService {
    LeaveDTO saveLeave(LeaveDTO leaveDTO);

    List<LeaveDTO> getAllLeaves();

    LeaveDTO updateLeave(LeaveDTO leaveDTO);

    void deleteLeave(Long id);
}
