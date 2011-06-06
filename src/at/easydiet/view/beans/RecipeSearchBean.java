package at.easydiet.view.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.domainlogic.RecipeSearchController;

@ManagedBean
@SessionScoped
public class RecipeSearchBean
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(RecipeSearchBean.class);
    
    
    private RecipeBO[] _selectedRecipes;

    public RecipeSearchBean()
    {
        getRecipeSearchController().doSearch();
    }

    /**
     * Gets all {@link RecipeBO}s matching the query
     * 
     * @return List of filtered {@link RecipeBO}s
     */
    public List<RecipeBO> getRecipes()
    {
        return getRecipeSearchController().getRecipes();
    }

    /**
     * Gets the queryString.
     * 
     * @return the queryString
     */
    public String getQueryString()
    {
        return getRecipeSearchController().getQueryString();
    }

    /**
     * Sets the queryString.
     * 
     * @param queryString
     *            the queryString to set
     */
    public void setQueryString(String queryString)
    {
        getRecipeSearchController().setQueryString(queryString);
    }

    /**
     * Start the search
     */
    public void doSearch()
    {
        getRecipeSearchController().doSearch();
    }
    
    public RecipeBO[] getSelectedRecipes()
    {
        return _selectedRecipes;
    }
    
    
    public void setSelectedRecipes(RecipeBO[] recipes)
    {
        _selectedRecipes = recipes;
    }
    
    private RecipeSearchController getRecipeSearchController()
    {
        return ControllerBean.getCreateNutritionProtocolController().getRecipeSearchController();
    }
}
