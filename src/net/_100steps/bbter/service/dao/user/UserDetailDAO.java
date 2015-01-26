package net._100steps.bbter.service.dao.user;

import net._100steps.bbter.service.model.UserDetail;

public interface UserDetailDAO {
	public void save(UserDetail userDetail);
	
	public void update(UserDetail userDetail);
	
	public void delete(UserDetail userDetail);
	
	public UserDetail getUserDetailById(int id);
	
	public UserDetail getUserDetailByUserId(int userId);
	
}
