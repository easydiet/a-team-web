package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import at.easydiet.businessobjects.DietTreatmentBO;

@ManagedBean
@SessionScoped
public class PatientDetailViewBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientDetailViewBean.class);
    
    public String getPatientName()
    {
        return ControllerBean.getPatientDetailViewController().getPatient().getDisplayName();
    }
    
    public List<DietTreatmentBO> getDietTreatments()
    {
        return ControllerBean.getPatientDetailViewController().getPatient().getTreatments();
    }
    
    public DietTreatmentBO getSelectedDietTreatment()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getDietTreatment();
    }

    public String setSelectedDietTreatment(DietTreatmentBO dietTreatment)
    {
        ControllerBean.getDietTreatmentDetailViewController().setDietTreatment(dietTreatment);
        return "dietTreatmentDetailView";
    }

    public String onDietTreatmentSelect(SelectEvent event)
    {
        return setSelectedDietTreatment((DietTreatmentBO)event.getObject());
    }
}
