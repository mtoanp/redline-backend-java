package app.controller;

import app.model.entity.Candidature;
import app.service.EtudiantService;
import app.service.SessionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/candidatures")
public class CandidatureCOntroller {
    private SessionService sessionService;
    private EtudiantService etudiantService;

    public CandidatureCOntroller(SessionService sessionService, EtudiantService etudiantService) {
        this.sessionService = sessionService;
        this.etudiantService = etudiantService;
    }


    @GetMapping()

    //	sessions/addCandidature?session=1&etudiant=4
    @PostMapping()
    public void addCandidature(@RequestBody Candidature candidature) {
        sessionService.addCandidature(sessionService.get(candidature.getIdSession()), etudiantService.get(candidature.getIdEtudiant()));
    }

//    denyCandidature?session=1&etudiant=4
    @DeleteMapping()
    public void removeCandidature(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
        sessionService.removeCandidature(sessionService.get(idSession), etudiantService.get(idEtudiant));
    }

    @PutMapping()
    public void updateCandidature(@RequestBody Candidature candidature) {
        sessionService.acceptCandidature(sessionService.get(candidature.getIdSession()), etudiantService.get(candidature.getIdEtudiant()));
    }

//    @DeleteMapping("/admin/denyCandidature")
//    public String denyCandidature(@RequestParam(name = "session") int idSession, @RequestParam(name = "etudiant") int idEtudiant) {
//        return sessionService.denyCandidature(sessionService.get(idSession), etudiantService.get(idEtudiant)) ? "Session remove etudiant success" : "failed";
//    }
}
