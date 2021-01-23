package br.com.estudigi.api.repository;

import br.com.estudigi.api.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(
            value = "SELECT count(id) FROM test_results WHERE user_id = :userId AND test_id = :testId",
            nativeQuery = true)
    int findTrials(@Param("userId") Integer userId,
                    @Param("testId") Integer testId);
}
