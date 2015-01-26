package net._100steps.bbter.service.message.impl;

import net._100steps.bbter.service.message.Message;

public class GeneralMessage implements Message
{
	private final int msgCode;
	private final Object msg;
	
	public GeneralMessage(int msgCode, Object msg)
	{
		this.msgCode = msgCode;
		this.msg = msg;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

	public Object getMsg()
	{
		return msg;
	}
}
