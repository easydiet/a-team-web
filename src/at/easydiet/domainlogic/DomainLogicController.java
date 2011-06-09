package at.easydiet.domainlogic;

import at.easydiet.ControllerProvider;
import at.easydiet.businesslogic.BusinessLogicController;

public abstract class DomainLogicController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
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

    public ControllerProvider getRootProvider()
    {
        return _currentProvider.getRootProvider();
    }

    /**
     * Initializes a new instance of the {@link BusinessLogicController} class.
     * @param currentProvider
     */
    protected DomainLogicController(DomainLogicProvider currentProvider)
    {
        super();
        _currentProvider = currentProvider;
    }

}
