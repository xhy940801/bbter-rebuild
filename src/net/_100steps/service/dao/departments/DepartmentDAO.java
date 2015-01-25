package net._100steps.service.dao.departments;

import java.util.List;

import net._100steps.service.dao.model.Department;

public interface DepartmentDAO
{
	public void save(Department department);
	public void update(Department department);
	public Department getById(int id);
	public List<Department> getAll();
}
