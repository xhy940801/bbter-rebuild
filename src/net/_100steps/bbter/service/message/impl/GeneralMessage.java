package net._100steps.bbter.service.message.impl;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.util.commontree.CommonTree;
import net._100steps.bbter.service.util.commontree.GeneralCommonTree;
import net._100steps.bbter.service.util.commontree.GeneralNode;

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

	@Override
	public CommonTree getDataTree()
	{
		CommonTree tree = new GeneralCommonTree();
		tree.add(new GeneralNode("code", msgCode));
		tree.add(new GeneralNode("data", msg));
		return tree;
	}
}
