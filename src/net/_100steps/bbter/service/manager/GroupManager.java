package net._100steps.bbter.service.manager;

import net._100steps.general.message.Message;

public interface GroupManager
{
	Message addGroup(String name);
	Message changeGroup(int id, String name);
	Message deleteGroup(int id);
	Message getGroup(int id);
	Message getAllGroups();
}
