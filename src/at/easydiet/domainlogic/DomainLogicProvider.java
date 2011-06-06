package at.easydiet.domainlogic;

import at.easydiet.ControllerProvider;
import at.easydiet.ControllerProviderBase;

public class DomainLogicProvider extends ControllerProviderBase
{
    public DomainLogicProvider(ControllerProvider rootProvider)
    {
        super(rootProvider);
    }

    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DomainLogicProvider.class);

    private SystemUserController _systemUserController;
    
    public SystemUserController getSystemUserController()
    {
        if(_systemUserController == null)
        {
            _systemUserController = SystemUserController.newInstance();
        }
        return _systemUserController;
    }
}
