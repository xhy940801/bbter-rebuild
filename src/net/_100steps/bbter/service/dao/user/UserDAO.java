package net._100steps.bbter.service.dao.user;

import net._100steps.bbter.service.model.User;

public interface UserDAO {
	/**
	 * 新增用户信息
	 * @param User
	 * @return void 
	 */
	public void save(User user);
	
	/**
	 * 更新用户信息
	 * @param User 
	 * @return void
	 */
	
	public void update(User user);
	/**
	 * 根据学号获得用户信息
	 * @param studentNumber
	 */
	public User getUserByStudentNumber(String studentNumber);
	
	/**
	 * 根据id获得User信息
	 * @param  int id
	 * @return User
	 */
	public User getUserById(int id);
	
	/**
	 * 根据所属的department id 获得用户信息
	 * @param int departmentId
	 * @return User 
	 */
	public User getUserByDepartmentId(int departmentId);
	
	/**
	 * 根据所属Group id获得User信息
	 * @param int groupId
	 * @return User
	 */
	public User getUserByGroupId(int groupId);
	/**
	 * 删除
	 * @param userId
	 */
	public void delete(User user);
}
