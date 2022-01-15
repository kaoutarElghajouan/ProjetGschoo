package fpl.service.impl;


import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fpl.dao.EtudiantDao;
import fpl.dao.FiliereDao;
import fpl.entities.Etudiant;
import fpl.entities.Filiere;
import fpl.service.EtudiantService;

@Component
public class EtudiantServiceImpl implements EtudiantService {


		@Autowired
		private EtudiantDao etudiantDao;
		
		
		 @Autowired
		 private FiliereDao filiereDao;
		
		@Override
		@Transactional
		public ResultObject Save(Etudiant o) {
			Etudiant e=new Etudiant();
			e.setCne(o.getCne());
			List<Etudiant>etudiant_ex=this.etudiantDao.getByExample(e, MatchMode.EXACT, true);
			
			if(etudiant_ex.size()==0 || (etudiant_ex.size()==1 && etudiant_ex.get(0).getId().equals(o.getId()))) {
				
				this.etudiantDao.Save(o);
				return new ResultObject(FacesMessage.SEVERITY_INFO,"Done"," to Save Etudiant: "
				                                                 +o.getPrenom()+" " +o.getNom()+" with success");
				
			}		
			
			return new  ResultObject(FacesMessage.SEVERITY_ERROR,"Failed"," to Save Etudiant: "
			                                                            +o.getPrenom()+" "+o.getNom());
		}

	    @Override
		@Transactional
		public ResultObject delete(Etudiant o) {
		 
				this.etudiantDao.delete(o);
				return new ResultObject(FacesMessage.SEVERITY_INFO,"Done"," to delete Etudiant: "
				                                              +o.getPrenom()+" " +o.getNom()+" with success");
				
			
		}

		@Override
		@Transactional
		public Etudiant getByID(int id) {
			return this.etudiantDao.getByID(id);
		}

		@Override
		@Transactional
		public List<Etudiant> getAll() {
			 
			return this.etudiantDao.getAll();
		}

		@Override
		@Transactional
		public List<Etudiant> completeEtudiant(Etudiant e) {
			
			return this.etudiantDao.getByExample(e, MatchMode.START, false);
		}

		@Override
		@Transactional
		public List<Object[]> distribustionByFiliere() {

			return this.etudiantDao.distributionByEntity("filiere");
			
		}

		
		@Override
		@Transactional
		public List<Filiere> completeFiliere(Filiere f) {
			
			return this.filiereDao.getByExample(f, MatchMode.START, false);
		}
	
	}
