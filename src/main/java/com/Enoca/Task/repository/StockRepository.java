package com.Enoca.Task.repository;

import com.Enoca.Task.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer>, CrudRepository<Stock,Integer> {
}
