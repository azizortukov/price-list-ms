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
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int serviceId;
    @Column(length = 17)
    private String mxikCode;
    private long pricePerUnit;
    @Column(length = 20)
    private String packageCode;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
