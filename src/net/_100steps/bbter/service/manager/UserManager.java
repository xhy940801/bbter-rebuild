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
	
	
	public Message complete(int userId,String[] infos);
}
