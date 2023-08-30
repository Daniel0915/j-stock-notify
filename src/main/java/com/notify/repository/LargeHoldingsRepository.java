package com.notify.repository;

import com.notify.entity.LargeHoldingsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeHoldingsRepository extends JpaRepository<LargeHoldingsEntity, Long> {
    Optional<LargeHoldingsEntity> findByRceptNo(long rceptNo);

    List<LargeHoldingsEntity> findAllByCorpCode(String corpCode);

    List<LargeHoldingsEntity> findByRegDtStartingWith(String regDt);
}
