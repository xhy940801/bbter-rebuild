package net._100steps.bbter.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.model.Department;

public class DepartmentMessage implements Message
{
	private final int msgCode;
	private final List<Department> departments;

	public DepartmentMessage(Department department)
	{
		this.msgCode = 0;
		departments = new ArrayList<Department>();
		departments.add(department);
	}
	
	public DepartmentMessage(List<Department> departments)
	{
		this.msgCode = 0;
		this.departments = departments;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}
}
