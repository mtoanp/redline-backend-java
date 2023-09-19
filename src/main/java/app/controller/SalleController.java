package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.exeption.SalleException;
import app.model.entity.Cours;
import app.model.entity.Salle;
import app.service.SalleService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/salles")
public class SalleController {

	@Autowired
	private SalleService salleService;
	
	public SalleController(SalleService salleService) {
		this.salleService = salleService;
	}
	
	@PostMapping("/addSalles")
	public Salle addSalles(@RequestBody Salle salle) {
		salle.setId(null);
		salleService.add(salle);
		return salle;
	}
	
	@PostMapping()
	public Salle addGetId(@RequestBody Salle salle) {
		salle.setId(salleService.addGetId(salle));
		return salle;
	}
	
	@PutMapping()
	public Salle update(@RequestBody Salle salle) {
		return salleService.update(salle) ? salle : null;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Salle salle = salleService.get(id);
		if (salle == null) {
			throw new RuntimeErrorException(null, "Salle not found id - " + id);
		}
		return salleService.delete(salle) ? "deleted" : "operation failed";
	}
	
	@GetMapping("/{id}")
	public Salle get(@PathVariable int id) {
		if (id < 0) {
			throw new SalleException("Salle not found : " + id);
		}
		return salleService.get(id);
	}
	
	@GetMapping()
	public List<Salle> getAll() {
		return salleService.getAll();
	}
	
	@GetMapping("/findsalle/{cours}")
	public Salle getByCours(@PathVariable Cours cours) {
		return salleService.getByCours(cours);
	}
	
	
}
