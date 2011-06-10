package at.easydiet;

/**
 * Root class for ControllerProvider. Stores controllers.
 *
 */
public abstract class ControllerProviderBase
{
    @SuppressWarnings({ "unused" })
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ControllerProviderBase.class);

    private ControllerProvider _rootProvider;

    /**
     * Gets the rootProvider.
     * @return the rootProvider
     */
    public ControllerProvider getRootProvider()
    {
        return _rootProvider;
    }

    /** 
     * Initializes a new instance of the {@link ControllerProviderBase} class. 
     * @param rootProvider The root provider
     */
    protected ControllerProviderBase(ControllerProvider rootProvider)
    {
        super();
        _rootProvider = rootProvider;
    }
    
    
}
