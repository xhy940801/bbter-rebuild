package net._100steps.bbter.service.model;

public class UserInfo
{
	private final User user;
	private final UserDetail userDetail;
	private final Group group;
	private final Department department;
	
	public UserInfo(User user, UserDetail userDetail, Group group, Department department)
	{
		this.user = user;
		this.userDetail = userDetail;
		this.group = group;
		this.department = department;
	}

	public User getUser()
	{
		return user;
	}

	public UserDetail getUserDetail()
	{
		return userDetail;
	}

	public Group getGroup()
	{
		return group;
	}

	public Department getDepartment()
	{
		return department;
	}
	
}
