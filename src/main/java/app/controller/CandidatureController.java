package app.controller;

import app.model.entity.Candidature;
import app.service.EtudiantService;
import app.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/candidatures")
public class CandidatureController {
    private SessionService sessionService;

    public CandidatureController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    //  GET  localhost:8080/api/candidatures?session=1
    @GetMapping()
    public List<Candidature> getCandidaturesBySession(@RequestParam(name = "session") int idSession) {
        return sessionService.getCandidaturesBySession(idSession);
    }

    //	POST localhost:8080/api/candidatures
    @PostMapping()
    public void addCandidature(@RequestBody Candidature candidature) {
        sessionService.addCandidature(candidature);
    }

    @PutMapping()
    public void updateCandidature(@RequestBody Candidature candidature) {
        System.out.println(candidature.getIdSession() + "  " +candidature.getIdEtudiant()+ "  " +candidature.isValide());
        sessionService.updateCandidature(candidature);
    }


    //  DELETE  localhost:8080/api/candidatures
    @DeleteMapping()
    public void removeCandidature(@RequestBody Candidature candidature) {
        sessionService.removeCandidature(candidature);
    }

//    DELETE  localhost:8080/api/candidatures?session=1&etudiant=4
//    @DeleteMapping()
//    public void removeCandidature(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
//        sessionService.removeCandidature(candidature);
//    }
}
