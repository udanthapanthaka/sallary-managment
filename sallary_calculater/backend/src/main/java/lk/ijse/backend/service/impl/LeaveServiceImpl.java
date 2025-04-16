package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.LeaveDTO;
import lk.ijse.backend.entity.Leave;
import lk.ijse.backend.repository.LeaveRepository;
import lk.ijse.backend.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LeaveDTO saveLeave(LeaveDTO leaveDTO) {
        leaveDTO.setId(null);
        leaveRepository.save(modelMapper.map(leaveDTO, Leave.class));
        return leaveDTO;
    }

    @Override
    public List<LeaveDTO> getAllLeaves() {
        List<Leave> leaves = leaveRepository.findAll();
        List<LeaveDTO> leaveDTOs = new ArrayList<>();
        for (Leave leave : leaves) {
            LeaveDTO leaveDTO = modelMapper.map(leave, LeaveDTO.class);
            leaveDTOs.add(leaveDTO);
        }
        return leaveDTOs;
    }

    @Override
    public LeaveDTO updateLeave(LeaveDTO leaveDTO) {
        if (leaveRepository.existsById(leaveDTO.getId())) {
            leaveRepository.save(modelMapper.map(leaveDTO, Leave.class));
            return leaveDTO;
        }
        throw new RuntimeException("Leave not found");
    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}
