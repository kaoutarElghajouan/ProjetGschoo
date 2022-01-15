package fpl.beans;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fpl.entities.User;
import fpl.service.UserService;

@ManagedBean
@Component
@Scope("session")
public class GlobalBean {
	
	@Autowired
	private UserService userService;
	
	private  User userConnecte ;
	private String etatMenu;
	private User user;
	private boolean connextion;
	private int indexMenu ;
	
//constrecteur
	public GlobalBean() {
		this.userConnecte=null;
		this.etatMenu="dashboard/dashboard";
		this.user=new User();
		this.connextion = false;
		this.indexMenu=0;
		
	}

	
	//getters and setters
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public User getUserConnecte() {
		return userConnecte;
	}


	public void setUserConnecte(User userConnecte) {
		this.userConnecte = userConnecte;
	}


	public boolean isConnextion() {
		return connextion;
	}

	public void setConnextion(boolean connextion) {
		this.connextion = connextion;
	}
	
  public String getEtatMenu() {
		return etatMenu;
	}
  
  public void setEtatMenu(String etatMenu) {
		this.etatMenu = etatMenu;
	}


	public int getIndexMenu() {
		return indexMenu;
	}
	
	
	public void setIndexMenu(int indexMenu) {
		this.indexMenu = indexMenu;
	}

	//affciher accueil
	public void home() {
		this.etatMenu ="dashboard/dashboard";
		this.indexMenu=0;
	}
   
	//afficher filieres
	public void filiere() {
		
		this.etatMenu="filiere/filiere";
		
		this.indexMenu=0;
	  }
	
//afficher les etudiants
public void etudiant() {
		
		this.etatMenu="etudiant/etudiant";
	    ((EtudiantBean)FacesContext.getCurrentInstance()
	    		                   .getExternalContext()
	    		                   .getSessionMap().get("etudiantBean")).listHandler();
		
	    this.indexMenu=0;
	 }

//affichers les users

public void user() {
	this.etatMenu="user/user";
	
	this.indexMenu=1;
}

//affichers Themes
 public void preferences() {
	 this.etatMenu="user/preferences";
	 this.indexMenu = 1;
	 
 }

 //donner theme au user connecte
 public String getUserTheme() {
	 
	 if(this.userConnecte==null || this.userConnecte.getTheme()==null || this.userConnecte.getTheme().equals(""))
	         return ThemesList.DEFAULT_THEME.getName();
	    	
	     return this.userConnecte.getTheme();
			 
		 }
 
 //retoune tous les themeList
 public List<Theme> getThemes(){
	 return ThemesList.THEMES;
}

	//enregister la theme de user connecte
	public void saveUserConnecteTheme() {
		 this.userService.Save(this.userConnecte);
		 
		 
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Done",
				                              "TEME Switched to "+getDisplayTheme(this.userConnecte.getTheme())));
		 
		 
	}
	
	//afficher tous les themeList et chenger teme 
	public String getDisplayTheme(String themeName) {
		for(Theme theme:ThemesList.THEMES)
		   if(theme.getName().equals(themeName))
			   return theme.getThemeDisplayName();
		
		return themeName;
	}
	
	//authentification de user 
	public void connecter() throws  Exception {
		List <User> users =this.userService.getByExample(this.user);
		 
	if(users !=null && users.size()==1 &&users.get(0).getPassword().equals(this.user.getPassword())) {
		this.connextion=true;
		this.userConnecte= users.get(0);
		this.userConnecte.setLastLogin(new Date());
		this.userService.Save(this.userConnecte);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		
		
	   }
	
	//password Errone
		this.user.setPassword("");
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				              "Autentification Failed","wrong login or password"));	


	}
	
	//donne la forme de date de user connecte
	public String getLastLoginUser() {
		if(this.userConnecte.getLastLogin()==null) return "-";
		return new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(userConnecte.getLastLogin());
	}
	
	//deconncter
	public void deconnecter() throws Exception{
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		
	}
	
	//exemple de exception
	public void throwException() throws Exception{
		throw new Exception(" exception !");
	}
	
	// 
		 public static User getCurrentUser() {
			 
			 return ((GlobalBean)FacesContext
					             .getCurrentInstance()
					             .getExternalContext()
					             .getSessionMap().get("globalBean"))
					              .getUserConnecte();
		 }
}
