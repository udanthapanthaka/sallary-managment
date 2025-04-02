package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.DepartmentDTO;
import lk.ijse.backend.entity.Department;
import lk.ijse.backend.repository.DepartmentRepository;
import lk.ijse.backend.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        departmentDTO.setId(null);
        departmentRepository.save(modelMapper.map(departmentDTO, Department.class));
        return departmentDTO;
//        try{
//            Department department = modelMapper.map(departmentDTO, Department.class);
//
//           Department department1 = departmentRepository.save(department);
//            return modelMapper.map(department1, DepartmentDTO.class);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
//        departmentDTO.setId(null);
//        if (departmentRepository.existsById(departmentDTO.getId())) {
//            Department department =modelMapper.map(departmentDTO, Department.class);
//            departmentRepository.save(department);
//        }else{
//            throw new RuntimeException("Department not found");
//        }
        if (departmentRepository.existsById(departmentDTO.getId())) {
            departmentRepository.save(modelMapper.map(departmentDTO, Department.class));
        }
        throw new RuntimeException("Department not found");
    }
}
