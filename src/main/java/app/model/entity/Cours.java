package app.model.entity;

public class Cours {
	
	private Integer id;
	private String nom;
	private String date;
	private Integer idSession;
	private Integer idProfesseur;
	private Integer idMatiere;
	private Integer idSalle;
	
	private Formation formation;
	private Session session;
	private Professeur professeur;
	private Matiere matiere;
	private Salle salle;
	
	public Cours() {}
	
	public Cours(String nom, String date, Integer idSession, Integer idProfesseur,
			Integer idMatiere, Integer idSalle) {
		this.id = null;
		this.nom = nom;
		this.date = date;
		this.idSession = idSession;
		this.idProfesseur = idProfesseur;
		this.idMatiere = idMatiere;
		this.idSalle = idSalle;
	}


	public Cours(Integer id, String nom, String date, Integer idSession, Integer idProfesseur,
			Integer idMatiere, Integer idSalle) {
		this.id = id;
		this.nom = nom;
		this.date = date;
		this.idSession = idSession;
		this.idProfesseur = idProfesseur;
		this.idMatiere = idMatiere;
		this.idSalle = idSalle;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIdSession() {
		return idSession;
	}

	public void setIdSession(Integer idSession) {
		this.idSession = idSession;
	}

	public Integer getIdProfesseur() {
		return idProfesseur;
	}

	public void setIdProfesseur(Integer idProfesseur) {
		this.idProfesseur = idProfesseur;
	}

	public Integer getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Integer idMatiere) {
		this.idMatiere = idMatiere;
	}

	public Integer getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Integer idSalle) {
		this.idSalle = idSalle;
	}

	
	
	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	

}
