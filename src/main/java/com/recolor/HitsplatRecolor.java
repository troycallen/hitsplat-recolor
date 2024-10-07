package com.recolor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.Hitsplat;

@Slf4j
@PluginDescriptor(
    name = "HitSplat Recolor",
    description = "Recolors hitsplats based on attack styles",
    tags = {"combat", "hitsplat", "recolor"}
)
public class HitsplatRecolor extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private RecolorConfig config;

    @Override
    protected void startUp() throws Exception
    {
        log.info("HitSplat Recolor started!");
    }

    @Override
    protected void shutDown() throws Exception
    {
        log.info("HitSplat Recolor stopped!");
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied hitsplatApplied)
    {
        Hitsplat hitsplat = hitsplatApplied.getHitsplat();
        
        switch(hitsplat.getHitsplatType())
        {
            case DAMAGE_ME:
                hitsplat.setColor(config.stabColor().getRGB());
                break;
            case DAMAGE_OTHER:
                hitsplat.setColor(config.slashColor().getRGB());
                break;
            case DAMAGE_OPPONENT:
                hitsplat.setColor(config.crushColor().getRGB());
                break;
            // Add cases for other hitsplat types as needed
        }
    }

    @Provides
    RecolorConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(RecolorConfig.class);
    }
}