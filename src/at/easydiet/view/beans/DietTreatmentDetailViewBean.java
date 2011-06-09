package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.businessobjects.NutritionProtocolBO;

@ManagedBean
@SessionScoped
public class DietTreatmentDetailViewBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentDetailViewBean.class);

    public String getTreatmentName()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getDietTreatment().getDisplayText();
    }
    
    public List<NutritionProtocolBO> getNutritionProtocols()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getNutritionProtocols();        
    }
    
    public String createNutritionProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(ControllerBean.getDietTreatmentDetailViewController().getDietTreatment());
        return "createNutritionProtocolView";
    }

}
