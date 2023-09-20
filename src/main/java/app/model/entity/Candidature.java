package app.model.entity;

import java.sql.Connection;

public class Candidature {
    private Integer idSession;
    private Integer idEtudiant;
    private Boolean valide;

    public Candidature(Integer idSession, Integer idEtudiant) {
        this.idSession = idSession;
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }
}
