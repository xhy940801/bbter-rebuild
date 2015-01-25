package net._100steps.service.message.impl;

import net._100steps.service.message.Message;

public class ErrorMessage implements Message
{
	private final int msgCode;
	private final Exception exception;

	public ErrorMessage(int errorCode)
	{
		msgCode = errorCode;
		this.exception = null;
	}
	
	public ErrorMessage(int errorCode, Exception exception)
	{
		msgCode = errorCode;
		this.exception = exception;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

}
