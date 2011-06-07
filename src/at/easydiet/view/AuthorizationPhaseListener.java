package at.easydiet.view;

import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import at.easydiet.view.beans.UserBean;

/**
 * This JSF PhaseListener checks for unauthorized access ot the website. If the
 * user is not logged in, he will get redirected to the login page. The login
 * and registration pages are available to the public.
 * @author Daniel
 * 
 */
public class AuthorizationPhaseListener implements PhaseListener
{
    /**
     * 
     */
    private static final long        serialVersionUID = -158797039543409856L;

    /**
     * A list of pages, available to the public
     */
    private static final Set<String> PUBLIC_SITES     = new HashSet<String>();

    static
    {
        // add login and registration pagese
        PUBLIC_SITES.add("Login");
    }

    /**
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void afterPhase(PhaseEvent event)
    {
        // check if public available site
        FacesContext ctx = event.getFacesContext();
        boolean publicSite = false;
        String currentSite = ctx.getViewRoot().getViewId().toLowerCase();

        for (String site : PUBLIC_SITES)
        {
            if (currentSite.toLowerCase().contains(site.toLowerCase()))
            {
                publicSite = true;
                break;
            }
        }

        if (!publicSite)
        {
            // check if logged in
            UserBean bean = BeanResolver.resolveBean(
                    FacesContext.getCurrentInstance(), "userBean");
            if (bean == null || !bean.isAuthenticated())
            {
                // redirect if not
                ctx.addMessage(
                        null,
                        new FacesMessage(
                                "Sie müssen sich einloggen um diese Seite betreten zu können!"));
                ctx.getApplication().getNavigationHandler()
                        .handleNavigation(ctx, null, "login");
            }
        }
    }

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void beforePhase(PhaseEvent event)
    {}

    /**
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RESTORE_VIEW;
    }
}
