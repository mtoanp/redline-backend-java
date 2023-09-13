package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.model.entity.Theme;
import app.model.validation.ThemeValidation;
import app.service.FormationService;
import app.service.ThemeService;

@CrossOrigin(maxAge = 3600)
@RestController

@RequestMapping("api/themes")
public class ThemeController {
	
	@Autowired
	FormationService formationService;
	
	ThemeService themeService;
	ThemeValidation themeValidation;

	public ThemeController(ThemeService themeService) {
		this.themeService = themeService;
		this.themeValidation = new ThemeValidation();
	}
	
	@GetMapping("/catalogue")
	public Theme getCatalogue() {
		return themeService.getTreeRoot();
	}
	
	@GetMapping()
	public List<Theme> getAll() {
		return themeService.getAll();
	}
	
	@GetMapping("/{id}")
	public Theme get(@PathVariable int id) {
		return themeService.getWithDetails(id);
	}
	
	
	@PostMapping()
	public Theme addGet(@RequestBody Theme theme) {
		if(themeValidation.isValide(theme)) {
			return themeService.addGet(theme);
		} else {
			return null;
		}
	}

	@PutMapping()
	public Theme updateGet(@RequestBody Theme theme) {
		if(themeValidation.isValide(theme)) {
			return themeService.update(theme) ? themeService.getWithDetails(theme.getId()) : null;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Theme theme = themeService.get(id);
		if(theme == null) {
			throw new RuntimeErrorException(null, "theme not found id: " + id);
		}
		return themeService.delete(theme) ? "deleted" : "operation failed";
	}
	
	@PostMapping("/addFormation")	// themes/id?idFormation=xxx
	public Theme addFormation(@RequestParam(name = "theme") int idTheme, @RequestParam(name = "formation") int idFormation) {
		themeService.addFormation(themeService.get(idTheme), formationService.get(idFormation));
		return themeService.getWithDetails(idTheme);
	}
	
	@DeleteMapping("/removeFormation")	// themes/id?idFormation=xxx
	public Theme removeFormation(@RequestParam(name = "theme") int idTheme, @RequestParam(name = "formation") int idFormation) {
		themeService.removeFormation(themeService.get(idTheme), formationService.get(idFormation));
		return themeService.getWithDetails(idTheme);
	}
}
