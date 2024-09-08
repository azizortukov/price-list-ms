package uz.aziz.pricelistms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.aziz.pricelistms.entity.Price;
import uz.aziz.pricelistms.repository.PriceRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class PriceListMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceListMsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PriceRepository priceRepository) {
        return args -> {
            if (priceRepository.findAll().isEmpty()) {
                priceRepository.save(Price.builder()
                        .pricePerUnit(1L)
                        .mxikCode("1")
                        .serviceId(12)
                        .packageCode("12342")
                        .createdAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")))
                        .build());
                priceRepository.save(Price.builder()
                        .pricePerUnit(42L)
                        .mxikCode("1")
                        .serviceId(21)
                        .packageCode("552")
                        .createdAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")))
                        .build());
                priceRepository.save(Price.builder()
                        .pricePerUnit(12L)
                        .mxikCode("2")
                        .serviceId(4)
                        .packageCode("432")
                        .createdAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")))
                        .build());
            }
        };
    }
}
