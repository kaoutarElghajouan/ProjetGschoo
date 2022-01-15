package fpl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fpl.entities.Filiere;
import fpl.service.FiliereService;
import fpl.service.impl.ResultObject;

@ManagedBean
@Component
@Scope("session")
public class FiliereBean {
	
	@Autowired
	private FiliereService filiereService;
	
	private Filiere filiere;
	private List<Filiere> filieres;
	private String etatActuel ;
	private String menuLabel ;
	private boolean c;
	
	//constrecteur
	public FiliereBean() {
		this.etatActuel="List";
		this.menuLabel="Liste";
		this.c=true;
		this.filieres=new ArrayList<Filiere>();
		this.filiere=new Filiere();
	}

	@PostConstruct
	public void init() {
		
		this.filieres=this.filiereService.getAll();
	}
	
	//getters and setters
	public Filiere getFiliere() {
		return filiere;
	}


	public boolean isC() {
		return c;
	}


	public void setC(boolean c) {
		this.c = c;
	}


	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}


	public List<Filiere> getFilieres() {
		return filieres;
	}


	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
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


	
	
	//afficher la filiere
	
	public void showHandler(Filiere f) {
		this.etatActuel="Fiche";
		this.menuLabel="Fiche";
		this.filiere=f;
		this.c=false;
	}
	
	//ajouter la filiere
	 public void addHandler() {
	    	this.etatActuel="Fiche";
	    	this.menuLabel="Ajout";
	    	this.filiere=new Filiere ();
	    	this.c=false;
	    }
	
	//afficher tous les filieres
	public void listHandler() {	
		this.etatActuel="List";
		this.menuLabel="Liste";	
		this.filiere=new Filiere ();
		this.filieres=this.filiereService.getAll();
		this.c=true;
	}
	

	//supprimer filiere
	public void delete(Filiere f) {
		
		ResultObject ro =this.filiereService.delete(f);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity()
				                                                            ,ro.getTitle(),
				                                                            ro.getMessage()));
		this.filieres=this.filiereService.getAll();
	}
	
	//enregister ou modifier la filiere
	
public void save() {
		
		this.filiere.setUser(GlobalBean.getCurrentUser());
		ResultObject ro=this.filiereService.Save(this.filiere);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity()
                                                                               ,ro.getTitle()
                                                                                ,ro.getMessage()));
		this.filiere=new Filiere();
		this.showHandler(this.filiere);
		
	
	}
}

