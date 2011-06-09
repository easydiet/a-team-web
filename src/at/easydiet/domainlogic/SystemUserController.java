package at.easydiet.domainlogic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import at.easydiet.businessobjects.SystemUserBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;
import at.easydiet.model.SystemUser;

/**
 * Provides data and methods for handling system users
 */
public class SystemUserController extends DomainLogicController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(SystemUserController.class);

    /**
     * Gets a new instance of this class.
     * @return a new instance for the current thread.
     */
    static SystemUserController newInstance(DomainLogicProvider provider)
    {
        return new SystemUserController(provider);
    }

    /**
     * Stores the current logged in {@link SystemUserBO}
     */
    private SystemUserBO _currentUser;

    /**
     * Gets the current logged in {@link SystemUserBO}
     * 
     * @return {@link SystemUserBO} currently logged in
     */
    public SystemUserBO getCurrentUser()
    {
        return _currentUser;
    }

    /**
     * Initializes a new instance of the {@link SystemUserController} class.
     */
    private SystemUserController(DomainLogicProvider provider)
    {
        super(provider);
    }
    /**
     * Get all registered {@link SystemUserBO}s
     * 
     * @return List of all registered {@link SystemUserBO}s
     */
    public List<?> getAllUsers()
    {
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();

        List<SystemUserBO> list = new ArrayList<SystemUserBO>();

        for (SystemUser bo : dao.findAll())
        {
            list.add(new SystemUserBO(bo));
        }

        return list;
    }

    public SystemUserBO getSystemUserByUsername(String username)
    {
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();
        SystemUser user = dao.findByUsername(username);
        return user == null ? null : new SystemUserBO(user);
    }

    public void login(String username, String password) throws InvalidPasswordException, UserNotFoundException
    {
        SystemUserBO user = getSystemUserByUsername(username);
        if(user == null)
        { 
            throw new UserNotFoundException();
        }
        
        if(!md5(password).equalsIgnoreCase(user.getPassword()))
        {
            throw new InvalidPasswordException();
        }
        
        _currentUser = user;
        onUserLogin(_currentUser);
    }

    private static String md5(String input)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] data = input.getBytes();
            m.update(data, 0, data.length);
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032X", i);
        }
        catch (Exception e)
        {
            return "00000000000000000000000000000000";
        }
    }

    public void logout()
    {
        onUserLogout(_currentUser);
        _currentUser = null;
    }

    public boolean isAuthenticated()
    {
        return _currentUser != null;
    }
    
    public interface SystemUserLoginListener
    {
        public void onUserLogin(SystemUserBO user);
        public void onUserLogout(SystemUserBO user);
        
        public class Adapter implements SystemUserLoginListener
        {
            @Override
            public void onUserLogin(SystemUserBO user)
            {}

            @Override
            public void onUserLogout(SystemUserBO user)
            {}
        }
    }
    private List<SystemUserLoginListener> _loginListeners = new ArrayList<SystemUserLoginListener>();
    
    private void onUserLogin(SystemUserBO user)
    {
        for (SystemUserLoginListener listener : _loginListeners)
        {
            if(listener != null)
            {
                listener.onUserLogin(user);
            }
        }
    }
    private void onUserLogout(SystemUserBO user)
    {
        for (SystemUserLoginListener listener : _loginListeners)
        {
            if(listener != null)
            {
                listener.onUserLogout(user);
            }
        }
    }
    
    public void addLoginListener(SystemUserLoginListener listener)
    {
        _loginListeners.add(listener);
    }
    
    public void removeLoginListener(SystemUserLoginListener listener)
    {
        _loginListeners.remove(listener);
    }
}
