package net._100steps.bbter.service.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetail {
	private int id;
	private String name;
	private String sex;
	private String portrait;
	private String dormitory;
	private int room;
	private int collegeId;
	private String major;
	private String classField;
	private Date birth;
	private String politics;
	private String origo;
	private String mobile;
	private String shortMobile;
	private String qq;
	private String weibo;
	private String remark;
	private Date created;
	private Date modified;
	private int userId;
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getSex()
	{
		return sex;
	}
	public void setPortrait(String portrait)
	{
		this.portrait = portrait;
	}
	public String getPortrait()
	{
		return portrait;
	}
	public void setDormitory(String dormitory)
	{
		this.dormitory = dormitory;
	}
	public String getDormitory()
	{
		return dormitory;
	}
	public void setRoom(int room)
	{
		this.room = room;
	}
	public int getRoom()
	{
		return room;
	}
	
	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}
	@Column(name = "college_id")
	public int getCollegeId()
	{
		return collegeId;
	}
	public void setMajor(String major)
	{
		this.major = major;
	}
	public String getMajor()
	{
		return major;
	}
	
	public void setClassField(String classField)
	{
		this.classField = classField;
	}
	@Column(name = "class")
	public String getClassField()
	{
		return this.classField;
	}
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public Date getBirth()
	{
		return birth;
	}
	public void setPolitics(String politics)
	{
		this.politics = politics;
	}
	public String getPolitics()
	{
		return politics;
	}
	public void setOrigo(String origo)
	{
		this.origo = origo;
	}
	public String getOrigo()
	{
		return origo;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setShortMobile(String shortMobile)
	{
		this.shortMobile = shortMobile;
	}
	public String getShortMobile()
	{
		return shortMobile;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	public String getQq()
	{
		return qq;
	}
	public void setWeibo(String weibo)
	{
		this.weibo = weibo;
	}
	public String getWeibo()
	{
		return weibo;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setCreated(Date created)
	{
		this.created = created;
	}
	public Date getCreated()
	{
		return created;
	}
	public void setModified(Date modified)
	{
		this.modified = modified;
	}
	public Date getModified()
	{
		return modified;
	}
}
