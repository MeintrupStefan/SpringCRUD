package com.meintrup.springcrud.repositories;

import com.meintrup.springcrud.entities.Furniture;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FurnitureRepository {
    private final JdbcTemplate jdbc;

    public void storeFurniture(Furniture furniture) {
        String sql = "INSERT INTO furniture (product, price) VALUES (?, ?)";
        jdbc.update(sql,
                furniture.getProduct(),
                furniture.getPrice());
    }

    public List<Furniture> findAllPurchases() {
        String sql = "SELECT * FROM furniture";

        RowMapper<Furniture> furnitureRowMapper = (f, i) -> {
            Furniture rowObject = new Furniture();
            rowObject.setId(f.getInt("id"));
            rowObject.setProduct(f.getString("product"));
            rowObject.setPrice(f.getBigDecimal("price"));
            return rowObject;
        };

        return jdbc.query(sql, furnitureRowMapper);
    }
}
