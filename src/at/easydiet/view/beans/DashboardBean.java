package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import at.easydiet.businessobjects.PatientBO;

/**
 * Bean for the DashboardView 
 */
@ManagedBean
@SessionScoped
public class DashboardBean
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DashboardBean.class);

    /**
     * Gets patients
     * @return List of {@link PatientBO}s
     */
    public List<PatientBO> getPatients()
    {
        return ControllerBean.getDashboardViewController().getPatients();
    }

    /**
     * Gets the selected patient
     * @return {@link PatientBO}
     */
    public PatientBO getSelectedPatient()
    {
        return ControllerBean.getPatientDetailViewController().getPatient();
    }

    /**
     * Executed when a patient is selected
     * @param event {@link SelectEvent}
     * @return destination
     */
    public String onPatientSelect(SelectEvent event)
    {
        ControllerBean.getPatientDetailViewController().setPatient((PatientBO) event.getObject());
        return "patientDetailView";
    }
    
}
