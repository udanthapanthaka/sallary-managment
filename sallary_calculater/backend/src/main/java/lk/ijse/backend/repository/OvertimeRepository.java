package lk.ijse.backend.repository;

import lk.ijse.backend.entity.OverTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTime, Long> {
    List<OverTime> findByEmployeeId(Long employeeId);

    List<OverTime> findByEmployeeIdAndOvertimeDateBetween(Long employeeId, Date startDate, Date endDate);
}