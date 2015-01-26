package net._100steps.bbter.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_details")
public class UserDetail
{
	static public enum Sex
	{
		MALE, FAMALE
	}
	
	private int id;
	private String name;
	private Sex sex;
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

	public void setId(int id)
	{
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setSex(Sex sex)
	{
		this.sex = sex;
	}

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="ENUM('MALE,'FEMALE','UNKNOW')")
	public Sex getSex()
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

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated()
	{
		return created;
	}

	public void setModified(Date modified)
	{
		this.modified = modified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified()
	{
		return modified;
	}
}
