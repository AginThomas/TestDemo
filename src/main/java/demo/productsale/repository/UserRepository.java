package demo.productsale.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.productsale.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
    @Query("SELECT u FROM User u WHERE u.userName = :username AND u.role = 'ADMIN'")
    Optional<User> findByUserNameAndRoleAdmin(String username);
    }
