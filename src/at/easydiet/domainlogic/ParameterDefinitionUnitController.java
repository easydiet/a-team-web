package at.easydiet.domainlogic;

import java.util.List;

import java.util.ArrayList;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.ParameterDefinitionUnitDAO;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Provides data and methods for compatibility with recipes,...
 */
public class ParameterDefinitionUnitController extends DomainLogicController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger      LOG = org.apache.log4j.Logger
                                                                 .getLogger(ParameterDefinitionUnitController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static ParameterDefinitionUnitController _singleton;

    /**
     * Get a Instance of this {@link ParameterDefinitionUnitController}
     * 
     * @return The instance of this {@link ParameterDefinitionUnitController}
     */
    static ParameterDefinitionUnitController newInstance(
            DomainLogicProvider provider)
    {
        if (_singleton == null)
        {
            _singleton = new ParameterDefinitionUnitController(provider);
        }
        return _singleton;
    }

    /**
     * Get a list of {@link ParameterDefinitionUnitBO}s that are compatible with
     * a {@link RecipeBO}
     * 
     * @param recipe
     *            The {@link RecipeBO}
     * @return List of compatible {@link ParameterDefinitionUnitBO}s
     */
    public ArrayList<ParameterDefinitionUnitBO> getUnitsCompatibleWithRecipe(
            RecipeBO recipe)
    {
        // TODO: Check for available type converters with recipe.getUnit()
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
                .getParameterDefinitionUnitDAO();
        List<ParameterDefinitionUnit> units = dao.findAll();
        ArrayList<ParameterDefinitionUnitBO> bos = new ArrayList<ParameterDefinitionUnitBO>();
        for (ParameterDefinitionUnit unit : units)
        {
            bos.add(new ParameterDefinitionUnitBO(unit));
        }
        return bos;
    }

    /**
     * Get a list of {@link ParameterDefinitionUnitBO}
     * @return List of all {@link ParameterDefinitionUnitBO}s
     */
    public List<ParameterDefinitionUnitBO> getUnits()
    {
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
                .getParameterDefinitionUnitDAO();
        List<ParameterDefinitionUnit> units = dao.findAll();
        ArrayList<ParameterDefinitionUnitBO> bos = new ArrayList<ParameterDefinitionUnitBO>();
        for (ParameterDefinitionUnit unit : units)
        {
            bos.add(new ParameterDefinitionUnitBO(unit));
        }
        return bos;
    }

    /**
     * Initializes a new instance of the
     * {@link ParameterDefinitionUnitController} class.
     */
    private ParameterDefinitionUnitController(DomainLogicProvider provider)
    {
        super(provider);
    }

    /**
     * Gets the default {@link ParameterDefinitionUnitBO}
     * 
     * @return Instance of the default {@link ParameterDefinitionUnitBO}
     */
    public ParameterDefinitionUnitBO getDefault()
    {
        return getByName("g");
    }

    /**
     * Gets the {@link ParameterDefinitionUnitBO} for the name
     * @param name The name of the {@link ParameterDefinitionUnitBO}
     * @return The {@link ParameterDefinitionUnitBO}
     */
    public ParameterDefinitionUnitBO getByName(String name)
    {
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
        .getParameterDefinitionUnitDAO();
        return new ParameterDefinitionUnitBO(dao.findByName(name));
    }
}
