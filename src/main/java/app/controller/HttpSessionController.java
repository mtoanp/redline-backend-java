package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.exeption.AuthenticateException;
import app.model.entity.User;
import app.service.UserService;


@RestController
@RequestMapping("api/login")
public class HttpSessionController {
	@Autowired
	UserService userService;

	@GetMapping()
	public void loginForm() {
		
	}
	
	@PostMapping()
	public User authenticate(@RequestBody User userCheck) {
		User user = userService.getByEmail(userCheck.getEmail());
		if(user == null) {
			throw new AuthenticateException("User not existe, email: " + userCheck.getEmail());
		} else if(userService.authenticate(user, userCheck.getPassword())) {	
			System.out.println("Password match !");
			loginUser(user);
			return user;
		} else {
			System.out.println("Password incorect !");
			throw new AuthenticateException("Password incorect !");
		}
	}
	
	private void loginUser(User user) {
		
	}
}
