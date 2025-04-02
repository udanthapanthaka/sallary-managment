package lk.ijse.backend.dto;

import java.util.Date;

public class PayrollDTO {

    private String id;
    private String employeeId;  // Storing only the employee ID instead of the full Employee object
    private Double baseSalary;
    private Double bonus;
    private Double netSalary;
    private Date payDate;

    public PayrollDTO() {
    }

    public PayrollDTO(String id, String employeeId, Double baseSalary, Double bonus, Double netSalary, Date payDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.netSalary = netSalary;
        this.payDate = payDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "PayrollDTO{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", baseSalary=" + baseSalary +
                ", bonus=" + bonus +
                ", netSalary=" + netSalary +
                ", payDate=" + payDate +
                '}';
    }
}
