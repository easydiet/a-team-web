package at.easydiet.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import at.easydiet.model.SystemUser;

/**
 * A DAO implementation for SystemUser objects.
 */
public class SystemUserDAO extends GenericHibernateDAO<SystemUser, Long>
{

    public SystemUser findByUsername(String name)
    {
        SystemUser template = new SystemUser();
        template.setUsername(name);

        Example ex = Example.create(template).excludeZeroes().ignoreCase();

        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(ex);
        return (SystemUser) crit.uniqueResult();
    }
}