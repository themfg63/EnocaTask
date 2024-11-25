package com.Enoca.Task.repository;

import com.Enoca.Task.entity.CartTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartTempRepository extends JpaRepository<CartTemp,Integer> {

    List<CartTemp> findByCartId(int cartId);

    void deleteByCustomerId(int customerId);
}
