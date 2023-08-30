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

import app.exeption.FormationException;
import app.exeption.ProfesseurException;
import app.model.entity.Cours;
import app.model.entity.Formation;
import app.model.entity.Professeur;
import app.service.ProfesseurService;

@RestController

@RequestMapping("api/professeurs")
public class ProfesseurController {

	
//	@Autowired
	private ProfesseurService professeurService;

	public ProfesseurController(ProfesseurService professeurService) {
		this.professeurService = professeurService;
	}
	
	@PostMapping()
	public Professeur add(@RequestBody Professeur professeur) {
		professeur.setId(null);
		professeurService.add(professeur);
		return professeur;
	}
	@GetMapping()
	public List<Professeur> getAll() {
		return professeurService.getAll();
	}
	
	@GetMapping("/{id}")
	public Professeur get(@PathVariable int id) {
		if(id < 0)
			throw new ProfesseurException("Professeur not found: " + id);
		return professeurService.get(id);
	}
	
	@PostMapping("/addprofesseur")
	public Professeur addGet(@RequestBody Professeur professeur) {
		professeur.setId(professeurService.addGetId(professeur));
		return professeur;
	}
	
	@PutMapping()
	public Professeur updateGet(@RequestBody Professeur professeur) {
		return professeurService.update(professeur) ? professeur : null;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Professeur professeur = professeurService.get(id);
		if(professeur == null) {
			throw new RuntimeErrorException(null, "professeur not found id: " + id);
		}
		return professeurService.delete(professeur) ? "deleted" : "operation failed";
	}
	
}
