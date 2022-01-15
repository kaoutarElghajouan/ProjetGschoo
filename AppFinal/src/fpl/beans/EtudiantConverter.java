package fpl.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fpl.service.EtudiantService;
 import fpl.entities.Etudiant;;

 
@ManagedBean
@Component
public class EtudiantConverter implements Converter {
	
	@Autowired
	private EtudiantService etudiantService;

	//convertir un objet
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
		
		
	   if (value!=null && value.trim().length() >0)
		   return this.etudiantService.getByID(Integer.parseInt(value));
		return null;
	}
	
	//convertir un string

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value==null) {
			return null;
		}
				
				
			return ((Etudiant)value).getId().toString();
		
	}

	
}
