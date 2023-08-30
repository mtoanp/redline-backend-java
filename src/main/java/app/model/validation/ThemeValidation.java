package app.model.validation;

import java.util.ArrayList;
import java.util.List;

import app.model.entity.Theme;

public class ThemeValidation {
	Theme theme;
	boolean result;
	List<String> errors = new ArrayList<>();
	
	public ThemeValidation() {
//		this.theme = theme;
		this.result = true;
	}
	
	public boolean isValide(Theme theme) {
		this.result = true;
		this.theme = theme;
		valideName();
		return result;
	}

	public void valideName() {
		String msg;
		if(theme.getNom() == "") {
			msg = "Nom est vide";
			errors.add(msg);
			System.out.println(msg);
			result = false;
		}
	}
}
