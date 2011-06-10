package at.easydiet.domainlogic;

import javax.naming.AuthenticationException;

/**
 * Thrown when no user is found
 */
public class UserNotFoundException extends AuthenticationException
{
    /**
     * generated serialization id
     */
    private static final long serialVersionUID = 2054713543139606791L;
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(UserNotFoundException.class);

    /**
     * Initializes a new instance of the {@link UserNotFoundException} class.
     */
    public UserNotFoundException()
    {
        super();
    }

    /**
     * Initializes a new instance of the {@link UserNotFoundException} class.
     * @param explanation error message
     */
    public UserNotFoundException(String explanation)
    {
        super(explanation);
    }
}
