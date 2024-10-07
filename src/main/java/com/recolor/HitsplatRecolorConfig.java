package com.recolor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.awt.Color;

@ConfigGroup("hitsplatrecolor")
public interface HitsplatRecolorConfig extends BaseConfig
{
    @ConfigItem(
        keyName = "stabColor",
        name = "Stab Hitsplat Color",
        description = "Color for stab hitsplats"
    )
    default Color stabColor()
    {
        return Color.RED;
    }

    @ConfigItem(
        keyName = "slashColor",
        name = "Slash Hitsplat Color",
        description = "Color for slash hitsplats"
    )
    default Color slashColor()
    {
        return Color.GREEN;
    }

    @ConfigItem(
        keyName = "crushColor",
        name = "Crush Hitsplat Color",
        description = "Color for crush hitsplats"
    )
    default Color crushColor()
    {
        return Color.BLUE;
    }

    @ConfigItem(
        keyName = "magicColor",
        name = "Magic Hitsplat Color",
        description = "Color for magic hitsplats"
    )
    default Color magicColor()
    {
        return Color.MAGENTA;
    }

    @ConfigItem(
        keyName = "rangedColor",
        name = "Ranged Hitsplat Color",
        description = "Color for ranged hitsplats"
    )
    default Color rangedColor()
    {
        return Color.YELLOW;
    }
}