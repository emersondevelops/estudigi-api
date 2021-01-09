package br.com.estudigi.api.repository;

import br.com.estudigi.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = "SELECT * FROM users WHERE role = :role",
            nativeQuery = true)
    Page<User> findByRole(Pageable pageable, @Param("role") String role);
}
