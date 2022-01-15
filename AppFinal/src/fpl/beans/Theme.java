package fpl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope("session")
public class Theme {

	

private String themeDisplayName;

 private String name;

public Theme(String themeDisplayName, String name) {
	
	this.themeDisplayName = themeDisplayName;
	this.name = name;
}



public String getThemeDisplayName() {
	return themeDisplayName;
}



public void setThemeDisplayName(String themeDisplayName) {
	this.themeDisplayName = themeDisplayName;
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


}


 class ThemesList{
	 
	public static List<Theme> THEMES;
	public static Theme DEFAULT_THEME =new Theme("Afternoon","afternoon");
	
	static {
	   
		THEMES=new ArrayList<>();
		
		
		THEMES.add(new Theme("Omega","omega"));
		THEMES.add(new Theme("Aristo","aristo"));
		THEMES.add(new Theme("Home","home"));
		THEMES.add(new Theme("Afternoon","afternoon"));
		THEMES.add(new Theme("Blue-Sky","bluesky"));
		THEMES.add(new Theme("Casablanca","casablanca"));
		THEMES.add(new Theme("Delta","delta"));
		THEMES.add(new Theme("Sam","sam"));
		THEMES.add(new Theme("Rocket","rocket"));
		THEMES.add(new Theme("Flick","flick"));
		THEMES.add(new Theme("Bootstrap","bootstrap"));
		THEMES.add(new Theme("Glass-x","glass-x"));
		THEMES.add(new Theme("Cupertino","cupertino"));
		THEMES.add(new Theme("Hot-Sneaks","hot-sneaks"));
		THEMES.add(new Theme("Eggyplant","eggyplant"));
		THEMES.add(new Theme("Humanity","humanity"));
		THEMES.add(new Theme("Pepper-Grinder","pepper-grinder"));
		THEMES.add(new Theme("Blitzer","blitzer"));
		THEMES.add(new Theme("Black-Tie","black-tie"));
		THEMES.add(new Theme("Blue-Sky","bluesky"));
		
		
		
		
		
		
		
		
		
	}
	 
 }
