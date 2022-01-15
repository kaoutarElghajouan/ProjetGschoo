package fpl.service;

import java.util.List;

import fpl.entities.Etudiant;
import fpl.entities.Filiere;
import fpl.service.impl.ResultObject;


public interface EtudiantService {


	ResultObject Save(Etudiant o);
	ResultObject delete (Etudiant o);
	Etudiant getByID(int id);
	List<Etudiant> getAll();
	List<Etudiant> completeEtudiant (Etudiant e);
	List <Object[]> distribustionByFiliere();
	List<Filiere> completeFiliere(Filiere f);

}
