package fpl.dao;

import java.util.List;

import fpl.entities.Etudiant;

public interface EtudiantDao extends Idao<Etudiant>
{
	List <Object[]> distributionByEntity(String entity);
}
