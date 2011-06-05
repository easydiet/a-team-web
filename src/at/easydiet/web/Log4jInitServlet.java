package at.easydiet.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * Initializes the log4j logger using the custom configuration.
 * @author Daniel
 * 
 */
public class Log4jInitServlet extends HttpServlet
{
    private static final long serialVersionUID = 8118956399938775241L;

    /**
     * @see javax.servlet.GenericServlet#init()
     */
    public void init()
    {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        if (file != null)
        {
            DOMConfigurator.configure(prefix + file);
        }
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {}
}