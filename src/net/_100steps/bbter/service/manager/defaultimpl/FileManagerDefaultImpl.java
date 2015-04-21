package net._100steps.bbter.service.manager.defaultimpl;

import net._100steps.bbter.service.dao.file.FileDAO;
import net._100steps.bbter.service.manager.FileManager;
import net._100steps.bbter.service.model.File;
import net._100steps.general.message.Message;
import net._100steps.general.message.impl.ErrorMessage;
import net._100steps.general.message.impl.GeneralMessage;
import net._100steps.general.message.impl.FileMessage;

import org.hibernate.HibernateException;

import sun.misc.BASE64Decoder;

public class FileManagerDefaultImpl implements FileManager
{
	private FileDAO fileDAO;
	private BASE64Decoder base64Decoder;
	
	public FileManagerDefaultImpl()
	{
		base64Decoder = new BASE64Decoder();
	}

	@Override
	public Message addFile(String name, String type, String content, String remark)
	{
		File file = new File();
		try
		{
			byte[] data =  base64Decoder.decodeBuffer(content);
			
			file.setName(name);
			file.setType(type);
			file.setContent(data);
			file.setRemark(remark);
			fileDAO.save(file);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(405010, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505010, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705010, null);
		}
		return new GeneralMessage(0, file.getId());
	}

	@Override
	public Message getFile(int id)
	{
		File file;
		try
		{
			file = fileDAO.getById(id);
			if(file == null)
				return new ErrorMessage(605011, null);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			return new ErrorMessage(405020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705020, null);
		}
		return new FileMessage(0, file);
	}

	@Override
	public Message deleteFile(int id)
	{
		try
		{
			fileDAO.delete(id);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(405020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705020, null);
		}
		return new GeneralMessage(0, null);
	}
	
	public void setFileDAO(FileDAO fileDAO)
	{
		this.fileDAO = fileDAO;
	}

	@Override
	public Message appendFile(int id, String content)
	{
		File file;
		try
		{
			file = fileDAO.getById(id);
			if(file == null)
				return new ErrorMessage(605011, null);
			
			byte[] newData =  base64Decoder.decodeBuffer(content);
			byte[] totalData = new byte[newData.length + file.getContent().length];
			System.arraycopy(file.getContent(), 0, totalData, 0, file.getContent().length);
			System.arraycopy(newData, 0, totalData, file.getContent().length, newData.length);
			file.setContent(totalData);
			fileDAO.update(file);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			return new ErrorMessage(405020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705020, null);
		}
		return new GeneralMessage(0, null);
	}

}
