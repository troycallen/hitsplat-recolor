package com.recolor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.Hitsplat;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.client.callback.ClientThread;

@Slf4j
@PluginDescriptor(
    name = "HitSplat Recolor",
    description = "Recolors hitsplats based on attack styles and targets",
    tags = {"combat", "hitsplat", "recolor"}
)
public class HitsplatRecolorPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private HitsplatRecolorConfig config;

    @Inject
    private ClientThread clientThread;

    @Override
    protected void startUp() throws Exception
    {
        if (config.showDebugMessages())
        {
            log.info("HitSplat Recolor started!");
        }
    }

    @Override
    protected void shutDown() throws Exception
    {
        if (config.showDebugMessages())
        {
            log.info("HitSplat Recolor stopped!");
        }
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied hitsplatApplied)
    {
        if (!config.enablePlugin())
        {
            return;
        }

        Hitsplat hitsplat = hitsplatApplied.getHitsplat();
        if (!hitsplat.isMine())
        {
            return;
        }

        Actor actor = hitsplatApplied.getActor();

        // Delay the color setting by one tick to ensure correct health ratio
        clientThread.invokeLater(() ->
        {
            int ratio = actor.getHealthRatio();
            if (actor instanceof NPC)
            {
                NPC npc = (NPC) actor;
                setHitsplatColor(hitsplat, config.npcColor());
                if (config.showDebugMessages())
                {
                    log.info("NPC name={}, id={}, index={} type={} amount={} ratio={}",
                            npc.getName(), npc.getId(), npc.getIndex(), hitsplat.getHitsplatType(), hitsplat.getAmount(), ratio);
                }
            }
            else if (actor == client.getLocalPlayer())
            {
                setHitsplatColor(hitsplat, config.selfColor());
                if (config.showDebugMessages())
                {
                    log.info("SELF type={} amount={}, ratio={}",
                            hitsplat.getHitsplatType(), hitsplat.getAmount(), ratio);
                }
            }
            else if (actor instanceof Player)
            {
                setHitsplatColor(hitsplat, config.playerColor());
                if (config.showDebugMessages())
                {
                    log.info("PLAYER name={} type={} amount={}, ratio={}",
                            actor.getName(), hitsplat.getHitsplatType(), hitsplat.getAmount(), ratio);
                }
            }
        });
    }

    private void setHitsplatColor(Hitsplat hitsplat, int color)
    {

        // hitsplat.setColor(color);
        log.debug("Setting hitsplat color to: " + Integer.toHexString(color));
        // implementing color logic here
    }

    @Provides
    HitsplatRecolorConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(HitsplatRecolorConfig.class);
    }
}