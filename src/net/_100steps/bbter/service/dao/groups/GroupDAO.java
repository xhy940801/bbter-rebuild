package net._100steps.bbter.service.dao.groups;

import java.util.List;

import net._100steps.bbter.service.dao.model.Group;

public interface GroupDAO
{
	public void save(Group group);
	public void update(Group group);
	public void delete(int id);
	public Group getById(int id);
	public List<Group> getAll();
}