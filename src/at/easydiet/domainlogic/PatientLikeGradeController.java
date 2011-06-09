package at.easydiet.domainlogic;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.businessobjects.PatientLikeGradeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientLikeGradeDAO;
import at.easydiet.model.PatientLikeGrade;

/**
 * Provides data and methods for
 */
public class PatientLikeGradeController extends DomainLogicController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientLikeGradeController.class);

    /**
     * Get a Instance of this {@link PatientLikeGradeController}
     * 
     * @return The instance of this {@link PatientLikeGradeController}
     */
    static PatientLikeGradeController newInstance(DomainLogicProvider provider)
    {
            return new PatientLikeGradeController(provider);
    }

    /**
     * Initializes a new instance of the {@link PatientLikeGradeController}
     * class.
     */
    private PatientLikeGradeController(DomainLogicProvider provider)
    {
        super(provider);
    }

    /**
     * Stores the grades
     */
    private List<PatientLikeGradeBO> _grades;

    /**
     * Gets all grades
     * 
     * @return List of all {@link PatientLikeGradeBO}
     */
    public List<PatientLikeGradeBO> getGrades()
    {
        if (_grades == null)
        {
            refreshGrades();
        }
        return _grades;
    }

    /**
     * Reload grades
     */
    private void refreshGrades()
    {
        _grades = new ArrayList<PatientLikeGradeBO>();

        PatientLikeGradeDAO dao = DAOFactory.getInstance()
                .getPatientLikeGradeDAO();
        for (PatientLikeGrade grade : dao.findAll())
        {
            _grades.add(new PatientLikeGradeBO(grade));
        }
    }
}
