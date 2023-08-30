package app.service;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Salle;

public interface SalleService {

	int add(Salle sall);
	Integer addGetId(Salle sall);
	boolean update(Salle sall);
	boolean delete(Salle sall);
	Salle get(int id);
	List<Salle> getAll();
	Salle getByCours(Cours cours);
}
