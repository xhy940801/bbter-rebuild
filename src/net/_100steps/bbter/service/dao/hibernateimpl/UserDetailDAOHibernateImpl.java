package net._100steps.bbter.service.dao.hibernateimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.model.UserDetail;

public class UserDetailDAOHibernateImpl implements UserDetailDAO {
	private SessionFactory sessionFactory;
	@Override
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
	public void update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(userDetail);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
		
	}

}
