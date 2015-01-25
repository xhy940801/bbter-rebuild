package net._100steps.bbter.service.manager.defaultimpl;

import java.util.List;

import net._100steps.bbter.service.dao.DAOException;
import net._100steps.bbter.service.dao.departments.DepartmentDAO;
import net._100steps.bbter.service.dao.model.Department;
import net._100steps.bbter.service.manager.DepartmentManager;
import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.message.impl.DepartmentMessage;
import net._100steps.bbter.service.message.impl.ErrorMessage;
import net._100steps.bbter.service.message.impl.GeneralMessage;

public class DepartmentManagerDefaultImpl implements DepartmentManager
{

	private DepartmentDAO departmentDAO;

	@Override
	public Message addDepartment(String name)
	{
		Department Department = new Department();
		Department.setName(name);
		if(name.length() > 50)
			return new ErrorMessage(202010);
		try
		{
			departmentDAO.save(Department);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402010, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502010, e);
		}
		return new DepartmentMessage(Department);
	}

	@Override
	public Message changeDepartment(int id, String name)
	{
		if(name.length() > 50)
			return new ErrorMessage(202020);
		Department Department;
		try
		{
			Department = departmentDAO.getById(id);
			if(Department == null)
				return new ErrorMessage(302020);
			departmentDAO.save(Department);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402020, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502020, e);
		}
		return new DepartmentMessage(Department);
	}

	@Override
	public Message deleteDepartment(int id)
	{
		try
		{
			if(id < 0)
				return new ErrorMessage(202030);
			departmentDAO.delete(id);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402030, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502030, e);
		}
		return new GeneralMessage(id, null);
	}

	@Override
	public Message getDepartment(int id)
	{
		Department Department;
		try
		{
			if(id < 0)
				return new ErrorMessage(202040);
			Department = departmentDAO.getById(id);
			if(Department == null)
				return new ErrorMessage(302040);
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402040, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502040, e);
		}
		return new GeneralMessage(id, null);
	}

	@Override
	public Message getAllDepartments()
	{
		List<Department> Departments;
		try
		{
			Departments = departmentDAO.getAll();
		}
		catch(DAOException e)
		{
			return new ErrorMessage(402050, e);
		}
		catch(RuntimeException e)
		{
			return new ErrorMessage(502050, e);
		}
		return new DepartmentMessage(Departments);
	}

}
