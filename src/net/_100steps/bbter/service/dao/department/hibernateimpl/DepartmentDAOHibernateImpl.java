package net._100steps.bbter.service.dao.department.hibernateimpl;

import java.lang.ref.SoftReference;
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
	private SoftReference<List<Department>> refDepartments;

	@Override
	@Transactional
	public void save(Department department)
	{
		refDepartments = null;
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
		cache.remove(department.getId());
		refDepartments = null;
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
		List<Department> departments = refDepartments == null ? null : refDepartments.get();
		if(departments != null)
			return departments;
		try
		{
			departments = (List<Department>) sessionFactory.getCurrentSession().createQuery("from Department").list();
			refDepartments = new SoftReference<List<Department>>(departments);
			return departments;
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
		cache.remove(id);
		refDepartments = null;
		try
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from Department as d where d.id=?").setInteger(0, id).executeUpdate() == 0)
				throw new DAOException("记录不存在");
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
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
