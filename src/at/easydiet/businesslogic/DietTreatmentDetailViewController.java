package at.easydiet.businesslogic;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.NutritionProtocolDAO;
import at.easydiet.model.NutritionProtocol;

/**
 * This controller provides data and functions for the
 * DietTreatmentDetailView
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
    private List<NutritionProtocolBO> 									_nutritionProtocols;

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
     * @param provider The {@link BusinessLogicProvider} which holds this instance
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
     * @param dietTreatment the new {@link DietTreatmentBO}
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
        _dietTreatment.updatePatientStatesCache();
        _dietTreatment.updateSystemUsersCache();
        
        // load nutritionprotocols
        NutritionProtocolDAO dao = DAOFactory.getInstance().getNutritionProtocolDAO();
        List<NutritionProtocol> nps = dao.findByDietTreatment(_dietTreatment.getModel());
        _nutritionProtocols = new ArrayList<NutritionProtocolBO>();
        for (NutritionProtocol nutritionProtocol : nps) 
        {
        	_nutritionProtocols.add(new NutritionProtocolBO(nutritionProtocol));
		}
        
    }
    
    /**
     * Gets a list of all {@link NutritionProtocolBO}s
     * @return List of {@link NutritionProtocolBO} of the current {@link DietTreatmentBO}
     */
    public List<NutritionProtocolBO> getNutritionProtocols() 
    {
		return _nutritionProtocols;
	}

    /**
     * Refreshes the currently loaded diet treatment and it's data.
     */
    public void refresh()
    {
        LOG.trace("Refreshing DietTreatment");
        DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
        dao.refresh(_dietTreatment.getModel());
        reloadTreatmentData();
    }
}
