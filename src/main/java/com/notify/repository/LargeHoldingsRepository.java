package com.notify.repository;

import com.notify.entity.LargeHoldingsEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeHoldingsRepository extends JpaRepository<LargeHoldingsEntity, Long> {
}
