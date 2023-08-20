package com.notify.repository;

import com.notify.entity.LargeHoldings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeHoldingsRepository extends JpaRepository<LargeHoldings, Integer> {
}
