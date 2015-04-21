package net._100steps.bbter.service.dao.file.impl;

import net._100steps.bbter.service.dao.file.FileDAO;
import net._100steps.bbter.service.model.File;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class FileDAOHibernateImpl implements FileDAO
{
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(File file)
	{
		sessionFactory.getCurrentSession().save(file);
	}

	@Override
	@Transactional
	public File getById(int id)
	{
		return (File) sessionFactory.getCurrentSession().get(File.class, id);
	}

	@Override
	@Transactional
	public void delete(int id)
	{
		sessionFactory.getCurrentSession()
				.createQuery("delete File as f where id=?").setInteger(0, id);
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void update(File file)
	{
		sessionFactory.getCurrentSession().update(file);
	}

}
