package com.notify.repository;

import com.notify.entity.ExecOwnershipEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecOwnershipRepository extends JpaRepository<ExecOwnershipEntity, Long> {
    Optional<ExecOwnershipEntity> findByRceptNo(long rceptNo);

    List<ExecOwnershipEntity> findAllByCorpCode(String corpCode);

}
