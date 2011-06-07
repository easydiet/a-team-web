package at.easydiet.businesslogic;

import at.easydiet.ControllerProvider;

public abstract class BusinessLogicController 
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(BusinessLogicController.class);

    private BusinessLogicProvider _currentProvider;

    /**
     * Gets the currentProvider.
     * @return the currentProvider
     */
    public BusinessLogicProvider getCurrentProvider()
    {
        return _currentProvider;
    }
    
    public ControllerProvider getRootProvider()
    {
        return _currentProvider.getRootProvider();
    }

    /** 
     * Initializes a new instance of the {@link BusinessLogicController} class. 
     * @param currentProvider
     */
    protected BusinessLogicController(BusinessLogicProvider currentProvider)
    {
        super();
        _currentProvider = currentProvider;
    }
    
}
