package at.easydiet.view.beans;

import java.util.Date;
import java.util.List;

import javassist.NotFoundException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import at.easydiet.businesslogic.CreateNutritionProtocolController;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.businessobjects.TimeSpanBO;

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
    private Date _date;
    private List<MealBO> _meals;
    
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

    private void createNewProtocol()
    {
        ControllerBean.getCreateNutritionProtocolController().createNew(ControllerBean.getDietTreatmentDetailViewController().getDietTreatment());
        ControllerBean.getCreateNutritionProtocolController().refresh();
    }
    
    public void searchDietPlanMenues(SelectEvent e){
        Date d = (Date) e.getObject();
        TimeSpanBO timespan = ControllerBean.getCreateNutritionProtocolController().createTimeSpan();
        timespan.setStart(d);
        timespan.setDuration(0);
        fillTimeSpanWithMeals(timespan);
    }
    
    private void fillTimeSpanWithMeals(TimeSpanBO timeSpan)
    {
        try
        {
            ControllerBean.getCreateNutritionProtocolController().fillTimeSpanWithMeals(timeSpan);
//            
//            //Testen
//            for(MealBO meals2 : timeSpan.getMeals()){
//                System.out.println("getTimeSpanOfDay: "+meals2.getName());
//            }
//            if(meals == null || meals.size() == 0){
//                System.out.println("Keine Mahlzeit vorhanden!");
//            }
//            for(int i = 0; i < meals.size(); i++){
//                System.out.println("getMealsOfDay: "+meals.get(i).getName());
//            }
//            _meals = meals;
        }
        catch (NotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void searchDietPlanMenues(){
        DietTreatmentBO dt = ControllerBean.getDietTreatmentDetailViewController().getDietTreatment();
        TimeSpanBO timeSpan = new TimeSpanBO();
        timeSpan.setStart(_date);
        timeSpan.setDuration(1);
        try
        {
            List<MealBO> meals = ControllerBean.getCreateNutritionProtocolController().getMealsOfDay(_date, dt);
            timeSpan = ControllerBean.getCreateNutritionProtocolController().getTimeSpanOfDay(timeSpan, dt);
            
            //Testen
            for(MealBO meals2 : timeSpan.getMeals()){
                System.out.println("getTimeSpanOfDay: "+meals2.getName());
            }
            if(meals == null || meals.size() == 0){
                System.out.println("Keine Mahlzeit vorhanden!");
            }
            for(int i = 0; i < meals.size(); i++){
                System.out.println("getMealsOfDay: "+meals.get(i).getName());
            }
            _meals = meals;
        }
        catch (NotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void setDate(Date date){
        _date = date;
    }
    
    public Date getDate(){
        return _date;
    }
    
    public List<MealBO> getMeals(){
        return _meals;
    }
}
