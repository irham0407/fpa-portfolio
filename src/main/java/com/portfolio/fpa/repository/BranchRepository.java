package com.portfolio.fpa.repository;

import com.portfolio.fpa.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    // Query bantuan untuk mengecek apakah nama cabang sudah terdaftar atau belum
    boolean existsByBranchName(String branchName);

    // Query untuk mencari cabang berdasarkan nama cabang secara spesifik
    Optional<Branch> findByBranchName(String branchName);
}
