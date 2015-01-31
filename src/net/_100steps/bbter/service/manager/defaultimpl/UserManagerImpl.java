package net._100steps.bbter.service.manager.defaultimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.GeneralException;
import net._100steps.bbter.service.dao.department.DepartmentDAO;
import net._100steps.bbter.service.dao.group.GroupDAO;
import net._100steps.bbter.service.dao.user.UserDAO;
import net._100steps.bbter.service.dao.user.UserDetailDAO;
import net._100steps.bbter.service.manager.UserManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.message.impl.GeneralMessage;
import net._100steps.bbter.service.message.impl.UserInfoMessage;
import net._100steps.bbter.service.message.impl.UserMessage;
import net._100steps.bbter.service.model.Department;
import net._100steps.bbter.service.model.Group;
import net._100steps.bbter.service.model.User;
import net._100steps.bbter.service.model.UserDetail;
import net._100steps.bbter.service.model.UserInfo;
import net._100steps.bbter.service.util.AESUtil;
import net._100steps.bbter.service.util.MD5Util;
import net._100steps.bbter.service.util.StringVerify;

import org.springframework.transaction.annotation.Transactional;

public class UserManagerImpl implements UserManager{
	private UserDAO userDAO;
	private UserDetailDAO userDetailDAO;
	private DepartmentDAO departmentDAO;
	private GroupDAO groupDAO;
	private String verificationString;
	private Long timelimit;
	private final int detailFieldsNumber; 
	
	public UserManagerImpl()
	{
		verificationString = "this is bbter";
		timelimit = (long) (1000 * 60 * 10);
		detailFieldsNumber = 20;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO)
	{
		this.departmentDAO = departmentDAO;
	}
	public void setGroupDAO(GroupDAO groupDAO)
	{
		this.groupDAO = groupDAO;
	}
	@Override
	public Message register(String studentNumber, String password,
			String email,int groupId,int departmentId,User.Status status) {
		// TODO Auto-generated method stub
		try {
			if(userDAO.getUserByStudentNumber(studentNumber)!=null)
				return new ErrorMessage(601012);
		} catch (DAOException e) {
			// TODO: handle exception
			return new  ErrorMessage(501010,e);
		}
		if(!StringVerify.checkLength(studentNumber, 12, 12))
			return new ErrorMessage(201010);
		if(!StringVerify.isStrongPassword(password, 6, 18))
			return new ErrorMessage(201011);
		if(!StringVerify.isEmail(email, 30))
			return new ErrorMessage(201010);
		password = MD5Util.md5(password);
		User user;
		try {
			user = addUser(studentNumber, password, email, groupId, departmentId, status);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501011,e);
		}
		return new UserMessage(0,user);
	}

	@Transactional
	public UserDetail addDefaultUserDetail(int userId){
		UserDetail userDetail = new UserDetail();
		userDetail.setUserId(userId);
		userDetail.setCreated(new Date());
		return userDetail;
	}
	@Transactional
	public User addUser(String studentNumber, String password,
			String email,int groupId,int departmentId,User.Status status){
		User user = new User();
		user.setStudentNumber(studentNumber);
		user.setPassword(password);
		user.setEmail(email);
		user.setGroupId(groupId);
		user.setDeparmentId(departmentId);
		if(status == null)
			status = User.Status.DEFAULT;
		user.setStatus(status);
		userDAO.save(user);
		UserDetail userDetail = addDefaultUserDetail(user.getId());
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
				return new ErrorMessage(601030);
			if(user.getStatus()!=User.Status.DEFAULT)
				return new ErrorMessage(501030);
			user.setStatus(User.Status.DELETED);
			userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(501020,e);
		}catch(RuntimeException e)
		{
			return new ErrorMessage(501030, e);
		}
		return new UserMessage(0, user);
	}
	public UserDetail verifyUserDetail(Map<String, Object> details) throws Exception
	{
		UserDetail userDetail = new UserDetail();
		if(details.keySet().size()<detailFieldsNumber)
			throw new Exception("101030");
		System.out.println(details.get("id"));
		String idString = details.get("id").toString();
		String name = (String) details.get("name");
		UserDetail.Sex sex = (UserDetail.Sex) details.get("sex");
		String portrait = (String) details.get("portrait");
		String dormitory = (String) details.get("dormitory");
		String room = details.get("room").toString();
		String collegeId = details.get("collegeId").toString();
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
		String userIdString = details.get("userId").toString();
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
				return new ErrorMessage(601030);
			userDetail = verifyUserDetail(details);
			userDetailDAO.update(userDetail);
		} catch (GeneralException e) {
			// TODO: handle exception
			return new ErrorMessage(Integer.parseInt(e.getMessage()),e);
		}catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501030,e);
		}catch (Exception  e){
			return new ErrorMessage(501030,e);
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
				return new ErrorMessage(601040);
			userDetail = verifyUserDetail(details);
			userDetailDAO.update(userDetail);
		} catch (GeneralException e) {
			// TODO: handle exception
			return new ErrorMessage(Integer.parseInt(e.getMessage()),null);
		}catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(501040,e);
		}catch (Exception  e){
			return new ErrorMessage(501040,e);
		}
		return new UserMessage(0, user);
	}
	@Transactional
	public User deleteUser(User user,UserDetail userDetail)
	{
		userDAO.delete(user);
		userDetailDAO.delete(userDetail);
		return user;
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
			return new ErrorMessage(601050);
		user = deleteUser(user, userDetail);
		}catch(DAOException e){
			return new ErrorMessage(501050);
		}
		return new UserMessage(0, user);
	}

	@Override
	public Message addToTiYou(int userId) {
		// TODO Auto-generated method stub
		User user;
		try{
			user = userDAO.getUserById(userId);
			if(user==null)
				return new ErrorMessage(601060);
			if(user.getStatus()!=User.Status.DEFAULT)
				return new ErrorMessage(501060);
			user.setStatus(User.Status.RETIRED);
			userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(501020,e);
		}
			return new UserMessage(0, user);
	}

	@Override
	public Message changeGroup(int userId, int group) {
		// TODO Auto-generated method stub
		User user;
		try{
		 user = userDAO.getUserById(userId);
		if(user==null)
			return new ErrorMessage(601070);
		user.setGroupId(group);
		userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(501070,e);
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
			return new ErrorMessage(601080);
		user.setDeparmentId(department);
		userDAO.update(user);
		}catch(DAOException e){
			return new ErrorMessage(401080,e);
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
			return new ErrorMessage(501090);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701090);
		}
		if (key == null)
			return new ErrorMessage(501090);
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
				return new ErrorMessage(201100);
			if (context ==null)
				return new ErrorMessage(501100);
			String[] datas = context.split(";",3);
			if(datas.length<3||!datas[2].equals(verificationString))
				return new ErrorMessage(301100);
			if(new Date().getTime() - Long.parseLong(datas[1])>timelimit)
				return new ErrorMessage(301100);
			password = MD5Util.md5(password);
			if(password == null)
				return new ErrorMessage(501100);
			user = userDAO.getUserByStudentNumber(datas[0]);
			user.setPassword(password);
			userDAO.update(user);
		}
		catch(DAOException e){
			return new ErrorMessage(40110);
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ErrorMessage(501100,e);
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
				return new ErrorMessage(201110);
			if(!user.getPassword().equals(MD5Util.md5(oldPassword)))
				return new ErrorMessage(301110);
			user.setPassword(MD5Util.md5(newPassword));
			userDAO.update(user);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(401110,e);
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
				return new ErrorMessage(301120);
		} catch (DAOException e) {
			return new ErrorMessage(501120,e);
		}
		return new UserMessage(0,user);
	}
	@Override
	public Message getUsersByDepartmentId(int departmentId)
	{
		List<User> users;
		try
		{
			users = userDAO.getUsersByDepartmentId(departmentId);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(401130, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(501130, e);
		}
		return new UserMessage(users);
	}
	public Message getUsersByGroupId(int groupId)
	{
		List<User> users;
		try
		{
			users = userDAO.getUsersByGroupId(groupId);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(401130, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(501130, e);
		}
		return new UserMessage(users);
	}
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	public void setUserDetailDAO(UserDetailDAO userDetailDAO)
	{
		this.userDetailDAO = userDetailDAO;
	}
	@Override
	public Message getUserDetailsByUserId(List<Integer> userIds)
	{
		List<UserInfo> userInfos = new ArrayList<UserInfo>(userIds.size());
		try
		{
			for(Integer id : userIds)
			{
				User user = userDAO.getUserById(id);
				if(user == null)
					return new ErrorMessage(201130);
				UserDetail userDetail = userDetailDAO.getUserDetailByUserId(id);
				Group group = groupDAO.getById(user.getGroupId());
				Department department = departmentDAO.getById(user.getGroupId());
				userInfos.add(new UserInfo(user, userDetail, group, department));
			}
		}
		catch(DAOException e)
		{
			return new ErrorMessage(401130, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(501130, e);
		}
		return new UserInfoMessage(userInfos);
	}
}
