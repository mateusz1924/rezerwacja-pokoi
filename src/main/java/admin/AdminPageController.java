package admin;


import javax.ws.rs.GET;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import Mateusz.demo.user.User;
import Mateusz.demo.user.UserService;

@Controller
public class AdminPageController {
	
	@Autowired
	private UserService userService;
	
	@GET
	@RequestMapping(value = "/admin")
	@Secured(value = { "ROLE_ADMIN" })
	public String openAdminMainPage() {
	
	//	List<User> userList = getAllUsers();
	//	model.addAttribute("userList", userList);
		
		return "admin/admin";
	}

	
	@GET
	@RequestMapping(value = "/admin/users")
	@Secured(value = { "ROLE_ADMIN" })
	public String openAdminAllUsersPage(Model model) {
		
		List<User> userList = getAllUsers();
		model.addAttribute("userList",userList);
		return "admin/users";
	}
	
	private List<User> getAllUsers(){
		List<User> userList = userService.findAll();
			for(User users: userList) {
				int numerRoli = users.getRoles().iterator().next().getId(); //jak odczytac numer roli z czegos co jest kolekcja
				users.setNrRoli(numerRoli);
					
			}
			return userList;
			
		}
	}

