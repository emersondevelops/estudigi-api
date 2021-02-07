package br.com.estudigi.api.repository;

import br.com.estudigi.api.model.TestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(
            value = "SELECT count(id) FROM test_results WHERE user_id = :userId AND test_id = :testId",
            nativeQuery = true)
    int findTrials(@Param("userId") Integer userId,
                    @Param("testId") Integer testId);

    @Query(
            value = "SELECT * FROM test_results tr INNER JOIN tests t ON tr.test_id = t.id where test_id = :testId",
            nativeQuery = true)
    Page<TestResult> findAllByTestId(Pageable pageable, @Param("testId") Integer testId);
}
