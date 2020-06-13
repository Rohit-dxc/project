package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ItemList;

public interface ItemListRepository extends JpaRepository<ItemList, Integer> {
	@Query("select il from ItemList il where name=:name")
	public ItemList itemDetail(@Param("name") String name);

	@Transactional
	@Modifying
	@Query("UPDATE ItemList il 	SET quantity =:quantity WHERE name=:name")
	public void updateQuantity(@Param("name") String name, @Param("quantity") int quantity);

}
