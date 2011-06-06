package at.easydiet.businesslogic;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.domainlogic.RecipeSearchController;

/**
 * This Controller handles the Creation of NutritionProtocols
 * 
 * @author Daniel
 * 
 */
public class CreateNutritionProtocolController extends
        DietPlanEditingController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateNutritionProtocolController.class);
    
    private RecipeSearchController _recipeSearchController;

    /**
     * Gets a new instance of this class.
     * @return a new instance for the current thread.
     */
    static CreateNutritionProtocolController newInstance()
    {
        return new CreateNutritionProtocolController();
    }

    /**
     * Initializes a new instance of the
     * {@link CreateNutritionProtocolController} class.
     */
    protected CreateNutritionProtocolController()
    {
        _recipeSearchController = new RecipeSearchController();
        // hidden
    }

    @Override
    public void validateDietPlan(boolean checkForEmpty)
    {
        // No validateion
    }

    @Override
    public void createNew(DietTreatmentBO dietTreatment)
    {
        DietPlanBO dietPlan = new NutritionProtocolBO();
        dietPlan.setDietTreatment(dietTreatment);
        setDietPlan(dietPlan);
    }
    
    @Override
    public NutritionProtocolBO getDietPlan()
    {
        return (NutritionProtocolBO) super.getDietPlan();
    }

    public RecipeSearchController getRecipeSearchController()
    {
        return _recipeSearchController;
    }
}
