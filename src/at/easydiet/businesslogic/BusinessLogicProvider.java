package at.easydiet.businesslogic;

import at.easydiet.ControllerProvider;
import at.easydiet.ControllerProviderBase;

public class BusinessLogicProvider extends ControllerProviderBase
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(BusinessLogicProvider.class);

    private CreateNutritionProtocolController   _createNutritionProtocolController;
    private DashboardViewController             _dashboardViewController;
    private DietPlanEditingController           _dietPlanEditingController;
    private DietTreatmentDetailViewController   _dietTreatmentDetailViewController;
    private PatientDetailViewController         _patientDetailViewController;
    

    /** 
     * Initializes a new instance of the {@link BusinessLogicProvider} class. 
     * @param rootProvider
     */
    public BusinessLogicProvider(ControllerProvider rootProvider)
    {
        super(rootProvider);
    }

    /**
     * Gets the createNutritionProtocolController.
     * 
     * @return the createNutritionProtocolController
     */
    public CreateNutritionProtocolController getCreateNutritionProtocolController()
    {
        if(_createNutritionProtocolController == null)
        {
            _createNutritionProtocolController = CreateNutritionProtocolController.newInstance(this);
        }
        return _createNutritionProtocolController;
    }

    /**
     * Gets the dashboardViewController.
     * 
     * @return the dashboardViewController
     */
    public DashboardViewController getDashboardViewController()
    {
        if(_dashboardViewController == null)
        {
            _dashboardViewController = DashboardViewController.newInstance(this);
        }
        return _dashboardViewController;
    }

    /**
     * Gets the dietPlanEditingController.
     * 
     * @return the dietPlanEditingController
     */
    public DietPlanEditingController getDietPlanEditingController()
    {
        if(_dietPlanEditingController == null)
        {
            _dietPlanEditingController = DietPlanEditingController.newInstance(this);
        }
        return _dietPlanEditingController;
    }

    /**
     * Gets the dietTreatmentDetailViewController.
     * 
     * @return the dietTreatmentDetailViewController
     */
    public DietTreatmentDetailViewController getDietTreatmentDetailViewController()
    {
        if(_dietTreatmentDetailViewController == null)
        {
            _dietTreatmentDetailViewController = DietTreatmentDetailViewController.newInstance(this);
        }
        return _dietTreatmentDetailViewController;
    }

    /**
     * Gets the patientDetailViewController.
     * 
     * @return the patientDetailViewController
     */
    public PatientDetailViewController getPatientDetailViewController()
    {
        if(_patientDetailViewController == null)
        {
            _patientDetailViewController = PatientDetailViewController.newInstance(this);
            getRootProvider().getSystemUserController().addLoginListener(_patientDetailViewController);
        }
        return _patientDetailViewController;
    }

}
