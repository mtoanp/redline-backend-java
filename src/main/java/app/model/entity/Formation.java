package app.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Formation {

	private Integer id;
	private String nom;
	private String description;
	private Integer prix;
	private List<Session> sessions = new ArrayList<>();
	private List<Theme> themes = new ArrayList<>();
	
	public Formation() {}

	public Formation(String nom, String description, Integer prix) {
		this.id = null;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	public Formation(Integer id, String nom, String description, Integer prix) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

}
