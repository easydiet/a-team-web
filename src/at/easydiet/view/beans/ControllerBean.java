package at.easydiet.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import at.easydiet.businesslogic.ControllerProvider;
import at.easydiet.businesslogic.CreateNutritionProtocolController;
import at.easydiet.businesslogic.DashboardViewController;
import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.DietTreatmentDetailViewController;
import at.easydiet.businesslogic.PatientDetailViewController;
import at.easydiet.view.BeanResolver;

/**
 * This bean provides a list of controller per session.
 */
@ManagedBean
@SessionScoped
public class ControllerBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerBean.class);

    private ControllerProvider _controllerProvider;
    
    public ControllerBean()
    {
        _controllerProvider = new ControllerProvider();
    }

    /**
     * @return
     * @see at.easydiet.businesslogic.ControllerProvider#getCreateNutritionProtocolController()
     */
    public static CreateNutritionProtocolController getCreateNutritionProtocolController()
    {
        return getInstance()._controllerProvider.getCreateNutritionProtocolController();
    }
    
    /**
     * @return
     * @see at.easydiet.businesslogic.ControllerProvider#getDashboardViewController()
     */
    public static DashboardViewController getDashboardViewController()
    {
        return getInstance()._controllerProvider.getDashboardViewController();
    }
    
    /**
     * @return
     * @see at.easydiet.businesslogic.ControllerProvider#getDietPlanEditingController()
     */
    public static DietPlanEditingController getDietPlanEditingController()
    {
        return getInstance()._controllerProvider.getDietPlanEditingController();
    }
    
    /**
     * @return
     * @see at.easydiet.businesslogic.ControllerProvider#getDietTreatmentDetailViewController()
     */
    public static DietTreatmentDetailViewController getDietTreatmentDetailViewController()
    {
        return getInstance()._controllerProvider.getDietTreatmentDetailViewController();
    }
    
    /**
     * @return
     * @see at.easydiet.businesslogic.ControllerProvider#getPatientDetailViewController()
     */
    public static PatientDetailViewController getPatientDetailViewController()
    {
        return getInstance()._controllerProvider.getPatientDetailViewController();
    }
    
    public static ControllerBean getInstance()
    {
        return BeanResolver.resolveBean(FacesContext.getCurrentInstance(), "controllerBean");
    }
}
