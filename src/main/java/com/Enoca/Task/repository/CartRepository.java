package com.Enoca.Task.repository;

import com.Enoca.Task.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByCustomerId(int id);
}
