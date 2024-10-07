package com.recolor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("recolor")
public interface RecolorConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "Check the sidebar for recoloring options"
	)
	default String greeting()
	{
		return "Welcome to HitSplat Recolor";
	}
}
