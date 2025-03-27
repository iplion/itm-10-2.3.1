package web.entity;

import lombok.Data;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "hair_color", nullable = false)
    private String hairColor;

    @Column(name = "must_die", nullable = false, updatable = false)
    private Boolean isMustDie = false;

    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void validateNames() {
        if ((this.name == null || this.name.trim().isEmpty()) && (this.nickname == null || this.nickname.trim().isEmpty())) {
            throw new IllegalArgumentException("User must have either a nickname or a name at least once");
        }

        this.updatedAt = LocalDateTime.now();
    }

    public User(String name, String nickname, String hairColor) {
        this.name = name;
        this.nickname = nickname;
        this.hairColor = hairColor;
    }
}
