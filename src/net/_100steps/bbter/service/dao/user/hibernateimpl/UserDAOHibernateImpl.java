package net._100steps.bbter.service.dao.user.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.model.User;

public class UserDAOHibernateImpl implements UserDAO{
	
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}
	@Override
	@Transactional
	public User getUserByStudentNumber(String studentNumber) {
		// TODO Auto-generated method stub
		try {
			return (User)sessionFactory.getCurrentSession().createQuery("from User as u where u.studentNumber = ?").setString(0, studentNumber).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	@Override
	@Transactional
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		try{
			return (User)sessionFactory.getCurrentSession().get(User.class, id);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getUsersByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		try {
			return (List<User>)sessionFactory.getCurrentSession().createQuery("from User as u where u.departmentId = ?").setInteger(0, departmentId).list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getUsersByGroupId(int groupId) {
		// TODO Auto-generated method stub
		try {
			return (List<User>)sessionFactory.getCurrentSession().createQuery("from User as u where u.groupId = ?").setInteger(0, groupId).list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getUsersByStatus(User.Status status) {
		try {
			return (List<User>) sessionFactory.getCurrentSession().createQuery("from User as u where u.status=?").setString(0, status.toString()).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void delete(User user)
	{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	

}
