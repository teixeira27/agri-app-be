package org.acme.repository;

import org.acme.domain.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Integer> {
    List<Culture> findByLand_LandId(Integer landId);
}
