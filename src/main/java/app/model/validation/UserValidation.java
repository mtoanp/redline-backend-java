package app.model.validation;

import java.util.ArrayList;
import java.util.List;

import app.model.entity.User;

public class UserValidation {
	User user;
	boolean result;
	List<String> errors = new ArrayList<>();
	
	public UserValidation() {
//		this.user = user;
		this.result = true;
	}
	
	public boolean isValide(User user) {
		this.result = true;
		this.user = user;
		valideName();
		return result;
	}

	public void valideName() {
		String msg;
		if(user.getName() == "") {
			msg = "Nom est vide";
			errors.add(msg);
			System.out.println(msg);
			result = false;
		}
	}
}
