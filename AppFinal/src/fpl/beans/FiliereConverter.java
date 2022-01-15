package fpl.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fpl.entities.Filiere;
import fpl.service.FiliereService;

@ManagedBean
@Component
public class FiliereConverter  implements Converter {
	
	@Autowired
    private FiliereService filiereService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value!=null && value.trim().length() >0)
			   return this.filiereService.getByID(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value!=null)
			return ((Filiere)value).getId().toString();
		return null;
	}


}
