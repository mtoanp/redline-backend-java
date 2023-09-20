package app.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.exeption.EtudiantException;
import app.model.entity.Etudiant;
import app.service.EtudiantService;
import app.service.SessionService;
import app.service.SessionServiceImplementation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;
	
	public EtudiantController(EtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		Etudiant etudiant = etudiantService.get(id);
		if (etudiant == null) {
			throw new RuntimeErrorException(null, "Etudiant not found id - " + id);
		}
		etudiantService.delete(etudiant);
//		return etudiantService.delete(etudiant) ? "deleted" : "operation failed";
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

	@GetMapping("/search={keyword}")
	public List<Etudiant> getByName(@PathVariable String keyword) {
		return etudiantService.getByName(keyword);
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

	@PostMapping("/addEtudiants")
	public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
		etudiant.setId(null);
		etudiantService.add(etudiant);
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
