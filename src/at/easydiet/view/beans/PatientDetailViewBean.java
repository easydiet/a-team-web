package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.PatientBO;

/**
 * Bean for the PatientDetailView
 */
@ManagedBean
@SessionScoped
public class PatientDetailViewBean
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientDetailViewBean.class);
    
    /**
     * Gets the name of the {@link PatientBO}
     * @return The name of the {@link PatientBO}
     */
    public String getPatientName()
    {
        return ControllerBean.getPatientDetailViewController().getPatient().getDisplayName();
    }
    
    /**
     * Gets all {@link DietTreatmentBO}s
     * @return List of all {@link DietTreatmentBO}s
     */
    public List<DietTreatmentBO> getDietTreatments()
    {
        return ControllerBean.getPatientDetailViewController().getPatient().getTreatments();
    }
    
    /**
     * Gets the selected {@link DietTreatmentBO}
     * @return The selected {@link DietTreatmentBO}
     */
    public DietTreatmentBO getSelectedDietTreatment()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getDietTreatment();
    }

    /**
     * Sets the selected {@link DietTreatmentBO}
     * @param dietTreatment The new selected {@link DietTreatmentBO}
     * @return destination
     */
    public String setSelectedDietTreatment(DietTreatmentBO dietTreatment)
    {
        ControllerBean.getDietTreatmentDetailViewController().setDietTreatment(dietTreatment);
        return "dietTreatmentDetailView";
    }

    /**
     * Event when a {@link DietTreatmentBO} is selected
     * @param event {@link SelectEvent}
     * @return destination
     */
    public String onDietTreatmentSelect(SelectEvent event)
    {
        return setSelectedDietTreatment((DietTreatmentBO)event.getObject());
    }
}
