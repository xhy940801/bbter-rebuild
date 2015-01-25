package net._100steps.service.dao.groups.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.service.dao.DAOException;
import net._100steps.service.dao.groups.GroupDAO;
import net._100steps.service.dao.model.Group;

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
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
