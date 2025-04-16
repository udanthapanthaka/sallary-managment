package lk.ijse.backend.dto;
import java.time.LocalTime;
import java.util.Date;

public class AttendanceDTO {

    private Long id;
    private Long employeeId;
    private Date attendanceDate;
    private LocalTime onTime;
    private LocalTime offTime;

    public AttendanceDTO() {
    }

    public AttendanceDTO(Long id, Long employeeId, Date attendanceDate, LocalTime onTime, LocalTime offTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
        this.onTime = onTime;
        this.offTime = offTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalTime getOnTime() {
        return onTime;
    }

    public void setOnTime(LocalTime onTime) {
        this.onTime = onTime;
    }

    public LocalTime getOffTime() {
        return offTime;
    }

    public void setOffTime(LocalTime offTime) {
        this.offTime = offTime;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", attendanceDate=" + attendanceDate +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                '}';
    }
}
