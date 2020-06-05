package de.k0ppl3r.teamchat.utils;

import de.k0ppl3r.teamchat.TeamChat;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final TeamChat plugin;

    public Config(TeamChat plugin){
        this.plugin = plugin;
    }

    public void createConfig() {
        File file = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        yamlConfiguration.options().copyDefaults(true);

        yamlConfiguration.addDefault("Information:", "You can use Color Codes! (&1, &2, &3, etc.)");
        yamlConfiguration.addDefault("Message.Prefix", "");
        yamlConfiguration.addDefault("Message.NoPermissions", "");
        yamlConfiguration.addDefault("Message.TeamChat.Format", "");
        yamlConfiguration.addDefault("Message.TeamChat.Help", "");
        yamlConfiguration.addDefault("Message.TeamChat.Toggle.Hide", "");
        yamlConfiguration.addDefault("Message.TeamChat.Toggle.Show", "");
        yamlConfiguration.addDefault("Message.TeamChat.Toggle.NoPermissions", "");
        yamlConfiguration.addDefault("Message.TeamChat.Help.Help", "");
        yamlConfiguration.addDefault("Message.TeamChat.Help.Toggle", "");
        yamlConfiguration.addDefault("Message.TeamChat.Help.TeamChat", "");
        yamlConfiguration.addDefault("Replacer.Player", "");
        yamlConfiguration.addDefault("Replacer.Message", "");
        yamlConfiguration.addDefault("Replacer.Prefix", "");

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
