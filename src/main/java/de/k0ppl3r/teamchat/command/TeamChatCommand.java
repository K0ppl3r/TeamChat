package de.k0ppl3r.teamchat.command;

import de.k0ppl3r.teamchat.TeamChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class TeamChatCommand implements CommandExecutor {

    private final TeamChat plugin;
    public ArrayList<UUID> onlyteamchat = new ArrayList<UUID>();
    ArrayList<UUID> togglechat = new ArrayList<UUID>();

    public TeamChatCommand(TeamChat plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("teamchat") || command.getName().equalsIgnoreCase("tc")) {
            if (sender instanceof  Player){
                Player player = (Player)sender;
                if(player.hasPermission("teamchat.use")) {
                    if (args.length == 1) {
                        //Teamchat togglemessage or enable/disable
                        //teamchat help
                        if (args[0].equalsIgnoreCase("toggle")) {
                            if(player.hasPermission("teamchat.toggle")){
                                if(!togglechat.contains(player.getUniqueId())){
                                    togglechat.add(player.getUniqueId());
                                    player.sendMessage("TC >> TeamChat Hide!");
                                } else {
                                    togglechat.remove(player.getUniqueId());
                                    player.sendMessage("TC >> TeamChat Show!");
                                }
                            } else {
                                player.sendMessage("TC >> You can't toggle TeamChat!");
                            }
                            return true;
                        } else if(args[0].equalsIgnoreCase("help")) {
                            player.sendMessage("TC HELP >> \"/teamchat toggle\" | Toggle Teamchat Visibility!");
                            player.sendMessage("TC HELP >> \"/teamchat <Message>\" | Send a Message in TeamChat!");
                            player.sendMessage("TC HELP >> \"/teamchat help\" | View this Message!");
                            return true;
                        } else {
                            for(Player team : Bukkit.getOnlinePlayers()){
                                if(team.hasPermission("teamchat.use")){
                                    if(!togglechat.contains(team.getUniqueId())) {
                                        team.sendMessage("§cTC §8§l>> §c" + player.getName() + " §8- §e" + args[0]);
                                    }
                                } else {
                                    player.sendMessage("Unknown command type \"/help\" for help!");
                                }
                            }
                        }
                        return false;
                    } else if (args.length >= 1) {
                        //teamchat <Message>
                        StringBuilder message = new StringBuilder();
                        for(int i = 0 ; i < args.length ; i++) {
                            message.append(args[i]).append(" ");
                        }
                        for(Player team : Bukkit.getOnlinePlayers()){
                            if(team.hasPermission("teamchat.use")){
                                if(!togglechat.contains(team.getUniqueId())) {
                                    team.sendMessage("§cTC §8§l>> §c" + player.getName() + " §8- §e" + message.toString());
                                }
                            } else {
                                player.sendMessage("Unknown command type \"/help\" for help!");
                            }
                        }
                    } else {
                        player.sendMessage("Type \"/teamchat help\" for help");
                    }
                } else {
                    player.sendMessage("Unknown command type \"help\" for help!");
                }
            } else {
                sender.sendMessage("Du musst ein Spieler sein!");
            }
        }


        return false;
    }
}
