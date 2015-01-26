package net._100steps.bbter.service.manager.defaultimpl;

import java.util.List;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.group.GroupDAO;
import net._100steps.bbter.service.manager.GroupManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.message.impl.GeneralMessage;
import net._100steps.bbter.service.message.impl.GroupMessage;
import net._100steps.bbter.service.model.Group;

public class GroupManagerDefaultImpl implements GroupManager
{
	private GroupDAO groupDAO;

	@Override
	public Message addGroup(String name)
	{
		Group group = new Group();
		group.setName(name);
		if(name.length() > 50)
			return new ErrorMessage(202010);
		try
		{
			groupDAO.save(group);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402010, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502010, e);
		}
		return new GroupMessage(group);
	}

	@Override
	public Message changeGroup(int id, String name)
	{
		if(name.length() > 50)
			return new ErrorMessage(202020);
		Group group;
		try
		{
			group = groupDAO.getById(id);
			if(group == null)
				return new ErrorMessage(302020);
			group.setName(name);
			groupDAO.update(group);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402020, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502020, e);
		}
		return new GroupMessage(group);
	}

	@Override
	public Message deleteGroup(int id)
	{
		try
		{
			if(id < 0)
				return new ErrorMessage(202030);
			groupDAO.delete(id);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402030, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502030, e);
		}
		return new GeneralMessage(0, null);
	}

	@Override
	public Message getGroup(int id)
	{
		Group group;
		try
		{
			if(id < 0)
				return new ErrorMessage(202040);
			group = groupDAO.getById(id);
			if(group == null)
				return new ErrorMessage(302040);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402040, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502040, e);
		}
		return new GroupMessage(group);
	}

	@Override
	public Message getAllGroups()
	{
		List<Group> groups;
		try
		{
			groups = groupDAO.getAll();
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402050, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502050, e);
		}
		return new GroupMessage(groups);
	}

	public void setGroupDAO(GroupDAO groupDAO)
	{
		this.groupDAO = groupDAO;
	}

}
