package net._100steps.bbter.service.manager.defaultimpl;

public class NameConf
{
	public final String SYSNAME;
	public final String USMNAME;
	public final String GRMNAME;
	public final String DEMNAME;
	
	public NameConf()
	{
		SYSNAME = "bbter";
		USMNAME = "User";
		GRMNAME = "Group";
		DEMNAME = "Department";
	}
	
	public NameConf(String s, String u, String g, String d)
	{
		SYSNAME = s;
		USMNAME = u;
		GRMNAME = g;
		DEMNAME = d;
	}
}
