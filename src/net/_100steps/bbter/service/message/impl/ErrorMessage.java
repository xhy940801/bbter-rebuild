package net._100steps.bbter.service.message.impl;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.util.commontree.CommonTree;
import net._100steps.bbter.service.util.commontree.GeneralCommonTree;
import net._100steps.bbter.service.util.commontree.GeneralNode;

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
		exception.printStackTrace();
		msgCode = errorCode;
		this.exception = exception;
	}
	
	public Exception getException()
	{
		return exception;
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
		tree.add(new GeneralNode("code", msgCode));
		return tree;
	}

}
