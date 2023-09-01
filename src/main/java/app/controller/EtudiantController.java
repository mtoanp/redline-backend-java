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

import app.exeption.EtudiantException;
import app.model.entity.Etudiant;
import app.service.EtudiantService;
import app.service.SessionService;
import app.service.SessionServiceImplementation;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;
	
	public EtudiantController(EtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}
	
	@PostMapping("/addEtudiants")
	public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
		etudiant.setId(null);
		etudiantService.add(etudiant);
		return etudiant;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Etudiant etudiant = etudiantService.get(id);
		if (etudiant == null) {
			throw new RuntimeErrorException(null, "Etudiant not found id - " + id);
		}
		return etudiantService.delete(etudiant) ? "deleted" : "operation failed";
	}
	
	@GetMapping("/{id}")
	public Etudiant get(@PathVariable int id) {
		if(id < 0) {
			throw new EtudiantException("Etudiant not found : " + id);
		}
		return etudiantService.get(id);
	}
	
	@GetMapping()
	public List<Etudiant> getAll() {
		return etudiantService.getAll();
	}
	
	@PutMapping()
	public Etudiant update(@RequestBody Etudiant etudiant) {
		return etudiantService.update(etudiant) ? etudiant : null;
	}
	
	@PostMapping()
	public Etudiant addGetId(@RequestBody Etudiant etudiant) {
		etudiant.setId(etudiantService.addGetId(etudiant));
		return etudiant;
	}
	
	@GetMapping("/findsession/{sessionId}")
	public List<Etudiant> getBySession(@PathVariable int id) {
		SessionService sessionService = new SessionServiceImplementation();
		return etudiantService.getBySession(sessionService.get(id));
	}
	
	@GetMapping("/findsessionCandidat/{sessionId}")
	public List<Etudiant> getCandidatsBySession(@PathVariable int id) {
		SessionService sessionService = new SessionServiceImplementation();
		return etudiantService.getCandidatsBySession(sessionService.get(id));
	}
	
	
}
