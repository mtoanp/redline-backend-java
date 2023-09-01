package app.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Session {

	private Integer id;
	private String dateDeDebut;
	private String dateDeFin;
	private Integer capacite;
	private Integer idFormation;
	private Integer nbEtudiants;
	private List<Etudiant> etudiants = new ArrayList<>();
	private List<Etudiant> candidats = new ArrayList<>();
	private List<Cours> coursList = new ArrayList<>();
	private Formation formation;
	
	public Session() {}

	public Session(String dateDeDebut, String dateDeFin, Integer capacite, Integer idFormation) {
		this.id = null;
		this.dateDeDebut = dateDeDebut;
		this.dateDeFin = dateDeFin;
		this.capacite = capacite;
		this.idFormation = idFormation;
	}
	
	public Session(Integer id, String dateDeDebut, String dateDeFin, Integer capacite, Integer idFormation) {
		this.id = id;
		this.dateDeDebut = dateDeDebut;
		this.dateDeFin = dateDeFin;
		this.capacite = capacite;
		this.idFormation = idFormation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateDeDebut() {
		return dateDeDebut;
	}

	public void setDateDeDebut(String dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}

	public String getDateDeFin() {
		return dateDeFin;
	}

	public void setDateDeFin(String dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Integer getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(Integer idFormation) {
		this.idFormation = idFormation;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Integer getNbEtudiants() {
		return nbEtudiants;
	}

	public void setNbEtudiants(Integer nbEtudiants) {
		this.nbEtudiants = nbEtudiants;
	}

	public List<Cours> getCoursList() {
		return coursList;
	}

	public void setCoursList(List<Cours> coursList) {
		this.coursList = coursList;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Etudiant> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Etudiant> candidats) {
		this.candidats = candidats;
	}
	
}
