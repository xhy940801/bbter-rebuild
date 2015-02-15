package net._100steps.bbter.service.dao.user.hibernateimpl;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.model.UserDetail;

public class UserDetailDAOHibernateImpl implements UserDetailDAO {
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public void save(UserDetail userDetail) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(userDetail);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(userDetail);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
		
	}
	@Transactional
	public void delete(UserDetail userDetail)
	{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(userDetail);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException();
		}
	}
	@Override
	@Transactional
	public UserDetail getUserDetailById(int id){
		try {
			return (UserDetail)sessionFactory .getCurrentSession().get(UserDetail.class, id);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	@Override
	@Transactional
	public UserDetail getUserDetailByUserId(int userId)
	{
		try {
			return (UserDetail)sessionFactory.getCurrentSession()
					.createQuery("from UserDetail as u where u.userId = ?")
					.setInteger(0, userId)
					.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
