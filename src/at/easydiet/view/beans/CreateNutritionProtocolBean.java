package at.easydiet.view.beans;

import java.util.Date;
import java.util.List;

import javassist.NotFoundException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;

import at.easydiet.domainlogic.RecipeSearchController;

/**
 * This bean handles the communication between the UI and the controller for
 * creating new nutritionprocotols
 */
@ManagedBean
@SessionScoped
public class CreateNutritionProtocolBean
{
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG           = org.apache.log4j.Logger
                                                                      .getLogger(CreateNutritionProtocolBean.class);

    private Date                                _date         = new Date();
    private MealBO                              _currentMeal;
    private TimeSpanBO                          _currentTimespan;

    private RecipeSearchController              _recipeSearch = new RecipeSearchController();
    private RecipeBO[]                          _selectedRecipes;

    /**
     * Get the current {@link TimeSpanBO}
     * @return The current {@link TimeSpanBO}
     */
    public TimeSpanBO getCurrentTimespan()
    {
        return _currentTimespan;
    }

    /**
     * Sets the current timespan
     * @param currentTimespan The new {@link TimeSpanBO}
     */
    public void setCurrentTimespan(TimeSpanBO currentTimespan)
    {
        this._currentTimespan = currentTimespan;
    }

    /**
     * Gets selected Recipes
     * @return Array of {@link RecipeBO}
     */
    public RecipeBO[] getSelectedRecipes()
    {
        return _selectedRecipes;
    }

    /**
     * Sets selected {@link RecipeBO}s
     * @param selectedRecipes The new array of {@link RecipeBO}s
     */
    public void setSelectedRecipes(RecipeBO[] selectedRecipes)
    {
        this._selectedRecipes = selectedRecipes;
    }

    /**
     * Gets the {@link RecipeSearchController}
     * @return The {@link RecipeSearchController}
     */
    public RecipeSearchController getRecipeSearch()
    {
        return _recipeSearch;
    }

    /**
     * Get the current {@link MealBO}
     * @return The current {@link MealBO}
     */
    public MealBO getCurrentMeal()
    {
        return _currentMeal;
    }

    /**
     * Set the current {@link MealBO}
     * @param currentMeal The new {@link MealBO}
     */
    public void setCurrentMeal(MealBO currentMeal)
    {
        this._currentMeal = currentMeal;
    }

    /**
     * Initializes a new instance of the {@link CreateNutritionProtocolBean} class.
     */
    public CreateNutritionProtocolBean()
    {
        createNewProtocol();
    }

    /**
     * Sets the Date
     * @param date The new {@link Date}
     */
    public void setDate(Date date)
    {
        _date = date;
    }

    /**
     * Gets the Date
     * @return {@link Date}
     */
    public Date getDate()
    {
        return _date;
    }

    /**
     * Gets the {@link NutritionProtocolBO}
     * @return The current {@link NutritionProtocolBO}
     */
    public NutritionProtocolBO getNutritionProtocol()
    {
        if (ControllerBean.getCreateNutritionProtocolController().getDietPlan() == null)
        {
            createNewProtocol();
        }
        return ControllerBean.getCreateNutritionProtocolController()
                .getDietPlan();
    }

    /**
     * Gets the {@link TimeSpanBO}s
     * @return List of all {@link TimeSpanBO}s
     */
    public List<TimeSpanBO> getTimeSpans()
    {
        return ControllerBean.getCreateNutritionProtocolController()
                .getDietPlan().getTimeSpans();
    }

    private void createNewProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(
                ControllerBean.getDietTreatmentDetailViewController()
                        .getDietTreatment());
        ControllerBean.getCreateNutritionProtocolController().refresh();
    }

    /**
     * Adds a new {@link TimeSpanBO}
     */
    public void addNewTimespan()
    {
        ControllerBean.getCreateNutritionProtocolController().createTimeSpan();
    }

    /**
     * Deletes a {@link TimeSpanBO}
     * @param e {@link ActionEvent}
     */
    public void deleteTimespan(ActionEvent e)
    {
        TimeSpanBO timespan = (TimeSpanBO) e.getComponent().getAttributes()
                .get("timespan");
        ControllerBean.getCreateNutritionProtocolController().deleteTimeSpan(
                timespan);
    }

    /**
     * Delete {@link MealLineBO}
     * @param e {@link ActionEvent}
     */
    public void deleteMealLine(ActionEvent e)
    {
        MealLineBO mealLine = (MealLineBO) e.getComponent().getAttributes()
                .get("mealLine");
        ControllerBean.getCreateNutritionProtocolController().removeMealLine(
                mealLine);
    }

    /**
     * Add a new {@link MealBO}
     * @param e {@link ActionEvent}
     */
    public void addNewMeal(ActionEvent e)
    {
        TimeSpanBO timespan = (TimeSpanBO) e.getComponent().getAttributes()
                .get("timespan");
        ControllerBean.getCreateNutritionProtocolController().createMeal(
                timespan);
    }

    /**
     * Delete a {@link MealBO}
     * @param e {@link ActionEvent}
     */
    public void deleteMeal(ActionEvent e)
    {
        MealBO meal = (MealBO) e.getComponent().getAttributes().get("meal");
        ControllerBean.getCreateNutritionProtocolController().deleteMeal(meal);
    }

    /**
     * Add a new {@link MealLineBO}
     */
    public void addNewMealLine()
    {
        if (_selectedRecipes == null) return;
        MealBO meal = getCurrentMeal();
        for (RecipeBO recipe : _selectedRecipes)
        {
            ControllerBean.getCreateNutritionProtocolController()
                    .addRecipeToMeal(meal, recipe);
        }
    }

    /**
     * Save the {@link NutritionProtocolBO}
     * @return Destination link
     */
    public String save()
    {
        if (ControllerBean.getCreateNutritionProtocolController()
                .saveDietPlan())
        {
            return "dietTreatmentDetailView";
        }
        return null;
    }

    /**
     * Gets {@link ParameterDefinitionUnitBO} for recipes
     * @return List of {@link ParameterDefinitionUnitBO}s
     */
    public List<ParameterDefinitionUnitBO> getRecipeUnits()
    {
        return ControllerBean.getParameterDefinitionUnitController().getUnits();
    }
    
    /**
     * Fills a new {@link TimeSpanBO} with the Meals of the corresponding {@link TimeSpanBO} from the {@link DietTreatmentBO} 
     */
    public void searchDietPlanMenues(){
        TimeSpanBO timespan = ControllerBean.getCreateNutritionProtocolController().createTimeSpan();
        timespan.setStart(_date);
        timespan.setDuration(0);
        fillTimeSpanWithMeals(timespan);
    }

    /**
     * Fill a {@link TimeSpanBO} with {@link MealBO}
     * @param timeSpan The {@link TimeSpanBO} to fill
     */
    private void fillTimeSpanWithMeals(TimeSpanBO timeSpan)
    {
        try
        {
            ControllerBean.getCreateNutritionProtocolController().fillTimeSpanWithMeals(timeSpan);
        }
        catch (NotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
