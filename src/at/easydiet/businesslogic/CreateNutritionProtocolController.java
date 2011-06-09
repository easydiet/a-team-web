package at.easydiet.businesslogic;

import java.util.Date;
import java.util.List;

import javassist.NotFoundException;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.PlanTypeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.model.DietPlan;
import at.easydiet.model.PlanType;
import at.easydiet.dao.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.NutritionProtocolDAO;
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

    private RecipeSearchController              _recipeSearchController;

    /**
     * Gets a new instance of this class.
     * 
     * @return a new instance for the current thread.
     */
    static CreateNutritionProtocolController newInstance(
            BusinessLogicProvider currentProvider)
    {
        return new CreateNutritionProtocolController(currentProvider);
    }

    /**
     * Initializes a new instance of the
     * {@link CreateNutritionProtocolController} class.
     * 
     * @param currentProvider
     */
    private CreateNutritionProtocolController(
            BusinessLogicProvider currentProvider)
    {
        super(currentProvider);
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
    

    public List<MealBO> getMealsOfDay(Date day, DietTreatmentBO diet) throws NotFoundException
    {
        for(DietPlanBO dp : diet.getDietPlans())
        {
            if(dp.getStart().compareTo(day) <= 0 && dp.getEnd().compareTo(day) >= 0 && dp.getPlanType() != PlanTypeBO.NUTRITION_PROTOCOL)
            {
                for(TimeSpanBO ts : dp.getTimeSpans())
                {
                    if(ts.getStart().compareTo(day) <= 0 && ts.getEnd().compareTo(day) >= 0)
                    {
                        return ts.getMeals();
                    }
                }
            }
        }
        throw new NotFoundException("No Meal found for this date on Diet: " + diet.getName()+", on Day: "+day);
    }
    
    public TimeSpanBO getTimeSpanOfDay(TimeSpanBO timeSpan, DietTreatmentBO diet) throws NotFoundException
    {
        for(DietPlanBO dp : diet.getDietPlans())
        {
            if(dp.getStart().compareTo(timeSpan.getStart()) <= 0 && dp.getEnd().compareTo(timeSpan.getEnd()) >= 0 && dp.getPlanType() != PlanTypeBO.NUTRITION_PROTOCOL)
            {
                for(TimeSpanBO ts : dp.getTimeSpans())
                {
                    if(ts.getStart().compareTo(timeSpan.getStart()) <= 0 && ts.getEnd().compareTo(timeSpan.getEnd()) >= 0)
                    {
                        for(MealBO me : ts.getMeals()){
                            timeSpan.addMeals(me);
                        }
                        return timeSpan;
                    }
                }
            }
        }
        throw new NotFoundException("No Meal found for this date on Diet: " + diet.getName()+", on Day: "+timeSpan.getStart());
    }

    public void createNewTimeSpan(DietPlanBO dietPlan)
    {
        TimeSpanBO span = new TimeSpanBO();
        span.setDietPlan(dietPlan);
        
    }

    public RecipeSearchController getRecipeSearchController()
    {
        return _recipeSearchController;
    }    
    
    public boolean saveDietPlan()
    {
        validateDietPlan(true);

        if (getErrors().size() > 0) return false;

        SimpleDateFormat formatter = new SimpleDateFormat(
                EasyDietApplication.DATETIME_FORMAT);
        // generate a good name if it's a new plan
        if (getDietPlan().getDietPlanId() <= 0)
        {
        	getDietPlan().setCreatedOn(new Date());
            String name = String.format("Ernährungsprotokoll vom %s",
                    formatter.format(getDietPlan().getCreatedOn()));
            getDietPlan().setName(name);
        }

        // update creator
        getDietPlan().setCreator(getRootProvider().getSystemUserController().getCurrentUser());

        HibernateUtil.closeSession();
        try
        {
            HibernateUtil.currentSession().beginTransaction();
            NutritionProtocolDAO dao = DAOFactory.getInstance().getNutritionProtocolDAO();
            dao.makePersistent(getDietPlan().getModel());
            HibernateUtil.currentSession().getTransaction().commit();
            getRootProvider().getDietTreatmentDetailViewController().refresh();
            return true;
        }
        catch (HibernateException e)
        {
            LOG.error("Could not save NP", e);
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }    
}
