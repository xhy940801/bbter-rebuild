package net._100steps.general.message.impl;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.general.util.commontree.GeneralCommonTree;
import net._100steps.general.util.commontree.GeneralNode;

public class GeneralMessage extends AbstractMessage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Object msg;
	
	public GeneralMessage(int msgCode, Object msg)
	{
		super(msgCode);
		this.msg = msg;
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
