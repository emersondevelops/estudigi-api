package br.com.estudigi.api.repository;

import br.com.estudigi.api.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    @Query(
            value = "SELECT * FROM tests",
            nativeQuery = true)
    List<Test> getTestEventsByUser(Integer userId);
}
