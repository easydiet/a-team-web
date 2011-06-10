package at.easydiet.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.view.beans.ControllerBean;

/**
 * Converts between string and {@link ParameterDefinitionUnitBO}
 *
 */
public class StringToUnitConverter implements Converter
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(StringToUnitConverter.class);

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
    {
        return ControllerBean.getParameterDefinitionUnitController().getByName(arg2);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
    {
        return ((ParameterDefinitionUnitBO)arg2).getName();
    }
}
