package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.TimeSpanBO;

/**
 * This bean handles the communication between the UI and the controller for creating new nutritionprocotols
 * @author Daniel
 *
 */
@ManagedBean
@SessionScoped
public class CreateNutritionProtocolBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateNutritionProtocolBean.class);
    
    public CreateNutritionProtocolBean()
    {
        createNewProtocol();
    }
    
    public NutritionProtocolBO getNutritionProtocol()
    {
        if(ControllerBean.getCreateNutritionProtocolController().getDietPlan() == null)
        {
            createNewProtocol();
        }
        return ControllerBean.getCreateNutritionProtocolController().getDietPlan();
    }
    
    public List<TimeSpanBO> getTimeSpans()
    {
    	return ControllerBean.getCreateNutritionProtocolController().getDietPlan().getTimeSpans();	
    }

    private void createNewProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(ControllerBean.getDietTreatmentDetailViewController().getDietTreatment());
        ControllerBean.getCreateNutritionProtocolController().refresh();
    }
   
    public void addNewTimespan()
    {
    	ControllerBean.getCreateNutritionProtocolController().createTimeSpan();
    }
   
}
