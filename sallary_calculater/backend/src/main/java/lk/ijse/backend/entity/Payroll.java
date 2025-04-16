package lk.ijse.backend.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "base_salary", nullable = false)
    private Double baseSalary;

    @Column(name = "bonus")
    private Double bonus;

    @Column(name = "net_salary")
    private Double netSalary;

    @Column(name = "pay_date")
    private Date payDate;

    public Payroll(Long id, Employee employee, Double baseSalary, Double bonus, Double netSalary, Date payDate) {
        this.id = id;
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.netSalary = netSalary;
        this.payDate = payDate;
    }

    public Payroll() {
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
        return "Payroll{" +
                "id=" + id +
                ", employee=" + employee +
                ", baseSalary=" + baseSalary +
                ", bonus=" + bonus +
                ", netSalary=" + netSalary +
                ", payDate=" + payDate +
                '}';
    }
}
