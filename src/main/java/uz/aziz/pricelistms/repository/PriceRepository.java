package uz.aziz.pricelistms.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.aziz.pricelistms.entity.Price;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = """
            SELECT * FROM price p
            WHERE (:serviceId IS NULL OR p.service_id = :serviceId) AND
            (:mxikCode IS NULL OR p.mxik_code = :mxikCode) AND
            (:pricePerUnit IS NULL OR p.price_per_unit = :pricePerUnit) AND
            (:packageCode IS NULL OR p.package_code = :packageCode)""", nativeQuery = true)
    List<Price> findAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Pageable pageable);

}