package lk.ijse.backend.entity;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "attendance_date", nullable = false)
    private Date attendanceDate;

    @Column(name = "on_time")
    private LocalTime onTime;

    @Column(name = "off_time")
    private LocalTime offTime;

    public Attendance() {

    }
    public Attendance(Long id, Employee employee, Date attendanceDate, LocalTime onTime, LocalTime offTime) {
        this.id = id;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        return "Attendance{" +
                "id=" + id +
                ", employee=" + employee +
                ", attendanceDate=" + attendanceDate +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                '}';
    }
}
