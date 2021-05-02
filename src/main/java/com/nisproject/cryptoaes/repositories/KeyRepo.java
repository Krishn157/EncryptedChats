package com.nisproject.cryptoaes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisproject.cryptoaes.models.KeyEntity;

@Repository
public interface KeyRepo extends JpaRepository<KeyEntity, Long> {

	@Query(value = "SELECT * FROM user_keys WHERE (user_id_1 = ?1 and user_id_2 = ?2) or (user_id_1 = ?2 and user_id_2 = ?1)", nativeQuery = true)
	KeyEntity findKeyOfUsers(@Param("user_id_1") String user_id_1, @Param("user_id_2") String user_id_2);
}
