package net._100steps.bbter.service.dao.groups.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.groups.GroupDAO;
import net._100steps.bbter.service.dao.model.Group;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class GroupDAOHibernateImpl implements GroupDAO
{

	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Group Group)
	{
		try
		{
			sessionFactory.getCurrentSession().save(Group);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Group Group)
	{
		try
		{
			sessionFactory.getCurrentSession().update(Group);
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
			return (Group) sessionFactory.getCurrentSession().get(Group.class, id);
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
			return (List<Group>) sessionFactory.getCurrentSession().createQuery("select from Group");
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
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
