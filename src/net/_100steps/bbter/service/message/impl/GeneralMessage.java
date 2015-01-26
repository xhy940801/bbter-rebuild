package net._100steps.bbter.service.message.impl;

import net._100steps.bbter.service.message.Message;

public class GeneralMessage implements Message
{
	private int msgCode;
	private Object msg;
	
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

	public void setMsgCode(int msgCode)
	{
		this.msgCode = msgCode;
	}

	public Object getMsg()
	{
		return msg;
	}

	public void setMsg(Object msg)
	{
		this.msg = msg;
	}
}
