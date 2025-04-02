package lk.ijse.backend.dto;

import java.util.Date;

public class LeaveDTO {

    private Long id;
    private String employeeId;  // Storing only the employee ID instead of the full Employee object
    private String leaveType;
    private Date startDate;
    private Date endDate;

    public LeaveDTO() {
    }

    public LeaveDTO(Long id, String employeeId, String leaveType, Date startDate, Date endDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LeaveDTO{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
