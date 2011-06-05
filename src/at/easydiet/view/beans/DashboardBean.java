package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import at.easydiet.businessobjects.PatientBO;

@ManagedBean
@SessionScoped
public class DashboardBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DashboardBean.class);

    public List<PatientBO> getPatients()
    {
        return ControllerBean.getDashboardViewController().getPatients();
    }

    public PatientBO getSelectedPatient()
    {
        return ControllerBean.getPatientDetailViewController().getPatient();
    }

    public String onPatientSelect(SelectEvent event)
    {
        ControllerBean.getPatientDetailViewController().setPatient((PatientBO) event.getObject());
        return "patientDetailView";
    }
    
}
