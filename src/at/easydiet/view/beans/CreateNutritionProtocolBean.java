package at.easydiet.view.beans;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.domainlogic.RecipeSearchController;

/**
 * This bean handles the communication between the UI and the controller for creating new nutritionprocotols
 * @author Daniel
 *
 */
@ManagedBean
@SessionScoped
public class CreateNutritionProtocolBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CreateNutritionProtocolBean.class);
    
    private MealBO _currentMeal;
    private RecipeSearchController _recipeSearch = new RecipeSearchController();
    private RecipeBO[] _selectedRecipes;
    
    
    
    
	public RecipeBO[] getSelectedRecipes() {
		return _selectedRecipes;
	}

	public void setSelectedRecipes(RecipeBO[] selectedRecipes) {
		this._selectedRecipes = selectedRecipes;
	}

	public RecipeSearchController getRecipeSearch() {
		return _recipeSearch;
	}

	public MealBO getCurrentMeal() {
		return _currentMeal;
	}

	public void setCurrentMeal(MealBO currentMeal) {
		this._currentMeal = currentMeal;
	}

	public CreateNutritionProtocolBean()
    {
        createNewProtocol();
    }
    
    public NutritionProtocolBO getNutritionProtocol()
    {
        if(ControllerBean.getCreateNutritionProtocolController().getDietPlan() == null)
        {
            createNewProtocol();
        }
        return ControllerBean.getCreateNutritionProtocolController().getDietPlan();
    }
    
    public List<TimeSpanBO> getTimeSpans()
    {
    	return ControllerBean.getCreateNutritionProtocolController().getDietPlan().getTimeSpans();	
    }

    private void createNewProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(ControllerBean.getDietTreatmentDetailViewController().getDietTreatment());
        ControllerBean.getCreateNutritionProtocolController().refresh();
    }
   
    public void addNewTimespan()
    {
    	ControllerBean.getCreateNutritionProtocolController().createTimeSpan();
    }
    
    public void addNewMeal(ActionEvent e)
    {
    	TimeSpanBO timespan = (TimeSpanBO) e.getComponent().getAttributes().get("timespan");
    	ControllerBean.getCreateNutritionProtocolController().createMeal(timespan);
    }
    
    public void addNewMealLine()
    {
    	if(_selectedRecipes == null) return;
    	MealBO meal = getCurrentMeal();
    	for (RecipeBO recipe : _selectedRecipes) 
    	{
    	  	ControllerBean.getCreateNutritionProtocolController().addRecipeToMeal(meal, recipe);
		}
   }
}
