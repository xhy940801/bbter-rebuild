package net._100steps.service.manager;

import net._100steps.service.message.Message;

public interface DepartmentManager
{
	Message addDepartment(String name);
	Message changeDepartment(int id, String name);
	Message deleteDepartment(int id);
	Message getDepartment(int id);
	Message getAllDepartments();
}
