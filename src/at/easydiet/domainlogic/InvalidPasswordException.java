package at.easydiet.domainlogic;

import javax.naming.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
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
     * @param explanation
     */
    public InvalidPasswordException(String explanation)
    {
        super(explanation);
    }

    
    
}
