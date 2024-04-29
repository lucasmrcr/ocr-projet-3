package fr.chatop.api.repositories;

import fr.chatop.api.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, Integer> {
}
