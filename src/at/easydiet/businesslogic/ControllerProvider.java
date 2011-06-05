package at.easydiet.businesslogic;

public class ControllerProvider
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerProvider.class);

    private CreateNutritionProtocolController   _createNutritionProtocolController;
    private DashboardViewController             _dashboardViewController;
    private DietPlanEditingController           _dietPlanEditingController;
    private DietTreatmentDetailViewController   _dietTreatmentDetailViewController;
    private PatientDetailViewController         _patientDetailViewController;

    /**
     * Gets the createNutritionProtocolController.
     * 
     * @return the createNutritionProtocolController
     */
    public CreateNutritionProtocolController getCreateNutritionProtocolController()
    {
        if(_createNutritionProtocolController == null)
        {
            _createNutritionProtocolController = CreateNutritionProtocolController.newInstance();
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
            _dashboardViewController = DashboardViewController.newInstance();
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
            _dietPlanEditingController = DietPlanEditingController.newInstance();
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
            _dietTreatmentDetailViewController = DietTreatmentDetailViewController.newInstance();
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
            _patientDetailViewController = PatientDetailViewController.newInstance();
        }
        return _patientDetailViewController;
    }

}
