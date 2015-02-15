package net._100steps.bbter.service.manager.defaultimpl;

import net._100steps.bbter.service.model.User;
import net._100steps.bbter.service.model.UserDetail;

public interface TransactionUtil
{
	public User addUser(String studentNumber, String password, String email,int groupId,int departmentId, User.Status status);
	public User deleteUser(User user,UserDetail userDetail);
}
