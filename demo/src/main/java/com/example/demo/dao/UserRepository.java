package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;


/**
 * dao只要继承JpaRepository类就可以，
 * 几乎可以不用写方法，还有一个特别有尿性的功能非常赞，
 * 就是可以根据方法名来自动的生产SQL，比如findByUserName 会自动生产一个以 userName 
 * 为参数的查询方法，比如 findAlll 自动会查询表里面的所有数据，比如自动分页等等。
 * @author
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String name); //User中属性为name，所以不能使用findByUsername
	
	//当查询中有多个参数的时候Pageable建议做为最后一个参数传入
	Page<User> findByName(String name,Pageable pageable);
	
	User findById(int id);
	
	
	/*
	 * org.springframework.dao.InvalidDataAccessApiUsageException: 
	 * Executing an update/delete query; 
	 * nested exception is javax.persistence.TransactionRequiredException: 
	 * Executing an update/delete query
	 */
	/**
	 * 更新、删除操作，需要增加@Transactional事务
	 * @param id
	 * @param status
	 */
	@Transactional
	@Modifying
	@Query("update User u set u.status = ?2 where u.id = ?1")
	void updateUserStatus(int id, int status);
	
	@Query("select count(*) from User where name = ?1")
	long countByName(String name);
}
