package uz.aziz.price_list;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.aziz.price_list.entity.Price;
import uz.aziz.price_list.repo.PriceRepository;

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
                        .build());
                priceRepository.save(Price.builder()
                        .pricePerUnit(42L)
                        .mxikCode("1")
                        .serviceId(21)
                        .packageCode("552")
                        .build());
                priceRepository.save(Price.builder()
                        .pricePerUnit(12L)
                        .mxikCode("2")
                        .serviceId(4)
                        .packageCode("432")
                        .build());
            }
        };
    }
}
