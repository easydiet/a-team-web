package at.easydiet.businesslogic;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.businessobjects.PatientBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.Patient;
import at.easydiet.util.StringUtils;

/**
 * Provides data and actions for the DashboardView.
 */
public class DashboardViewController extends BusinessLogicController
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger              LOG            = org.apache.log4j.Logger
                                                                                     .getLogger(DashboardViewController.class);

    /**
     * Contains all patients
     */
    private List<PatientBO>                                   _patients      = null;

    /**
     * Patient filter criteria
     */
    private String                                            _patientFilter = "";

    /**
     * Gets a new instance of this class.
     * @return a new instance for the current thread.
     */
    static DashboardViewController newInstance(BusinessLogicProvider provider)
    {
        return new DashboardViewController(provider);
    }

    /**
     * Initializes a new instance of the
     * {@link DashboardViewController} class.
     * @param provider The {@link BusinessLogicProvider} which holds this instance
     */
    protected DashboardViewController(BusinessLogicProvider provider)
    {
        super(provider);
    }

    /**
     * Returns a list of all loaded patients. This collection is cached and
     * filtered by the currently set
     * {@link DashboardViewController#getPatientFilter()}
     * 
     * @see DashboardViewController#refreshPatients()
     * @return list of all loaded patients.
     */
    public List<PatientBO> getPatients()
    {
        if (_patients == null)
        {
            refreshPatients();
        }
        return _patients;
    }

    /**
     * Refreshes the list of loaded patients. This method will use the specified
     * filter to only load specific patients.
     */
    public void refreshPatients()
    {
        LOG.trace("Refreshing Patients");
        _patients = new ArrayList<PatientBO>();
        

        // check if a patient is logged in, he will only see himself
        String username = getRootProvider().getSystemUserController().getCurrentUser().getUsername();
        PatientBO currentPatient = getRootProvider().getPatientDetailViewController().getPatient();
        if(currentPatient != null && username.equalsIgnoreCase(currentPatient.getInsuranceNumber()))
        {
            _patients.add(getRootProvider().getPatientDetailViewController().getPatient());
        }
        else
        {    
            PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
            List<Patient> patients;
    
            if (StringUtils.isNullOrWhitespaceOnly(_patientFilter))
            {
                LOG.trace("Loading all Patients (no filtering)");
                patients = patientDao.findAll();
            }
            else
            {
                LOG.trace(String.format("Loading filtered Patients (%s)",
                        _patientFilter));
                patients = patientDao.findByQuery(_patientFilter);
            }
    
            LOG.trace("Setup loaded patients");
            for (Patient patient : patients)
            {
                _patients.add(new PatientBO(patient));
            }
        }
    }

    /**
     * Sets the filter string to only load specific patients during load. This
     * query supports several properties to mention like the InsuranceNumber and
     * the name of a Patient. This method will not trigger any patient
     * reloading.
     * 
     * @param searchString
     *            the filter string.
     */
    public void setPatientFilter(String searchString)
    {
        _patientFilter = searchString;
    }

    /**
     * Gets the currently loaded filter for loading patients.
     * 
     * @return the currently set filter.
     */
    public String getPatientFilter()
    {
        return _patientFilter;
    }
}
