package net._100steps.bbter.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.model.Group;

public class GroupMessage implements Message
{
	private final int msgCode;
	private final List<Group> groups;

	public GroupMessage(Group group)
	{
		this.msgCode = 0;
		groups = new ArrayList<Group>();
		groups.add(group);
	}
	
	public GroupMessage(List<Group> groups)
	{
		this.msgCode = 0;
		this.groups = groups;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

}
