package vn.ltnc.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ltnc.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User tiep);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);
}
