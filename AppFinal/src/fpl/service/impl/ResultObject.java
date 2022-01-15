package fpl.service.impl;

import javax.faces.application.FacesMessage.Severity;

import org.springframework.stereotype.Component;

@Component
public class ResultObject {
	private Severity severity ;
	private String title;
	private String message;
	
	public ResultObject(Severity severity, String title, String message) {
		
		this.severity = severity;
		this.title = title;
		this.message = message;
	}

	public ResultObject() {
		
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
