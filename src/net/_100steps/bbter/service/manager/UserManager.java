package net._100steps.bbter.service.manager;

import net._100steps.bbter.service.message.Message;

public interface UserManager {
	/**
	 * 增加用户
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	public Message register(String studentNumber,String password);
	
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
	public Message complete(int userId,String[] infos);
	
	/**
	 * 修改人员信息
	 * @param userId
	 * @param infos
	 * @return
	 */
	public Message update(int userId,String[] infos);
	
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
	 * 找回密码
	 * @param userId
	 * @return
	 */
	public Message findPassword(int userId);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public Message resetPassword(int userId,String password);
}
