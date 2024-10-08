package uz.aziz.pricelistms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "price_list")
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
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
