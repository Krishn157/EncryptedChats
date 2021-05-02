package com.nisproject.cryptoaes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisproject.cryptoaes.models.ChatEntity;

@Repository
public interface ChatRepo extends JpaRepository<ChatEntity, Long> {
	@Query(value = "select * from chats c where c.sender_id = ?1 or c.recipient_id = ?1", nativeQuery = true)
	List<ChatEntity> findChatsBySenderAndRecipient(String userId);
}