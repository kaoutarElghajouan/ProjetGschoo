 package fpl.beans;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import fpl.entities.Etudiant;
import fpl.entities.Filiere;
import fpl.service.EtudiantService;
import fpl.service.impl.ResultObject;



@ManagedBean
@Component
@Scope("session")
public class EtudiantBean {
	
	@Autowired
	private EtudiantService etudiantService;
	
	private Etudiant etudiant;
	private Etudiant etudiantComplete ;
	private String etatActuel ;
	private String menuLabel ;
	private List<Etudiant> etudiants ;
	private Boolean c;
	
	//constrecteur
	public EtudiantBean () {
		this.c=true;
		this.etudiant=new Etudiant();
		this.etatActuel="List";
		this.menuLabel="Liste";
		
		this.etudiants =new ArrayList<Etudiant>();
		
	}

	
	
	//les Getters & Setters 

	public Boolean getC() {
		return c;
	}


	public void setC(Boolean c) {
		this.c = c;
	}

	public String getMenuLabel() {
		return menuLabel;
	}


	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}


	public String getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(String etatActuel) {
		this.etatActuel = etatActuel;
	}

	
	public Etudiant getEtudiantComplete() {
		return etudiantComplete;
	}



	public void setEtudiantComplete(Etudiant etudiantComplete) {
		this.etudiantComplete = etudiantComplete;
	}
	


	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	//afficher l'etudiant
	public void showHandler(Etudiant e) {
		  this.etatActuel="Fiche";
			this.menuLabel="Fiche";
			this.etudiant=e;
			this.c=false;
			
	}
	

	
    
	     //afficher tous la liste
    public void listHandler() {
    	
    	this.etatActuel="List";
    	this.menuLabel="Liste";
    	this.c=true;
    	this.etudiant=new Etudiant ();
    	this.etudiants=this.etudiantService.getAll();
    }
    
    //ajout d'etudiant
    public void addHandler() {
    	this.etatActuel="Fiche";
    	this.menuLabel="Ajout";
    	this.etudiant=new Etudiant ();
    	this.c=false;
    }
    
    //recherche d'etudiaant by cne
    public List<Etudiant> completeEtudiant(String s){
    	Etudiant etudiant =new Etudiant();
    	etudiant.setCne(s);
    	return this.etudiantService.completeEtudiant(etudiant);
    }
    
    //recherche filiere by code
    public List<Filiere> completeFiliere(String s){
    	Filiere filiere =new Filiere();
    	filiere.setCode(s);
    	return this.etudiantService.completeFiliere(filiere);
    }

	
	//afficher l'etudiant rechercher 
	public void Search() {
		 
		if(this.etudiantComplete==null) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Not Found ", "Etudiant Non trouve"));
			
			return ;	
			
		}
		
	   ((GlobalBean)FacesContext.getCurrentInstance().
			                    getExternalContext().
			                    getSessionMap().get("globalBean")).etudiant();
	   
	   this.showHandler(this.etudiantComplete);
		this.etudiantComplete =null;
	}
	
	
	
	//affichage d'etudiant dans la barre auto-incriment
	public String EtudiantLabel(Etudiant e) {
		 if(e==null) return null;
		 
		 return e.getCne() + ":" + e.getPrenom() + " "+e.getNom();
		
	}
	
	
  //charger le photo donner par admin dans l'etudiant 
	public void chargePicture(FileUploadEvent event) {
		byte[] data =event.getFile().getContent();
		this.etudiant.setPhoto(data);
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				              "Done","Chargement photo avec success"));	
		
		
	
	}
  
 //retourne l'etudiant donne
	public StreamedContent etudiantPicture() {
		if(this.etudiant.getPhoto() !=null)
	return DefaultStreamedContent.builder()
			                     .contentType("image/png")
			                     .stream(() -> {return new ByteArrayInputStream(this.etudiant.getPhoto());}).build();
			                     
	
	 return null;
	}



//supprimer
public void delete(Etudiant e) {
	ResultObject ro =this.etudiantService.delete(e);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity()
			                                                            ,ro.getTitle(),
			                                                            ro.getMessage()));
	
	this.etudiants=this.etudiantService.getAll();
	
	
}

//enregister ou modifier
public void save() {

	this.etudiant.setUser(GlobalBean.getCurrentUser());
	ResultObject ro=this.etudiantService.Save(this.etudiant);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ro.getSeverity()
                                                                         ,ro.getTitle()
                                                                          ,ro.getMessage()));
	
	this.etudiant=new Etudiant();
	this.showHandler(this.etudiant);
}

}
