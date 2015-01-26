package net._100steps.bbter.service.dao.group;

import java.util.List;

import net._100steps.bbter.service.model.Group;

public interface GroupDAO
{
	public void save(Group group);
	public void update(Group group);
	public void delete(int id);
	public Group getById(int id);
	public List<Group> getAll();
}