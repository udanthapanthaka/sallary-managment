package lk.ijse.backend.dto;

import java.util.Set;

public class DepartmentDTO {

    private Long id;
    private String departmentName;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


}
