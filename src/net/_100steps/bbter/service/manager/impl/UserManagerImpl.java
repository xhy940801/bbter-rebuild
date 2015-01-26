package net._100steps.bbter.service.manager.impl;

import java.util.Date;
import java.util.Map;

import org.hibernate.HibernateException;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.GeneralException;
import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.manager.UserManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.message.impl.GeneralMessage;
import net._100steps.bbter.service.message.impl.UserMessage;
import net._100steps.bbter.service.model.User;
import net._100steps.bbter.service.model.UserDetail;
import net._100steps.bbter.service.util.AESUtil;
import net._100steps.bbter.service.util.MD5Util;
import net._100steps.bbter.service.util.StringVerify;

public class UserManagerImpl implements UserManager{
	private UserDAO userDAO;
	private UserDetailDAO userDetailDAO;
	private String verificationString;
	private Long timelimit;
	private final int detailFieldsNumber; 
	
	public UserManagerImpl()
	{
		verificationString = "this is bbter";
		timelimit = (long) (1000 * 60 * 10);
		detailFieldsNumber = 20;
	}
	@Override
	public Message register(String studentNumber, String password,
			String email,int groupId,int departmentId,int status) {
		// TODO Auto-generated method stub
		if(!StringVerify.checkLength(studentNumber, 12, 12))
			return new ErrorMessage(201010,null);
		if(!StringVerify.isStrongPassword(password, 6, 18))
			return new ErrorMessage(201011,null);
		try {
			if(userDAO.getUserByStudentNumber(studentNumber)!=null)
				return new ErrorMessage(601012,null);
		} catch (HibernateException e) {
			// TODO: handle exception
			return new  ErrorMessage(501010,null);
		}
		
		if(!StringVerify.isEmail(email, 30))
			return new ErrorMessage(201010,null);
		/**
		 * 还剩department和group以及status的验证
		 */
		password = MD5Util.md5(password);
		if(password==null)
			return new ErrorMessage(501010,null);
		User user;
		try {
			user = addUser(studentNumber, password, email, groupId, departmentId, status);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501010,null);
		}
		return new UserMessage(0,user);
	}
	public User addUser(String studentNumber, String password,
			String email,int groupId,int departmentId,int status){
		User user = new User();
		user.setStudentNumber(studentNumber);
		user.setPassword(password);
		user.setEmail(email);
		user.setGroupId(groupId);
		user.setDeparmentId(departmentId);
		user.setStatus(status);
		userDAO.save(user);
		UserDetail userDetail = new UserDetail();
		Date date = new Date(1970-1900,01,01);
		userDetail.setBirth(date);
		userDetail.setUserId(user.getId());
		userDetailDAO.save(userDetail);
		return user;
	}
	/**
	 * -1带便彻底删除 ,0 代表回收站，1表示存在
	 */
	@Override
	public Message delete(int userId) {
		// TODO Auto-generated method stub
		User user;
		try{
			user = userDAO.getUserById(userId);
			if(user==null)
				return new ErrorMessage(601030,null);
			user.setStatus(0);
			userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(501020,null);
		}
		return new UserMessage(0, user);
	}
	public UserDetail verifyUserDetail(Map<String, Object> details) throws Exception
	{
		UserDetail userDetail = new UserDetail();
		if(details.keySet().size()<detailFieldsNumber)
			throw new Exception("101030");
		String idString = (String) details.get("id");
		String name = (String) details.get("name");
		String sex = (String) details.get("sex");
		String portrait = (String) details.get("portrait");
		String dormitory = (String) details.get("dormitory");
		String room = (String) details.get("room");
		String collegeId = (String) details.get("collegeId");
		String major = (String) details.get("major");
		String classField = (String) details.get("classField");
		Date birth = (Date) details.get("birth");
		String politics = (String)details.get("politics");
		String origo = (String) details.get("origo");
		String mobile = (String) details.get("mobile");
		String shortMobile = (String) details.get("shortMobile");
		String qq = (String) details.get("qq");
		String weibo = (String) details.get("weibo");
		String remark = (String) details.get("remark");
		Date created = (Date) details.get("created");
		Date modified = (Date) details.get("modified");
		String userIdString = (String) details.get("userId");
		boolean isNull = idString==null||name==null
				||sex==null||portrait==null||dormitory == null||room == null||collegeId == null||major==null||classField == null
				||birth == null||politics == null||origo == null||mobile==null||shortMobile==null||qq==null||weibo==null||remark==null
				||created==null||modified==null||userIdString==null;
		//存在一个为空，抛出数据不完整错误
		if(isNull)
			throw new GeneralException("101030");
		if(userDetailDAO.getUserDetailById(Integer.parseInt(idString))==null)
			throw new GeneralException("601031");
		if(userDetailDAO.getUserDetailByUserId(Integer.parseInt(userIdString))==null)
			throw new GeneralException("601032");
		userDetail.setId(Integer.parseInt(idString));
		userDetail.setName(name);
		userDetail.setSex(sex);
		userDetail.setPortrait(portrait);
		userDetail.setDormitory(dormitory);
		userDetail.setRoom(Integer.parseInt(room));
		userDetail.setCollegeId(Integer.parseInt(collegeId));
		userDetail.setMajor(major);
		userDetail.setClassField(classField);
		userDetail.setBirth(birth);
		userDetail.setPolitics(politics);
		userDetail.setOrigo(origo);
		userDetail.setMobile(mobile);
		userDetail.setShortMobile(shortMobile);
		userDetail.setQq(qq);
		userDetail.setWeibo(weibo);
		userDetail.setRemark(remark);
		userDetail.setCreated(created);
		userDetail.setModified(modified);
		userDetail.setUserId(Integer.parseInt(userIdString));
		return userDetail;
		
		
	}
	@Override
	public Message complete(int userId, Map<String, Object> details) {
		// TODO Auto-generated method stub
		UserDetail userDetail;
		User user;
		try {
			user = userDAO.getUserById(userId);
			if(user == null)
				return new ErrorMessage(601030,null);
			userDetail = verifyUserDetail(details);
			userDetailDAO.update(userDetail);
		} catch (GeneralException e) {
			// TODO: handle exception
			return new ErrorMessage(Integer.parseInt(e.getMessage()),null);
		}catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501030,null);
		}catch (Exception  e){
			return new ErrorMessage(501030,null);
		}
		return new UserMessage(0, user);
	}
	
	@Override
	public Message update(int userId, Map<String, Object> details) {
		// TODO Auto-generated method stub
		UserDetail userDetail;
		User user;
		try {
			user = userDAO.getUserById(userId);
			if(user == null)
				return new ErrorMessage(601040,null);
			userDetail = verifyUserDetail(details);
			userDetailDAO.update(userDetail);
		} catch (GeneralException e) {
			// TODO: handle exception
			return new ErrorMessage(Integer.parseInt(e.getMessage()),null);
		}catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501040,null);
		}catch (Exception  e){
			return new ErrorMessage(501040,null);
		}
		return new UserMessage(0, user);
	}

	@Override
	public Message deleteForever(int userId) {
		// TODO Auto-generated method stub
		User user;
		UserDetail userDetail = new UserDetail();
		try{
		 user = userDAO.getUserById(userId);
		 userDetail = userDetailDAO.getUserDetailByUserId(user.getId());
		if(user==null||userDetail==null)
			return new ErrorMessage(601050,null);
		userDAO.delete(user);
		userDetailDAO.delete(userDetail);
		}catch(DAOException e){
			return new ErrorMessage(501050,null);
		}
		return new UserMessage(0, user);
	}

	@Override
	public Message addToTiYou(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message changeGroup(int userId, int group) {
		// TODO Auto-generated method stub
		User user;
		try{
		 user = userDAO.getUserById(userId);
		if(user==null)
			return new ErrorMessage(601070,null);
		/**
		 * 判断groupid 的有效性
		 */
		user.setGroupId(group);
		userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(501070,null);
		}
		return new UserMessage(0, user);
	}

	@Override
	public Message changeDepartment(int userId, int department) {
		// TODO Auto-generated method stub
		User user;
		try{
		 user = userDAO.getUserById(userId);
		if(user==null)
			return new ErrorMessage(601080,null);
		user.setDeparmentId(department);
		userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(401080,null);
		}
		return new UserMessage(0, user);
	}
	@Override
	public Message getFindPasswordKey(String studentNumber)
	{
		String key;
		try
		{
			//对studentNumber,时间和一段特定的信息进行加密
			key = AESUtil.encrypt(studentNumber + ";" + new Date().getTime() + ";"
					+ verificationString);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501090, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701090, null);
		}
		if (key == null)
			return new ErrorMessage(501090, null);
		return new GeneralMessage(0, key);
	}
	
	@Override
	public Message findPassword(String key,String password) {
		// TODO Auto-generated method stub
		String context;
		User user;
		try{
			context = AESUtil.decrypt(key);
			if(!StringVerify.isStrongPassword(password, 6, 18))
				return new ErrorMessage(201100,null);
			if (context ==null)
				return new ErrorMessage(501100,null);
			String[] datas = context.split(";",3);
			if(datas.length<3||!datas[2].equals(verificationString))
				return new ErrorMessage(301100,null);
			if(new Date().getTime() - Long.parseLong(datas[1])>timelimit)
				return new ErrorMessage(301100,null);
			password = MD5Util.md5(password);
			if(password == null)
				return new ErrorMessage(501100,null);
			user = userDAO.getUserByStudentNumber(datas[0]);
			user.setPassword(password);
			userDAO.update(user);
		}
		catch(DAOException e){
			return new ErrorMessage(40110,null);
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ErrorMessage(501100,null);
		}
		
		return new UserMessage(0, user);
	}

	@Override
	public Message resetPassword(int userId, String oldPassword,String newPassword) {
		// TODO Auto-generated method stub
		User user;
		try {
			user = userDAO.getUserById(userId);
			if(!StringVerify.isStrongPassword(newPassword, 6, 18))
				return new ErrorMessage(201110,null);
			user.setPassword(newPassword);
			userDAO.update(user);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(401110,null);
		}
		return new UserMessage(0, user);
	}
	@Override
	public Message login(String studentNumber,String password)
	{
		User user;
		try {
			user = userDAO.getUserByStudentNumber(studentNumber);
			if(!user.getPassword().equals(MD5Util.md5(password)))
				return new ErrorMessage(301120,null);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501120,null);
		}
		return new UserMessage(0,user);
	}
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
	

}
