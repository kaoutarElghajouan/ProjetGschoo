package fpl.service.impl;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fpl.dao.FiliereDao;
import fpl.entities.Filiere;
import fpl.service.FiliereService;

@Component
public class FiliereServiceImpl implements FiliereService {

	@Autowired
	private FiliereDao filiereDao;
	
	@Override
	@Transactional
	public ResultObject Save(Filiere o) {
		Filiere f=new Filiere();
		f.setCode(o.getCode());
		List<Filiere>filieres_ex=this.filiereDao.getByExample(f, MatchMode.EXACT, true);
		
		if(filieres_ex.size()==0 || (filieres_ex.size()==1 && filieres_ex.get(0).getId().equals(o.getId()))) {
			
			this.filiereDao.Save(o);
			return new ResultObject(FacesMessage.SEVERITY_INFO,"Done",
					                                    " to Save Filiere: "+o.getCode()+" with success");
			
		}		
		
		return new  ResultObject(FacesMessage.SEVERITY_ERROR,"Failed"," to Save Filiere: "+o.getCode());
	}

	@Override
	@Transactional
	public ResultObject delete(Filiere o) {
		this.filiereDao.delete(o);
		return new ResultObject(FacesMessage.SEVERITY_INFO,"Done",
				                 " to Delete Filiere: "+o.getCode()+" with success");
	}

	@Override
	@Transactional
	public Filiere getByID(int id) {
		return this.filiereDao.getByID(id);
	}

	@Override
	@Transactional
	public List<Filiere> getAll() {
		
		
		return this.filiereDao.getAll();
	}

	

}
