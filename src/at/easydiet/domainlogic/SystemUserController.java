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

    /**
     * Gets the {@link SystemUserBO} by it's username
     * @param username The username of the {@link SystemUserBO}
     * @return The {@link SystemUserBO}
     */
    public SystemUserBO getSystemUserByUsername(String username)
    {
        SystemUserDAO dao = DAOFactory.getInstance().getSystemUserDAO();
        SystemUser user = dao.findByUsername(username);
        return user == null ? null : new SystemUserBO(user);
    }

    /**
     * Tries to log in a user
     * @param username username of the user
     * @param password non-md5 password
     * @throws InvalidPasswordException Thrown when an invalid password is detected
     * @throws UserNotFoundException Thrown when the user is not found
     */
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

    /**
     * Logout the current user
     */
    public void logout()
    {
        onUserLogout(_currentUser);
        _currentUser = null;
    }

    /**
     * Checks whether the user is authenticated or not
     * @return True if the user is logged in.
     */
    public boolean isAuthenticated()
    {
        return _currentUser != null;
    }
    
    
    /**
     * Implements a listener for the login
     */
    public interface SystemUserLoginListener
    {
        /**
         * When the user logs in
         * @param user User who logs in
         */
        public void onUserLogin(SystemUserBO user);
        
        /**
         * When the user logs out
         * @param user User who logs out
         */
        public void onUserLogout(SystemUserBO user);
        
        /**
         * Adapter pattern
         */
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
    
    /**
     * Add a login listener
     * @param listener Listener to add
     */
    public void addLoginListener(SystemUserLoginListener listener)
    {
        _loginListeners.add(listener);
    }
    
    /**
     * Remove a login listener
     * @param listener Listener to remove
     */
    public void removeLoginListener(SystemUserLoginListener listener)
    {
        _loginListeners.remove(listener);
    }
}
