package net._100steps.bbter.service.manager.impl;

import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.manager.UserManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.model.UserDetail;
import net._100steps.bbter.service.util.StringVerify;

public class UserManagerImpl implements UserManager{
	private UserDAO userDAO;
	private UserDetailDAO userDetailDAO;
	public Message setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
		return null;
	}
	public Message setUserDetailDAO(UserDetailDAO userDetailDAO)
	{
		this.userDetailDAO = userDetailDAO;
		return null;
	}
	@Override
	public Message register(String studentNumber, String password) {
		// TODO Auto-generated method stub
		/*if(!StringVerify.checkLength(studentNumber, 12, 12))
			return new ErrorMessage();*/
		return null;
	}

	@Override
	public Message delete(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message complete(int userId, String[] infos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message update(int userId, String[] infos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message deleteForever(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message addToTiYou(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message changeGroup(int userId, int group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message changeDepartment(int userId, int department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message findPassword(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message resetPassword(int userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
