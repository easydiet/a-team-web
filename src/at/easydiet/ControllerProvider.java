package at.easydiet;

import at.easydiet.businesslogic.BusinessLogicProvider;
import at.easydiet.businesslogic.CreateNutritionProtocolController;
import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.domainlogic.DietParameterController;
import at.easydiet.domainlogic.DietParameterUnitController;
import at.easydiet.domainlogic.DomainLogicProvider;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.PatientLikeGradeController;
import at.easydiet.domainlogic.SystemUserController;
import at.easydiet.domainlogic.TimeSpanController;

public class ControllerProvider
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerProvider.class);

    private BusinessLogicProvider _businessProvider;
    private DomainLogicProvider _domainProvider;
    
    public ControllerProvider()
    {
        _domainProvider = new DomainLogicProvider(this);
        _businessProvider = new BusinessLogicProvider(this);
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

    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getDietParameterController()
     */
    public DietParameterController getDietParameterController()
    {
        return _domainProvider.getDietParameterController();
    }

    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getDietParameterUnitController()
     */
    public DietParameterUnitController getDietParameterUnitController()
    {
        return _domainProvider.getDietParameterUnitController();
    }

    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getParameterDefinitionUnitController()
     */
    public ParameterDefinitionUnitController getParameterDefinitionUnitController()
    {
        return _domainProvider.getParameterDefinitionUnitController();
    }

    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getPatientLikeGradeController()
     */
    public PatientLikeGradeController getPatientLikeGradeController()
    {
        return _domainProvider.getPatientLikeGradeController();
    }

    /**
     * @return
     * @see at.easydiet.domainlogic.DomainLogicProvider#getTimeSpanController()
     */
    public TimeSpanController getTimeSpanController()
    {
        return _domainProvider.getTimeSpanController();
    }
    
    
}
