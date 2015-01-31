package net._100steps.bbter.service.util.commontree;

import java.util.ArrayList;
import java.util.List;

public class GeneralCommonTree extends ArrayList<CommonTreeNode> implements CommonTree
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8311154999546437455L;
	
	public GeneralCommonTree()
	{
		
	}

	@Override
	public List<CommonTreeNode> getChildren()
	{
		return this;
	}
}
