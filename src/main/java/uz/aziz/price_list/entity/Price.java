package uz.aziz.price_list.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mxik_code", "price_per_unit"})})
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer serviceId;
    @Column(length = 17)
    private String mxikCode;
    private Long pricePerUnit;
    @Column(length = 20)
    private String packageCode;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
