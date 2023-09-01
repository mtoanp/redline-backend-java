package app.service;

import java.util.List;

import app.model.entity.Etudiant;
import app.model.entity.Session;

public interface EtudiantService {
	public int add(Etudiant etudiant);
	public boolean delete(Etudiant etudiant);
	public Etudiant get(int id);
	public List<Etudiant> getAll();
	public boolean update(Etudiant etudiant);
	public Integer addGetId(Etudiant etudiant);
	public List<Etudiant> getBySession(Session session);
	public List<Etudiant> getCandidatsBySession(Session session);
}
