package at.easydiet.businessobjects;

import java.util.List;

public interface IDietParameterizable
{
    public String getDisplayText();

    public List<? extends DietParameterTemplateBO> getDietParameters();

    public void addDietParameters(DietParameterTemplateBO parameter);

    public void removeDietParameters(DietParameterTemplateBO parameter);
    
    //public List<DietParameterTemplateBO> getPossibleParameters();
}