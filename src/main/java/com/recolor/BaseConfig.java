package com.recolor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("hitsplatrecolorbase")
public interface BaseConfig extends Config
{
    @ConfigItem(
        keyName = "enablePlugin",
        name = "Enable Plugin",
        description = "Toggles the Hitsplat Recolor plugin"
    )
    default boolean enablePlugin()
    {
        return true;
    }

    @ConfigItem(
        keyName = "showDebugMessages",
        name = "Show Debug Messages",
        description = "Displays debug messages in the chat box"
    )
    default boolean showDebugMessages()
    {
        return false;
    }
}