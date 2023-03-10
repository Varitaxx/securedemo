package eu.asgardschmiede.securedemo.repository;

import eu.asgardschmiede.securedemo.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    Optional<Token> findByIdAndType(UUID uuid,Token.TokenType type);

    void deleteByCreatedAtBefore(LocalDateTime localDateTime);
}
