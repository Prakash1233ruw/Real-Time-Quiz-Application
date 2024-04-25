package com.pp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pp.model.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	public User findByUserName(String username);

}
