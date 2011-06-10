package at.easydiet.domainlogic;

import javax.naming.AuthenticationException;

/**
 * Thrown when an invalid password is found
 */
public class InvalidPasswordException extends AuthenticationException
{
    private static final long serialVersionUID = -5618706735581102928L;
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(InvalidPasswordException.class);

    /** 
     * Initializes a new instance of the {@link InvalidPasswordException} class. 
     */
    public InvalidPasswordException()
    {
        super();
    }

    /** 
     * Initializes a new instance of the {@link InvalidPasswordException} class. 
     * @param explanation error message
     */
    public InvalidPasswordException(String explanation)
    {
        super(explanation);
    }

    
    
}
