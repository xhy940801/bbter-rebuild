package net._100steps.bbter.service.manager;

import java.util.List;
import java.util.Map;

import net._100steps.general.message.Message;

public interface UserManager {
	/**
	 * 增加用户
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	public Message register(String studentNumber,
			String password,String email,int groupId,int departmentId);
	
	/**
	 * 删除用户（回收站）
	 * @param userId
	 * @return
	 */
	public Message delete(int userId);
	
	/**
	 * 完善信息
	 * @param userId
	 * @param infos
	 * @return
	 */
	public Message complete(int userId,Map<String, Object> details);
	
	/**
	 * 修改人员信息
	 * @param userId
	 * @param infos
	 * @return
	 */
	public Message update(int userId,Map<String, Object> details);
	
	/**
	 * 彻底删除人员信息
	 * @param userId
	 * @return
	 */
	public Message deleteForever(int userId);
	
	/**
	 * 加入梯友库
	 * @param userId
	 * @return
	 */
	public Message addToTiYou(int userId);
	
	/**
	 * 改变职位
	 * @param userId
	 * @param position
	 * @return
	 */
	public Message changeGroup(int userId,int group);
	
	/**
	 * 改变部门
	 * @param userId
	 * @param department
	 * @return
	 */
	public Message changeDepartment(int userId,int department);
	
	/**
	 * 生成找回密码时的key
	 * @param studentNumber
	 * @return
	 */
	public Message getFindPasswordKey(String studentNumber);
	
	/**
	 * 找回密码
	 * @param userId
	 * @return
	 */
	public Message findPassword(String key,String password);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public Message resetPassword(int userId,String oldPassword,String newPassword);
	
	/**
	 * 登陆
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	public Message login(String studentNumber,String password);

	/**
	 * 获得某一个部门的全部人员
	 * @param departmentId
	 * @return
	 */
	public Message getUsersByDepartmentId(int departmentId);
	
	/**
	 * 获得某一个组的全部用户
	 * @param groupId
	 * @return
	 */
	public Message getUsersByGroupId(int groupId);
	
	/**
	 * 获得某一些用户的全部详细信息
	 * @param userIds
	 * @return
	 */
	public Message getUserDetailsByUserId(List<Integer> userIds);
	
}
