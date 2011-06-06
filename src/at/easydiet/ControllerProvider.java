package at.easydiet;

import at.easydiet.businesslogic.BusinessLogicProvider;
import at.easydiet.businesslogic.CreateNutritionProtocolController;
import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.domainlogic.DomainLogicProvider;
import at.easydiet.domainlogic.SystemUserController;

public class ControllerProvider
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerProvider.class);

    private BusinessLogicProvider _businessProvider;
    private DomainLogicProvider _domainProvider;
    
    public ControllerProvider()
    {
        _businessProvider = new BusinessLogicProvider(this);
        _domainProvider = new DomainLogicProvider(this);
    }
    /**
     * @return
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getCreateNutritionProtocolController()
     */
    public CreateNutritionProtocolController getCreateNutritionProtocolController()
    {
        return _businessProvider.getCreateNutritionProtocolController();
    }
    /**
     * @return
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDashboardViewController()
     */
    public DashboardViewController getDashboardViewController()
    {
        return _businessProvider.getDashboardViewController();
    }
    /**
     * @return
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDietPlanEditingController()
     */
    public DietPlanEditingController getDietPlanEditingController()
    {
        return _businessProvider.getDietPlanEditingController();
    }
    /**
     * @return
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDietTreatmentDetailViewController()
     */
    public DietTreatmentDetailViewController getDietTreatmentDetailViewController()
    {
        return _businessProvider.getDietTreatmentDetailViewController();
    }
    /**
     * @return
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getPatientDetailViewController()
     */
    public PatientDetailViewController getPatientDetailViewController()
    {
        return _businessProvider.getPatientDetailViewController();
    }
    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getSystemUserController()
     */
    public SystemUserController getSystemUserController()
    {
        return _domainProvider.getSystemUserController();
    }
    
    
}
