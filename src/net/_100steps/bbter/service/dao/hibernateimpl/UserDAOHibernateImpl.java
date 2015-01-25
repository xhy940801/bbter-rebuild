package net._100steps.bbter.service.dao.hibernateimpl;




import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.model.User;

public class UserDAOHibernateImpl implements UserDAO{
	
	private SessionFactory sessionFactory;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		try{
		sessionFactory.getCurrentSession().update(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		try{
		return (User)sessionFactory.getCurrentSession().get(UserDAO.class, id);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public User getUserByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		try {
			this.sessionFactory = sessionFactory;
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
		
	}
	/*@Override
	public User getUserByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		Session session = (Session) sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where User.departmentId = ?");
		query.setInteger(0, departmentId);
		
		return null;
	}

	@Override
	public User getUserByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
