package app.model.entity;

public class Professeur {

	private Integer id;
	private String nom;
	private String prenom;
	
	public Professeur() {}

	public Professeur(String nom, String prenom) {
		this.id = null;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Professeur(Integer id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
