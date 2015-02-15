package net._100steps.bbter.service.manager.defaultimpl;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.model.User;
import net._100steps.bbter.service.model.User.Status;
import net._100steps.bbter.service.model.UserDetail;
import net._100steps.general.message.Message;
import net._100steps.labelsys.service.api.rmi.EntityManagerRO;

public class TUtilImpl implements TransactionUtil
{
	private final Log logger = LogFactory.getLog(getClass());

	private UserDAO userDAO;
	private UserDetailDAO userDetailDAO;
	private NameConf conf;
	private EntityManagerRO entityRO;

	@Override
	@Transactional
	public User addUser(String studentNumber, String password, String email,
			int groupId, int departmentId, Status status)
	{
		User user = new User();
		user.setStudentNumber(studentNumber);
		user.setPassword(password);
		user.setEmail(email);
		user.setGroupId(groupId);
		user.setDeparmentId(departmentId);
		if (status == null)
			status = User.Status.DEFAULT;
		user.setStatus(status);
		userDAO.save(user);
		UserDetail userDetail = addDefaultUserDetail(user.getId());
		userDetailDAO.save(userDetail);
		try
		{
			Message msg = entityRO.createEntity(conf.SYSNAME, conf.USMNAME,
					user.getId());
			if (msg.getMsgCode() != 0)
			{
				logger.fatal("rmi error: " + msg.getMsgCode());
				throw new GeneralException("rmi error!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new GeneralException("rmi error!", e);
		}
		return user;
	}

	@Override
	@Transactional
	public User deleteUser(User user, UserDetail userDetail)
	{
		userDAO.delete(user);
		userDetailDAO.delete(userDetail);
		return user;
	}

	public void setEntityRO(EntityManagerRO entityRO)
	{
		this.entityRO = entityRO;
	}

	public void setConf(NameConf conf)
	{
		this.conf = conf;
	}

	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public void setUserDetailDAO(UserDetailDAO userDetailDAO)
	{
		this.userDetailDAO = userDetailDAO;
	}

	private UserDetail addDefaultUserDetail(int userId)
	{
		UserDetail userDetail = new UserDetail();
		userDetail.setUserId(userId);
		userDetail.setCreated(new Date());
		userDetail.setModified(new Date());
		return userDetail;
	}

}
