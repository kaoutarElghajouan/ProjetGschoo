package fpl.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fpl.entities.User;
import fpl.service.UserService;
import fpl.service.impl.ResultObject;

@ManagedBean
@Component
@Scope("session")
public class UserBean {

	@Autowired
	private UserService userService;
	
	private User user;
	private String etatActuel ;
	private String menuLabel ;
	private boolean c;
	private List<User> users ;
	private String userpass;
	
	//constrecteur
	public UserBean() {
		this.user=new User();
		this.etatActuel="List";
		this.menuLabel="Liste";
		this.c=true;
	
	}

	
	@PostConstruct
	public void  init() {
		this.users=this.userService.getAll();
	}

	
	//getters and setters
	public boolean isC() {
		return c;
	}


	public void setC(boolean c) {
		this.c = c;
	}



	public String getUserpass() {
		return userpass;
	}


	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(String etatActuel) {
		this.etatActuel = etatActuel;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}



	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	//afficher tous les users
public void listHandler() {
		
		this.etatActuel="List";
		this.menuLabel="Liste";	
		this.c=true;
		this.user=new User();
		this.users=this.userService.getAll();
	}

//ajouter un user
public void addHandler()
{
	this.etatActuel="Fiche";
	this.menuLabel="Ajout";
	this.user=new User ();
	this.c=false;
}


//afficher l'info de user
public void showHandler(User u) {
	this.etatActuel="Fiche";
	this.menuLabel="Fiche";
	this.user=u;
	this.c=false;
}

//supprimer un user
public void delete(User u) {
	
	ResultObject ro =this.userService.delete(u);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity()
			                                                            ,ro.getTitle(),
			                                                            ro.getMessage()));
	this.users=this.userService.getAll();
	
}


//enregistrer ou modifier 
public void save() {
	   
	
	if(this.user.getId()!=null &&(this.user.getPassword()==null  ||this.user.getPassword().equals("")))
		this.user.setPassword(this.userpass);
	  
	    this.userpass=this.user.getPassword();
	    ResultObject ro=this.userService.Save(this.user);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity(),
	    		                                                            ro.getTitle(),
	    		                                                            ro.getMessage()));
	    this.user=new User();
	    this.showHandler(this.user);
	
}
	
}
