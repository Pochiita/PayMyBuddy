package com.paymybuddy.pochiita.repository;

import com.paymybuddy.pochiita.model.User;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT f.id FROM User u JOIN u.friendsList f WHERE u.id = :userId) AND u.id != :userId AND u.id != :ownUserId")
    List<User> findAllUsersNotInFriendListAndNotOwnUser(@Param("userId") Long userId, @Param("ownUserId") Long ownUserId, Pageable pageable);

}
