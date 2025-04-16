package lk.ijse.backend.repository;

import lk.ijse.backend.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PayRollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);

    // Optional: Query for specific month/year
    @Query("SELECT p FROM Payroll p WHERE p.employee.id = :employeeId " +
            "AND MONTH(p.payDate) = :month AND YEAR(p.payDate) = :year")
    List<Payroll> findByEmployeeAndMonthYear(
            @Param("employeeId") Long employeeId,
            @Param("month") int month,
            @Param("year") int year);
}