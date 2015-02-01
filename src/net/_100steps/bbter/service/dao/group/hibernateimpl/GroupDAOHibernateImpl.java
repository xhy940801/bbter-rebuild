package net._100steps.bbter.service.dao.group.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.group.GroupDAO;
import net._100steps.bbter.service.model.Group;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;

public class GroupDAOHibernateImpl implements GroupDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Integer, Group> cache;

	@Override
	@Transactional
	public void save(Group group)
	{
		try
		{
			sessionFactory.getCurrentSession().save(group);
			cache.cache(group.getId(), group);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Group group)
	{
		try
		{
			sessionFactory.getCurrentSession().update(group);
			cache.cache(group.getId(), group);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Group getById(int id)
	{
		try
		{
			return cache.get(id, (key)->{return (Group) sessionFactory.getCurrentSession().get(Group.class, key);});
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Group> getAll()
	{
		try
		{
			return (List<Group>) sessionFactory.getCurrentSession().createQuery("from Group").list();
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
		if(sessionFactory.getCurrentSession().createQuery("delete from Group as d where d.id=?").setInteger(0, id).executeUpdate() == 0)
			throw new DAOException("记录不存在");
		cache.remove(id);
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public void setCache(QuickCache<Integer, Group> cache)
	{
		this.cache = cache;
	}

}
