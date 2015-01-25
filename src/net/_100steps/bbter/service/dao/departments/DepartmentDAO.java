package net._100steps.bbter.service.dao.departments;

import java.util.List;

import net._100steps.bbter.service.dao.model.Department;

public interface DepartmentDAO
{
	public void save(Department department);
	public void update(Department department);
	public void delete(int id);
	public Department getById(int id);
	public List<Department> getAll();
}
