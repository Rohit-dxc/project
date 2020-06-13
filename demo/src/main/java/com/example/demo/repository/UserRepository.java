package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// @Query("select count(*) from User ur where ur.email=:#{#urdto.getEmail()} and
	// ur.pass= :#{#urdto.getPass()}")

	@Query("select ur from User ur where ur.email=:#{#urdto.getEmail()}")
	public User logId(@Param("urdto") UserDto urdto);
	// public User logId(@Param("urdto") UserDto urdto);
// and ur.pass=:#{#urdto.getPass()}//	public User logId(@Param("uid") long uid, @Param("pass") String pass);
	// @Query(" SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END from User ur
	// where ur.email=:#{#urdto.getEmail()} and ur.pass=:#{#urdto.getPass()}")

	// @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Company c
	// WHERE c.name = :companyName")

	@Query("select count(ur)>0 from User ur where ur.email=:buyer")
	public boolean checkUser(String buyer);

}
