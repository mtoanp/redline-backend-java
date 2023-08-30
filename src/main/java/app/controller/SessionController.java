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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.entity.Session;
import app.service.CoursService;
import app.service.EtudiantService;
import app.service.EtudiantServiceImplementation;
import app.service.FormationService;
import app.service.FormationServiceImplementation;
import app.service.SessionService;

@RestController
@RequestMapping("api/sessions")
public class SessionController {

	private SessionService sessionService;
	
	@Autowired
	CoursService coursService;

	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	
	@GetMapping("/{id}")
	public Session get(@PathVariable int id) {
		return sessionService.getWithDetails(id);
	}
	
	@GetMapping()	// ?idFormation
	public List<Session> getByFormation(@RequestParam int idFormation) {
		FormationService formationService = new FormationServiceImplementation();
		return sessionService.getByFormation(formationService.get(idFormation));
	}
	
	@PostMapping()
	public String add(@RequestBody Session session) {
		return sessionService.addGetId(session) != null ? "Session added" : "failed";
	}
	
	@PutMapping()
	public String update(@RequestBody Session session) {
		return sessionService.update(session) ? "Session updated" : "failed";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Session session = sessionService.get(id);
		if(session == null) {
			throw new RuntimeErrorException(null, "formation not found id: " + id);
		}
		return sessionService.delete(session) ? "deleted" : "operation failed";
	}
	
	
	//	sessions/addEtudiant?session=1&etudiant=6
	@PostMapping("/addEtudiant")
	public String addEtudiant(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		EtudiantService etudiantService = new EtudiantServiceImplementation();
		return sessionService.addEtudiant(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session add etudiant success" : "failed";
	}
	
	@DeleteMapping("/removeEtudiant")
	public String removeEtudiant(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		EtudiantService etudiantService = new EtudiantServiceImplementation();
		return sessionService.removeEtudiant(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session remove etudiant success" : "failed";
	}

}
