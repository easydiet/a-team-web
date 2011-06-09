package at.easydiet.view;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.view.beans.ControllerBean;

public class DietPlanToTimeStringConverter implements Converter
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(DietPlanToTimeStringConverter.class);

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
    {
        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
    {
        DietPlanBO dietPlan = (DietPlanBO)arg2;
        SimpleDateFormat format = new SimpleDateFormat(EasyDietApplication.DATE_FORMAT);
        return String.format("Von %s bis %s", format.format(dietPlan.getStart()), format.format(dietPlan.getEnd()));
    }
}
