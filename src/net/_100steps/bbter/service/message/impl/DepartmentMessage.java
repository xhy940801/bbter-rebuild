package net._100steps.bbter.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.bbter.service.message.Message;
import net._100steps.bbter.service.model.Department;
import net._100steps.bbter.service.util.commontree.CommonTree;
import net._100steps.bbter.service.util.commontree.GeneralCommonTree;
import net._100steps.bbter.service.util.commontree.GeneralNode;

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

	@Override
	public CommonTree getDataTree()
	{
		CommonTree tree = new GeneralCommonTree();
		List<CommonTree> departmentDatas = new ArrayList<CommonTree>();
		for(Department department : departments)
		{
			CommonTree departmentData = new GeneralCommonTree();
			departmentData.add(new GeneralNode("id", department.getId()));
			departmentData.add(new GeneralNode("name", department.getName()));
			departmentDatas.add(departmentData);
		}
		tree.add(new GeneralNode("code", msgCode));
		tree.add(new GeneralNode("data", departmentDatas));
		return tree;
	}
}
