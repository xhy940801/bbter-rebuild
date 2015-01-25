package net._100steps.bbter.service.dao.hibernateimpl;




import org.hibernate.SessionFactory;

import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.model.User;

public class UserDAOHibernateImpl implements UserDAO{
	
	private SessionFactory sessionFactory;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return (User)sessionFactory.getCurrentSession().get(UserDAO.class, id);
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
		this.sessionFactory = sessionFactory;
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
