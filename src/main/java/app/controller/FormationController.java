package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.*;

import app.exeption.FormationException;
import app.model.entity.Formation;
import app.service.FormationService;
import app.service.SessionService;
import app.service.SessionServiceImplementation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/formations")
public class FormationController {
	
//	@Autowired
	private FormationService formationService;

	public FormationController(FormationService formationService) {
		this.formationService = formationService;
	}


	@GetMapping()
	public List<Formation> getAll() {
		return formationService.getAll();
	}
	
	@GetMapping("/{id}")
	public Formation get(@PathVariable int id) {
		if(id < 0)
			throw new FormationException("Formation not found: " + id);
		return formationService.getWithDetails(id);
	}

	@GetMapping("/search={keyword}")
	public List<Formation> getByName(@PathVariable String keyword) {
		System.out.print(keyword);
		return formationService.getByName(keyword);
	}

	@PostMapping()
	public Formation addGet(@RequestBody Formation formation) {
		formation.setId(formationService.addGetId(formation));
		return formation;
	}
	
	@PutMapping()
	public Formation updateGet(@RequestBody Formation formation) {
		return formationService.update(formation) ? formation : null;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		Formation formation = formationService.get(id);
		if(formation == null) {
			throw new RuntimeErrorException(null, "formation not found id: " + id);
		}
		formationService.delete(formation);
//		return formationService.delete(formation) ? "deleted" : "operation failed";
	}
	
}
