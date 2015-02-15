package net._100steps.general.message.impl;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.general.util.commontree.GeneralCommonTree;
import net._100steps.general.util.commontree.GeneralNode;

public class ErrorMessage extends AbstractMessage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Exception exception;

	public ErrorMessage(int errorCode)
	{
		super(errorCode);
		this.exception = null;
	}
	
	public ErrorMessage(int errorCode, Exception exception)
	{
		super(errorCode);
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
	
	@Override
	public String toString()
	{
		return super.toString() + exception;
	}

}
