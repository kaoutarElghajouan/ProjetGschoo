package fpl.beans;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import fpl.entities.Filiere;
import fpl.service.EtudiantService;

@ManagedBean
@Component
@Scope("session")
public class DashboardBean {
	
	
	
	@Autowired
	private EtudiantService etudiantService;
	
	private PieChartModel etudiantsByFilieres_Pie;
	private BarChartModel etudiantsByFilieres_histogram;
	
    //diagramme de repartition des filiere	
	public PieChartModel getEtudiantsByFilieres_Pie() {
		
	etudiantsByFilieres_Pie=new PieChartModel();
		
		for (Object[] objects :etudiantService.distribustionByFiliere())
			etudiantsByFilieres_Pie.getData().put(((Filiere)objects[0]).getCode(), (Long)objects[1]);
		
		etudiantsByFilieres_Pie.setTitle("Repartition des Filieres");
		etudiantsByFilieres_Pie.setLegendPosition("w");
		etudiantsByFilieres_Pie.setSliceMargin(7);
		etudiantsByFilieres_Pie.setShowDataLabels(true);
		
		 return etudiantsByFilieres_Pie;
		 
	
	}
	
	//diagramme nombre des etudiant par filieres
	public BarChartModel getEtudiantsByFilieres_histogram() {
		
		etudiantsByFilieres_histogram =new BarChartModel();
		ChartSeries filieres_series=new ChartSeries();
		
		for(Object[] objects : etudiantService.distribustionByFiliere())
			filieres_series.getData().put(((Filiere)objects[0]).getCode(),(Long)objects[1]);
		
		etudiantsByFilieres_histogram.addSeries(filieres_series);
		etudiantsByFilieres_histogram.setTitle("Nombre des Etudiants par Filieres ");
		etudiantsByFilieres_histogram.setAnimate(true);
		etudiantsByFilieres_histogram.setBarWidth(25);
		etudiantsByFilieres_histogram.setShowDatatip(true);
		etudiantsByFilieres_histogram.setDatatipFormat("%c%s");
		etudiantsByFilieres_histogram.getAxis(AxisType.Y).setLabel("Nombre des Etudiants");
		
		
		return etudiantsByFilieres_histogram;
		
	}

}
