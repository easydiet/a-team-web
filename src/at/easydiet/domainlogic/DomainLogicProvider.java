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

    private DietParameterController _dietParameterController;
    private DietParameterUnitController _dietParameterUnitController;
    private ParameterDefinitionUnitController _parameterDefinitionUnitController;
    private PatientLikeGradeController _patientLikeGradeController;
    private SystemUserController _systemUserController;
    private TimeSpanController _timeSpanController;
    
    public SystemUserController getSystemUserController()
    {
        if(_systemUserController == null)
        {
            _systemUserController = SystemUserController.newInstance(this);
        }
        return _systemUserController;
    }

    /**
     * Gets the dietParameterController.
     * @return the dietParameterController
     */
    public DietParameterController getDietParameterController()
    {
        if(_dietParameterController == null)
        {
            _dietParameterController = DietParameterController.newInstance(this);
        }
        return _dietParameterController;
    }

    /**
     * Gets the dietParameterUnitController.
     * @return the dietParameterUnitController
     */
    public DietParameterUnitController getDietParameterUnitController()
    {
        if(_dietParameterUnitController == null)
        {
            _dietParameterUnitController = DietParameterUnitController.newInstance(this);
        }
        return _dietParameterUnitController;
    }

    /**
     * Gets the parameterDefinitionUnitController.
     * @return the parameterDefinitionUnitController
     */
    public ParameterDefinitionUnitController getParameterDefinitionUnitController()
    {
        if(_parameterDefinitionUnitController == null)
        {
            _parameterDefinitionUnitController = ParameterDefinitionUnitController.newInstance(this);
        }
        return _parameterDefinitionUnitController;
    }

    /**
     * Gets the patientLikeGradeController.
     * @return the patientLikeGradeController
     */
    public PatientLikeGradeController getPatientLikeGradeController()
    {
        if(_patientLikeGradeController == null)
        {
            _patientLikeGradeController = PatientLikeGradeController.newInstance(this);
        }
        return _patientLikeGradeController;
    }

    /**
     * Gets the timeSpanController.
     * @return the timeSpanController
     */
    public TimeSpanController getTimeSpanController()
    {
        if(_patientLikeGradeController == null)
        {
            _timeSpanController = TimeSpanController.newInstance(this);
        }
        return _timeSpanController;
    }

}
