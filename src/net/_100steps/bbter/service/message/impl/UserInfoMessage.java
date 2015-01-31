package net._100steps.bbter.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.model.UserInfo;
import net._100steps.bbter.service.util.commontree.CommonTree;

public class UserInfoMessage implements Message
{
	private final int msgCode;
	private final List<UserInfo> userInfos;
	
	public UserInfoMessage(int msgCode,UserInfo userInfo)
	{
		this.msgCode = msgCode;
		userInfos = new ArrayList<UserInfo>();
		userInfos.add(userInfo);
	}
	
	public UserInfoMessage(List<UserInfo> userInfos)
	{
		this.msgCode = 0;
		this.userInfos = userInfos;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

	@Override
	public CommonTree getDataTree()
	{
		return null;
	}

}
