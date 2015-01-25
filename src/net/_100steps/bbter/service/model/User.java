package net._100steps.bbter.service.model;


public class User {
		private int id;
		private String studentNumber;
		private String password;
		private String email;
		private int status;
		private int departmentId;
		private int groupId;
		
		public User(){
			
		}
		
		public int getId()
		{
			return id;
		}
		
		public void setId(int id)
		{
			this.id = id;
		}
		
		public String getStudentNumber()
		{
			return studentNumber;
		}
		
		public void setStudentNumber(String studentNumber)
		{
			this.studentNumber = studentNumber;
		}
		public String getPassword()
		{
			return password;
		}
		public void setPassword(String password)
		{
			this.password = password;
		}
		public String getEmail()
		{
			return email;
		}
		public void setEmail(String email)
		{
			this.email = email;
		}
		public int getStatus()
		{
			return status;
		}
		public void setStatus(int status)
		{
			this.status = status; 
		}
		public int getDepartmentId()
		{
			return departmentId;
		}
		public void setDeparmentId(int departmentId)
		{
			this.departmentId = departmentId;
		}
		public int getGroupId()
		{
			return groupId;
		}
		public void setGroupId(int groupId)
		{
			this.groupId = groupId;
		}
		

}
