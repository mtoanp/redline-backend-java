package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.entity.User;
import app.model.validation.UserValidation;
import app.service.UserService;

@RestController

@RequestMapping("api/users")
public class UserController {
	
//	@Autowired
	UserService userService;
	UserValidation userValidation;
	
	public UserController(UserService userService) {
		this.userService = userService;
		this.userValidation = new UserValidation();
	}
	
	@GetMapping()
	public List<User> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable int id) {
		return userService.get(id);
	}
	
	
	@PostMapping()
	public User addGet(@RequestBody User user) {
		if(userValidation.isValide(user)) {
			return userService.get(userService.addGetId(user));
		} else {
			return null;
		}
	}

	@PutMapping()
	public User updateGet(@RequestBody User user) {
		if(userValidation.isValide(user)) {
			return userService.update(user) ? userService.get(user.getId()) : null;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		User user = userService.get(id);
		if(user == null) {
			throw new RuntimeErrorException(null, "user not found id: " + id);
		}
		return userService.delete(user) ? "deleted" : "operation failed";
	}
	
}
