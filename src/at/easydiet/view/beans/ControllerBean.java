package at.easydiet.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import at.easydiet.ControllerProvider;
import at.easydiet.businesslogic.CreateNutritionProtocolController;
import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.SystemUserController;
import at.easydiet.view.BeanResolver;

/**
 * This bean provides a list of controller per session.
 */
@ManagedBean
@SessionScoped
public class ControllerBean
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerBean.class);

    private ControllerProvider _provider;
    
    /** 
     * Initializes a new instance of the {@link ControllerBean} class. 
     */
    public ControllerBean()
    {
        _provider = new ControllerProvider();
    }
    
    /**
     * @return The {@link SystemUserController}
     * @see at.easydiet.domainlogic.DomainLogicProvider#getSystemUserController()
     */
    public static SystemUserController getSystemUserController()
    {
        return getInstance()._provider.getSystemUserController();
    }

    /**
     * @return The {@link CreateNutritionProtocolController}
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getCreateNutritionProtocolController()
     */
    public static CreateNutritionProtocolController getCreateNutritionProtocolController()
    {
        return getInstance()._provider.getCreateNutritionProtocolController();
    }
    
    /**
     * @return The {@link DashboardViewController}
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDashboardViewController()
     */
    public static DashboardViewController getDashboardViewController()
    {
        return getInstance()._provider.getDashboardViewController();
    }
    
    /**
     * @return The {@link DietPlanEditingController}
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDietPlanEditingController()
     */
    public static DietPlanEditingController getDietPlanEditingController()
    {
        return getInstance()._provider.getDietPlanEditingController();
    }
    
    /**
     * @return The {@link DietTreatmentDetailViewController}
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getDietTreatmentDetailViewController()
     */
    public static DietTreatmentDetailViewController getDietTreatmentDetailViewController()
    {
        return getInstance()._provider.getDietTreatmentDetailViewController();
    }
    
    /**
     * @return The {@link PatientDetailViewController}
     * @see at.easydiet.businesslogic.BusinessLogicProvider#getPatientDetailViewController()
     */
    public static PatientDetailViewController getPatientDetailViewController()
    {
        return getInstance()._provider.getPatientDetailViewController();
    }
    
    
    /**
     * Get an Instance of this object.
     * @return Instance of {@link ControllerBean}
     */
    public static ControllerBean getInstance()
    {
        return BeanResolver.resolveBean(FacesContext.getCurrentInstance(), "controllerBean");
    }

    /**
     * Get the {@link ParameterDefinitionUnitController}
     * @return The {@link ParameterDefinitionUnitController}
     */
    public static ParameterDefinitionUnitController getParameterDefinitionUnitController()
    {
        return getInstance()._provider.getParameterDefinitionUnitController();
    }
}
