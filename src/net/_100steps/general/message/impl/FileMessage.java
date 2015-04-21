package net._100steps.general.message.impl;

import net._100steps.bbter.service.model.File;
import net._100steps.general.message.Message;
import net._100steps.general.util.commontree.CommonTree;

public class FileMessage implements Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int msgCode;
	private File file;
	
	public FileMessage(int msgCode, File file)
	{
		this.msgCode = msgCode;
		this.file = file;
	}
	
	public File getFile()
	{
		return file;
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
