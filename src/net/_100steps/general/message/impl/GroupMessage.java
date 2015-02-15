package net._100steps.general.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.model.Group;
import net._100steps.general.message.Message;
import net._100steps.general.util.commontree.CommonTree;
import net._100steps.general.util.commontree.GeneralCommonTree;
import net._100steps.general.util.commontree.GeneralNode;

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

	@Override
	public CommonTree getDataTree()
	{
		CommonTree tree = new GeneralCommonTree();
		List<CommonTree> groupDatas = new ArrayList<CommonTree>();
		for(Group group : groups)
		{
			CommonTree groupData = new GeneralCommonTree();
			groupData.add(new GeneralNode("id", group.getId()));
			groupData.add(new GeneralNode("name", group.getName()));
			groupDatas.add(groupData);
		}
		tree.add(new GeneralNode("code", msgCode));
		tree.add(new GeneralNode("data", groupDatas));
		return tree;
	}

}
