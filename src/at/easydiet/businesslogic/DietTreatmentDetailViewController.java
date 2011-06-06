package at.easydiet.businesslogic;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;

/**
 * This controller provides data and functions for the
 * {@link DietTreatmentDetailView}
 */
public class DietTreatmentDetailViewController extends BusinessLogicController 
{
    /**
     * Logger for debugging
     */
    private static final org.apache.log4j.Logger                        LOG        = org.apache.log4j.Logger
                                                                                           .getLogger(DietTreatmentDetailViewController.class);

    /**
     * The current opened diet treatment
     */
    private DietTreatmentBO                                             _dietTreatment;

    /**
     * Gets a new instance of this class.
     * @return a new instance for the current thread.
     */
    static DietTreatmentDetailViewController newInstance(BusinessLogicProvider provider)
    {
        return new DietTreatmentDetailViewController(provider);
    }

    /**
     * Initializes a new instance of the
     * {@link DietTreatmentDetailViewController} class.
     */
    protected DietTreatmentDetailViewController(BusinessLogicProvider provider)
    {
        super(provider);
    }

    /**
     * Gets the currently selected {@link DietTreatmentBO} within this
     * application.
     * 
     * @return the currently selected {@link DietTreatmentBO}
     * @see PatientDetailViewController#getPatient()
     */
    public DietTreatmentBO getDietTreatment()
    {
        return _dietTreatment;
    }

    /**
     * Sets the selected {@link DietTreatmentBO} within this application.
     * 
     * @param dietTreatment
     */
    public void setDietTreatment(DietTreatmentBO dietTreatment)
    {
        _dietTreatment = dietTreatment;
        reloadTreatmentData();
    }

    /**
     * Updates the cached lists of the dietTreatment
     */
    private void reloadTreatmentData()
    {
        if (_dietTreatment == null) return;
        _dietTreatment.updateContactJournalsCache();
        _dietTreatment.updateDietParametersCache();
        _dietTreatment.updateDietPlansCache();
        _dietTreatment.updateNutritionProtocolsCache();
        _dietTreatment.updatePatientStatesCache();
        _dietTreatment.updateSystemUsersCache();
    }

    /**
     * Refreshes the currently loaded diettreamtent and it's data.
     */
    public void refresh()
    {
        LOG.trace("Refreshing DietTreatment");
        DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
        dao.refresh(_dietTreatment.getModel());
        reloadTreatmentData();
    }
}
