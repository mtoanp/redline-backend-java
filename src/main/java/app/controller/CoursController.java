package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.exeption.CoursException;
import app.model.entity.Cours;
import app.service.CoursService;
import app.service.SessionService;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

//	@Autowired
	private CoursService coursService;
	@Autowired
	private SessionService sessionService;
	
	public CoursController(CoursService coursService) {
		this.coursService = coursService;
	}
	
	
	@GetMapping()
	public List<Cours> getAll() {
		return coursService.getAll();
	}
	
	@GetMapping("/{id}")
	public Cours get(@PathVariable int id) {
		if (id < 0) {
			throw new CoursException("Cours not found :" + id);
		}
		return coursService.get(id);
	}
	
	
	@GetMapping("/session/{idSession}")
	public List<Cours> getBySession(@PathVariable int idSession) {
		return coursService.getBySession(sessionService.get(idSession));
	}
	
	
	@PostMapping()
	public Cours addGetId(@RequestBody Cours cours) {
		cours.setId(coursService.addGetId(cours));
		return cours;
	}
	
	@PutMapping()
	public Cours update(@RequestBody Cours cours) {
		return coursService.update(cours) ? cours : null;
	}
	
	@PostMapping("/addCours")
	public Cours add(@RequestBody Cours cours) {
		cours.setId(coursService.addGetId(cours));
		return cours;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Cours cours = coursService.get(id);
		if (cours == null) {
			throw new RuntimeErrorException(null, "Cours not found id - " + id);
		}
		return coursService.delete(cours) ? "deleted" : "operation failed";
	}
}
