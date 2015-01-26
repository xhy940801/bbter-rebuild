package net._100steps.bbter.service.manager.defaultimpl;

import java.util.List;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.department.DepartmentDAO;
import net._100steps.bbter.service.manager.DepartmentManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.DepartmentMessage;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.message.impl.GeneralMessage;
import net._100steps.bbter.service.model.Department;

public class DepartmentManagerDefaultImpl implements DepartmentManager
{

	private DepartmentDAO departmentDAO;

	@Override
	public Message addDepartment(String name)
	{
		Department department = new Department();
		department.setName(name);
		if(name.length() > 50)
			return new ErrorMessage(203010);
		try
		{
			departmentDAO.save(department);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(403010, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(503010, e);
		}
		return new DepartmentMessage(department);
	}

	@Override
	public Message changeDepartment(int id, String name)
	{
		if(name.length() > 50)
			return new ErrorMessage(203020);
		Department department;
		try
		{
			department = departmentDAO.getById(id);
			if(department == null)
				return new ErrorMessage(303020);
			department.setName(name);
			departmentDAO.update(department);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(403020, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(503020, e);
		}
		return new DepartmentMessage(department);
	}

	@Override
	public Message deleteDepartment(int id)
	{
		try
		{
			if(id < 0)
				return new ErrorMessage(203030);
			departmentDAO.delete(id);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(403030, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(503030, e);
		}
		return new GeneralMessage(0, null);
	}

	@Override
	public Message getDepartment(int id)
	{
		Department department;
		try
		{
			if(id < 0)
				return new ErrorMessage(203040);
			department = departmentDAO.getById(id);
			if(department == null)
				return new ErrorMessage(303040);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(403040, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(503040, e);
		}
		return new DepartmentMessage(department);
	}

	@Override
	public Message getAllDepartments()
	{
		List<Department> departments;
		try
		{
			departments = departmentDAO.getAll();
		}
		catch(DAOException e)
		{
			return new ErrorMessage(403050, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(503050, e);
		}
		return new DepartmentMessage(departments);
	}
	
	public void setDepartmentDAO(DepartmentDAO departmentDAO)
	{
		this.departmentDAO = departmentDAO;
	}

}
