package at.easydiet.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.domainlogic.SystemUserController;

/**
 * Bean for the User
 */
@ManagedBean
@SessionScoped
public class UserBean
{
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(UserBean.class);

    /**
     * Gets the user
     * @return {@link SystemUserBO}
     */
    public SystemUserBO getUser()
    {
        return ControllerBean.getSystemUserController().getCurrentUser();
    }

    private String _username;
    private String _password;

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername()
    {
        return _username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        _username = username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword()
    {
        return _password;
    }

    /**
     * Sets the password.
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        _password = password;
    }

    /**
     * Tries to login
     * @return destination
     */
    public String doLogin()
    {
        try
        {
            ControllerBean.getSystemUserController().login(getUsername(),
                    getPassword());
            setPassword("");
            setUsername("");

            if (ControllerBean.getPatientDetailViewController().getPatient() != null)
            {
                return "patientDetailView";
            }
            else
            {
                return "dashboardView";
            }
        }
        catch (AuthenticationException e)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Login Fehler",
                            "Ungültiger Benutzername oder Passwort"));
            return "login";
        }
    }

    /**
     * Logs out the user
     * @return destination
     * @throws ServletException When something went wrong
     */
    public String doLogout() throws ServletException
    {
        ControllerBean.getSystemUserController().logout();
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "login";
    }

    /**
     * @see SystemUserController#isAuthenticated()
     * @return True if user is logged in
     */
    public boolean isAuthenticated()
    {
        return ControllerBean.getSystemUserController().isAuthenticated();
    }

}