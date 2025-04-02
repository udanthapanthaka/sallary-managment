package lk.ijse.backend.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "overtime")
public class OverTime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Generates a unique UUID
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "overtime_hours", nullable = false)
    private Double overtimeHours;

    @Column(name = "overtime_rate", nullable = false)
    private Double overtimeRate;

    @Column(name = "overtime_date", nullable = false)
    private Date overtimeDate;

    @Column(name = "total_overtime_pay")
    private Double totalOvertimePay; // Auto-calculated overtime pay

    public OverTime(String id, Employee employee, Double overtimeHours, Double overtimeRate, Date overtimeDate, Double totalOvertimePay) {
        this.id = id;
        this.employee = employee;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.overtimeDate = overtimeDate;
        this.totalOvertimePay = totalOvertimePay;
    }

    public OverTime() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        return "OverTime{" +
                "id='" + id + '\'' +
                ", employee=" + employee +
                ", overtimeHours=" + overtimeHours +
                ", overtimeRate=" + overtimeRate +
                ", overtimeDate=" + overtimeDate +
                ", totalOvertimePay=" + totalOvertimePay +
                '}';
    }
}
