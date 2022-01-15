package fpl.service.impl;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fpl.dao.UserDao;
import fpl.entities.User;
import fpl.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public ResultObject Save(User o) {
	
		User u =new User();
		u.setNom(o.getNom());
		List<User>users_ex=this.userDao.getByExample(u, MatchMode.EXACT, false);
		
		if(users_ex.size()==0 || (users_ex.size()==1 && !(users_ex.get(0).getId().equals(o.getId()))) ) {
			this.userDao.Save(o);
			return new ResultObject(FacesMessage.SEVERITY_INFO,"Done",
					                                        " to Save User: "+o.getNom()+" with success");
			
		}		
		
		return new  ResultObject(FacesMessage.SEVERITY_ERROR,"Failed"," to Save User: "+o.getNom());

	}

	@Override
	@Transactional
	public ResultObject delete(User o) {
		this.userDao.delete(o);
		return new ResultObject(FacesMessage.SEVERITY_INFO,"Done"," to Delete User: "+o.getNom()+" with success");
	}

	@Override
	@Transactional
	public User getByID(int id) {
		
		return this.userDao.getByID(id);
	}

	@Override
	@Transactional
	public List<User> getAll() {
		
		return this.userDao.getAll();
	}

	@Override
	@Transactional
	public List<User> getByExample(User u) {
		
		return this.userDao.getByExample(u, MatchMode.EXACT,false);
	}

}
