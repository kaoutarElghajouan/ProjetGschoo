package fpl.service;

import java.util.List;

import fpl.entities.Filiere;
import fpl.service.impl.ResultObject;

public interface FiliereService {

	ResultObject Save(Filiere o);
	ResultObject  delete (Filiere o);
	Filiere getByID(int id);
	List<Filiere> getAll();
   
}
