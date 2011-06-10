package at.easydiet.domainlogic;

import at.easydiet.ControllerProvider;
import at.easydiet.businesslogic.BusinessLogicController;

/**
 * Root class for all {@link DomainLogicController}s
 */
public abstract class DomainLogicController
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DomainLogicController.class);

    private DomainLogicProvider               _currentProvider;

    /**
     * Gets the currentProvider.
     * @return the currentProvider
     */
    public DomainLogicProvider getCurrentProvider()
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
    protected DomainLogicController(DomainLogicProvider currentProvider)
    {
        super();
        _currentProvider = currentProvider;
    }

}
