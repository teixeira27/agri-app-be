package org.acme.repository;

import org.acme.domain.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {

    List<StatusHistory> findByCulture_CultureId(Integer cultureId);
}
