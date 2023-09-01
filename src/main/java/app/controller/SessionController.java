package app.controller;

import app.model.entity.Session;
import app.service.*;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;
import java.util.List;

@RestController
@RequestMapping("api/sessions")
public class SessionController {

	private SessionService sessionService;
	private EtudiantService etudiantService;
	private CoursService coursService;

	public SessionController(SessionService sessionService, EtudiantService etudiantService, CoursService coursService) {
		this.sessionService = sessionService;
		this.etudiantService = etudiantService;
		this.coursService = coursService;
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
	
	
	//	sessions/addCandidat?session=1&etudiant=6
	@PostMapping("/addCandidat")
	public String addCandidat(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		return sessionService.addCandidat(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session add etudiant success" : "failed";
	}
	
	@DeleteMapping("/removeCandidat")
	public String removeCandidat(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		return sessionService.removeCandidat(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session remove etudiant success" : "failed";
	}

	@PostMapping("/admin/addEtudiant")
	public String addEtudiant(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		return sessionService.addEtudiant(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session add etudiant success" : "failed";
	}

	@DeleteMapping("/admin/removeEtudiant")
	public String removeEtudiant(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
		return sessionService.removeEtudiant(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session remove etudiant success" : "failed";
	}
}
