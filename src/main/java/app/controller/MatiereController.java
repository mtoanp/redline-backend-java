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
import app.exeption.MatiereException;
import app.model.entity.Matiere;
import app.service.MatiereService;

@RestController

@RequestMapping("api/matieres")
public class MatiereController {
//	@Autowired
	private MatiereService matiereService;
	
	public MatiereController(MatiereService matiereService) {
		this.matiereService = matiereService;

	}
	
	@GetMapping()
	public List<Matiere> getAll() {
		return matiereService.getAll();
	}
	
	@GetMapping("/{id}")
	public Matiere get(@PathVariable int id) {
		if(id < 0)
			throw new MatiereException("Matiere not found: " + id);
		return matiereService.get(id);
	}
	
	@PostMapping("/addmatiere")
	public Matiere addGet(@RequestBody Matiere Matiere) {
		Matiere.setId(matiereService.addGetId(Matiere));
		return Matiere;
	}
	
	@PutMapping()
	public Matiere updateGet(@RequestBody Matiere matiere) {
		return matiereService.update(matiere) ? matiere : null;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Matiere matiere = matiereService.get(id);
		if(matiere == null) {
			throw new RuntimeErrorException(null, "matiere not found id: " + id);
		}
		return matiereService.delete(matiere) ? "deleted" : "operation failed";
	}
	
}
