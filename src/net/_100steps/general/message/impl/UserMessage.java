package net._100steps.general.message.impl;


import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.model.User;
import net._100steps.general.message.Message;
import net._100steps.general.util.commontree.CommonTree;

public class UserMessage implements Message {
	private int msgCode;
	private final List<User> users;
	
	public UserMessage(int msgCode,User user)
	{
		this.msgCode = msgCode;
		users = new ArrayList<User>();
		users.add(user);
	}
	public UserMessage(List<User> users)
	{
		this.msgCode = 0;
		this.users = users;
	}
	@Override
	public int getMsgCode() {
		// TODO Auto-generated method stub
		return msgCode;
	}
	@Override
	public CommonTree getDataTree()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
