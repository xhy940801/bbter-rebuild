package net._100steps.bbter.service.dao.department.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.department.DepartmentDAO;
import net._100steps.bbter.service.model.Department;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;

public class DepartmentDAOHibernateImpl implements DepartmentDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Integer, Department> cache;

	@Override
	@Transactional
	public void save(Department department)
	{
		try
		{
			sessionFactory.getCurrentSession().save(department);
			cache.cache(department.getId(), department);
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
			cache.cache(department.getId(), department);
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
			return cache.get(id, (key)->{return (Department) sessionFactory.getCurrentSession().get(Department.class, key);});
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
			return (List<Department>) sessionFactory.getCurrentSession().createQuery("from Department").list();
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
		cache.remove(id);
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public void setCache(QuickCache<Integer, Department> cache)
	{
		this.cache = cache;
	}

}
