package at.easydiet.domainlogic;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.TimeSpanBO;

/**
 * Provides data and methods for handling {@link TimeSpanBO}s
 */
public class TimeSpanController extends DomainLogicProvider
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(TimeSpanController.class);

    /**
     * Get a Instance of this {@link TimeSpanController}
     * 
     * @return The instance of this {@link TimeSpanController}
     */
    static TimeSpanController newInstance(DomainLogicProvider provider)
    {
        return new TimeSpanController(provider);
    }

    /**
     * Initializes a new instance of the {@link TimeSpanController} class.
     */
    private TimeSpanController(DomainLogicProvider provider)
    {
        super(provider);
    }

    /**
     * Checks if the specified timespan collides with any other elements within
     * the patient context and returns a list of all collisions.
     * @param timespan the timespan to validate
     * @return List of collisions
     */
    public List<Object> validateCollisions(TimeSpanBO timespan)
    {
        List<Object> collisions = new ArrayList<Object>();

        // collisions with any timespans in same plan?
        List<TimeSpanBO> timeSpans = timespan.getDietPlan().getTimeSpans();
        for (TimeSpanBO other : timeSpans)
        {
            if (other == timespan
                    || (timespan.getTimeSpanId() > 0 && timespan.equals(other)))
                continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(),
                    other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }

        // collision with any other dietplans in same treatment?
        List<DietPlanBO> plans = timespan.getDietPlan().getDietTreatment()
                .getDietPlans();
        for (DietPlanBO other : plans)
        {
            if (other.equals(timespan.getDietPlan())) continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(),
                    other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }

        // collision with any other treaments
        List<DietTreatmentBO> treatments = timespan.getDietPlan()
                .getDietTreatment().getPatient().getTreatments();
        for (DietTreatmentBO other : treatments)
        {
            if (other.equals(timespan.getDietPlan().getDietTreatment()))
                continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(),
                    other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }

        return collisions;
    }

    /**
     * Checks whether two timespans collide
     * @param currentStart
     * @param currentEnd
     * @param otherStart
     * @param otherEnd
     * @return True if there is a collision
     */
    private boolean isCollision(Date currentStart, Date currentEnd,
            Date otherStart, Date otherEnd)
    {
        return currentStart.compareTo(otherEnd) <= 0
                && otherStart.compareTo(currentEnd) <= 0;
    }
}
