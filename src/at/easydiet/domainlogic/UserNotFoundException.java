package at.easydiet.domainlogic;

import javax.naming.AuthenticationException;

public class UserNotFoundException extends AuthenticationException
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
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
     * @param explanation
     */
    public UserNotFoundException(String explanation)
    {
        super(explanation);
    }
}
