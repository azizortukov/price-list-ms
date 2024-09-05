package uz.aziz.price_list.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aziz.price_list.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}