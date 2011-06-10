package at.easydiet.view;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 * A utility for resolving beans.
 */
public class BeanResolver
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(BeanResolver.class);

    /**
     * Resolves a beanname to a bean
     * @param ctx The JavaFaces context
     * @param <T> The type of the bean
     * @param beanName Bean name
     * @return a bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T resolveBean(FacesContext ctx, String beanName)
    {
        ELContext elContext = ctx.getELContext();
        return (T) ctx.getApplication().getELResolver()
                .getValue(elContext, null, beanName);
    }
}
