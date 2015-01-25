package net._100steps.bbter.service.message.impl;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.model.User;

public class UserMessage implements Message {
	private int msgCode;
	private User user;
	
	public UserMessage(int msgCode,User user)
	{
		this.msgCode = msgCode;
		this.user = user;
	}
	@Override
	public int getMsgCode() {
		// TODO Auto-generated method stub
		return msgCode;
	}

}
