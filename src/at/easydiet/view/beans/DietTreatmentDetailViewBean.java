package at.easydiet.view.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;

/**
 * Bean for the DietTreatmentDetailView
 */
@ManagedBean
@SessionScoped
public class DietTreatmentDetailViewBean
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentDetailViewBean.class);

    /**
     * Gets the name of the {@link DietTreatmentBO}
     * @return Name of the {@link DietTreatmentBO}
     */
    public String getTreatmentName()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getDietTreatment().getDisplayText();
    }
    
    /**
     * Gets {@link NutritionProtocolBO}s
     * @return List of all {@link NutritionProtocolBO}s
     */
    public List<NutritionProtocolBO> getNutritionProtocols()
    {
        return ControllerBean.getDietTreatmentDetailViewController().getNutritionProtocols();        
    }
    
    /**
     * Create a new {@link NutritionProtocolBO}
     * @return destination
     */
    public String createNutritionProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(ControllerBean.getDietTreatmentDetailViewController().getDietTreatment());
        return "createNutritionProtocolView";
    }


}
