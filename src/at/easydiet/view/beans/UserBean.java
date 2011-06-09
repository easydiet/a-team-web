package at.easydiet.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import at.easydiet.businessobjects.SystemUserBO;

@ManagedBean
@SessionScoped
public class UserBean
{
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(UserBean.class);

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

    public String doLogout() throws ServletException
    {
        ControllerBean.getSystemUserController().logout();
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "login";
    }

    public boolean isAuthenticated()
    {
        return ControllerBean.getSystemUserController().isAuthenticated();
    }

}