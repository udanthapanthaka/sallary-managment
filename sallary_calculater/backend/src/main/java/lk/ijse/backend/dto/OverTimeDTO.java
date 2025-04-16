package lk.ijse.backend.dto;

import java.util.Date;

public class OverTimeDTO {

    private String id;
    private Long employeeId;
    private Double overtimeHours;
    private Double overtimeRate;
    private Date overtimeDate;
    private Double totalOvertimePay;

    public OverTimeDTO() {
    }

    public OverTimeDTO(String id, Long employeeId, Double overtimeHours, Double overtimeRate, Date overtimeDate, Double totalOvertimePay) {
        this.id = id;
        this.employeeId = employeeId;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.overtimeDate = overtimeDate;
        this.totalOvertimePay = totalOvertimePay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public Double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(Double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Date getOvertimeDate() {
        return overtimeDate;
    }

    public void setOvertimeDate(Date overtimeDate) {
        this.overtimeDate = overtimeDate;
    }

    public Double getTotalOvertimePay() {
        return totalOvertimePay;
    }

    public void setTotalOvertimePay(Double totalOvertimePay) {
        this.totalOvertimePay = totalOvertimePay;
    }

    @Override
    public String toString() {
        return "OverTimeDTO{" +
                "id='" + id + '\'' +
                ", employeeId=" + employeeId +
                ", overtimeHours=" + overtimeHours +
                ", overtimeRate=" + overtimeRate +
                ", overtimeDate=" + overtimeDate +
                ", totalOvertimePay=" + totalOvertimePay +
                '}';
    }
}
