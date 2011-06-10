package at.easydiet.businesslogic;

import at.easydiet.ControllerProvider;

/**
 * Root class for all {@link BusinessLogicController}s
 */
public abstract class BusinessLogicController 
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
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
    
    /**
     * Gets the rootProvider
     * @return the rootProvider
     */
    public ControllerProvider getRootProvider()
    {
        return _currentProvider.getRootProvider();
    }

    /** 
     * Initializes a new instance of the {@link BusinessLogicController} class. 
     * @param currentProvider the currentProvider
     */
    protected BusinessLogicController(BusinessLogicProvider currentProvider)
    {
        super();
        _currentProvider = currentProvider;
    }
    
}
