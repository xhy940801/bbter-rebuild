package net._100steps.service.dao.groups;

import java.util.List;

class GroupDAO
{
	public int save(Group group);
	public void update(Group group);
	public Group getById(int id);
	public List<Group> getAll();
}