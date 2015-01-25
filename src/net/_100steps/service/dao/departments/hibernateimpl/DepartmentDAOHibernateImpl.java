package net._100steps.service.dao.departments.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.service.dao.DAOException;
import net._100steps.service.dao.departments.DepartmentDAO;
import net._100steps.service.dao.model.Department;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class DepartmentDAOHibernateImpl implements DepartmentDAO
{
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Department department)
	{
		try
		{
			sessionFactory.getCurrentSession().save(department);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Department department)
	{
		try
		{
			sessionFactory.getCurrentSession().update(department);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Department getById(int id)
	{
		try
		{
			return (Department) sessionFactory.getCurrentSession().get(Department.class, id);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Department> getAll()
	{
		try
		{
			return (List<Department>) sessionFactory.getCurrentSession().createQuery("select from Department").list();
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	
	@Override
	@Transactional
	public void delete(int id)
	{
		if(sessionFactory.getCurrentSession().createQuery("delete from Department as d where d.id=?").setInteger(0, id).executeUpdate() == 0)
			throw new DAOException("记录不存在");
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
