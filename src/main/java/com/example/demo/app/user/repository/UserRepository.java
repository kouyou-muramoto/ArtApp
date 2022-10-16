package com.example.demo.app.user.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.app.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query(value="SELECT * "
			+ "FROM user "
			+ "WHERE login_id = :login_id", nativeQuery = true)
	User identifyUser(@Param("login_id") String login_id);
	
	@Query(value="SELECT * "
			+ "FROM user "
			+ "WHERE id = :id", nativeQuery = true)
	User findCurrentUserId(@Param("id") String id);
	
	
	
//	@Query(value="INSERT INTO user (login_id, name, email, password, password_confirm) "
//			+ "VALUES (:login_id, :name, :email, :password, :password_confirm);", nativeQuery = true)
//	User registerUser(@Param("login_id") String login_id,
//						@Param("name") String name,
//						@Param("email") String email, 
//						@Param("password") String password, 
//						@Param("password_confirm") String password_confirm);
	
	
//	void createUser(String name, String email, String login_id, String password_confirm, String password);
}
