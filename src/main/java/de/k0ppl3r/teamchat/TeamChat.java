package de.k0ppl3r.teamchat;

import de.k0ppl3r.teamchat.command.TeamChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamChat extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("teamchat").setExecutor(new TeamChatCommand(this));

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}


