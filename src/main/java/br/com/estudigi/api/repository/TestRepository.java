package br.com.estudigi.api.repository;

import br.com.estudigi.api.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    @Query(
            value = "SELECT * FROM tests WHERE created_by_id = :userId",
            nativeQuery = true)
    Page<Test> getTestsByCreatedBy(Pageable pageable, @Param("userId") Integer userId);

    Page<Test> findAll(Pageable pageable);
}
