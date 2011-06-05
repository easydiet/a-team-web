package at.easydiet.view;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 * A utlity for resolving beans.
 */
public class BeanResolver
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(BeanResolver.class);

    /**
     * @param string
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T resolveBean(FacesContext ctx, String beanName)
    {
        ELContext elContext = ctx.getELContext();
        return (T) ctx.getApplication().getELResolver()
                .getValue(elContext, null, beanName);
    }
}
