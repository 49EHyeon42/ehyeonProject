package dev.ehyeon.ehyeon.repository;

import dev.ehyeon.ehyeon.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndDeletedAtIsNull(Long id);

    List<User> findAllByDeletedAtIsNull();

    // INFO: 쿼리로 직접 deletedAt을 넣을 수 있지만 User 의 delete 메서드 사용을 권장
    @Modifying
    @Query("UPDATE User u SET u.deletedAt = :deletedAt WHERE u.id = :id AND u.deletedAt IS NULL")
    void softDeleteById(@Param("id") Long id, @Param("deletedAt") LocalDateTime deletedAt);
}
