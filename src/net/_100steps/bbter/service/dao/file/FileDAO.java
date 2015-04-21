package net._100steps.bbter.service.dao.file;

import net._100steps.bbter.service.model.File;

public interface FileDAO
{
	/**
	 * 新增文件
	 * @param file 文件
	 */
	public void save(File file);
	
	/**
	 * 新增更改
	 * @param file 文件
	 */
	public void update(File file);
	
	/**
	 * 通过id获取文件
	 * @param id 文件id
	 * @return 文件对象
	 */
	public File getById(int id);
	
	/**
	 * 通过id删除文件
	 * @param id 文件id
	 */
	public void delete(int id);
}
