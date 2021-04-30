package com.nisproject.cryptoaes.repositories;

import java.util.List;

import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.repositories.interfaces.UserId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);

    @Query(value = "SELECT id,user_id FROM users WHERE user_id <> ?1", nativeQuery = true)
    List<UserId> findAllUsers(@Param("userId") String userId);

}
