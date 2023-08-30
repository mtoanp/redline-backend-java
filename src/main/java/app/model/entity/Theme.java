package app.model.entity;

import java.util.List;

public class Theme {
	private Integer id;
	private String nom;
	private Integer idParent;
	private List<Theme> subThemes;
	private List<Formation> formations;
	
	public Theme() {}
	
	public Theme(String nom, Integer idParent) {
		this.id = null;
		this.nom = nom;
		this.idParent = idParent;
	}
	
	public Theme(Integer id, String nom, Integer idParent) {
		this.id = id;
		this.nom = nom;
		this.idParent = idParent;
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

	public Integer getIdParent() {
		return idParent;
	}

	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}

	
	public List<Theme> getSubThemes() {
		return subThemes;
	}

	public void setSubThemes(List<Theme> subThemes) {
		this.subThemes = subThemes;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
}
