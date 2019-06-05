package Mateusz.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer>{
	public User findByEmail(String email);

	// Ten kod znaczy tyle co Select * from User WHERE email = ? - tak nam to hibernate tlumaczy :)
	
	
	@Modifying //ta adnotacja oznacza, że bedziemy cos w bazie modyfikować
	@Query("UPDATE User u SET u.password = :newPassword WHERE u.email= :email")
	public void updateUserPassword(@Param("newPassword") String password, @Param("email") String email);
	
	@Modifying
	@Query("UPDATE User u SET u.name = :newName, u.lastName = :newLastName, u.email = :newEmail WHERE u.id= :id")
	public void updateUserProfile(@Param("newName") String newName, @Param("newLastName") String newLastName,
			@Param("newEmail") String newEmail, @Param("id") Integer id);
	
	
	
	
}

