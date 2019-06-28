package Mateusz.demo.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional //polega na tym ze jesli np. zapisujemy dane w dwoch bazach i cos sie nie uda zapisac w jednej to w drugiej tez sie nie zapisze
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //tutaj szyfrujemy haslo
		user.setActive(1); // ustawiamy aktywnosc
		
		Role role = roleRepository.findByRole("ROLE_USER"); // odczytujemy Role
		user.setRoles(new HashSet<Role>(Arrays.asList(role))); // Ustawiamy role dla uzywkownika
		
		userRepository.save(user);
	}

	@Override // metoda ktora zmienia nam hasło
	public void updateUserPassword(String newPassword, String email) {
		userRepository.updateUserPassword(bCryptPasswordEncoder.encode(newPassword), email);
		
	}
	
	@Override //metoda która aktualizuje nam dane zalogowanego uzykownika
	public void updateUserProfile(String newName, String newLastName, String newEmail, int id) {
		userRepository.updateUserProfile(newName, newLastName, newEmail, id);
	}

	@Override
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	
	
}

