package study.eatgo.domain.review.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import study.eatgo.domain.restaurant.domain.Restaurant;

@EqualsAndHashCode(of = {"id"})
@ToString(of = {"username","score","content"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Integer id;

    //TODO: User table과 Mapping
    @Column(name = "username", nullable = false, updatable = false, length = 50)
    private String username;

    @Min(0)
    @Max(5)
    @Column(name = "score", nullable = false, updatable = false)
    private Integer score;

    @Column(name = "content", updatable = false, length = 100)
    private String content;

    //TODO: 사진 추가

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false, nullable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Builder
    public Review(String username, Integer score, String content, Restaurant restaurant) {
        this.username = username;
        this.score = score;
        this.content = content;
        this.restaurant = restaurant;
    }

}
