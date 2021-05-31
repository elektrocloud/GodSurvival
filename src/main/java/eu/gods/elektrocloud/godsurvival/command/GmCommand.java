package eu.gods.elektrocloud.godsurvival.command;

import eu.gods.elektrocloud.godsurvival.GodSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor {

    private String prefix;
    private String noperms;
    private GodSurvival plugin;

    public GmCommand(GodSurvival plugin){
        this.plugin = plugin;
        this.noperms = plugin.getConfig().getString("Config.NoPerms");
        this.prefix = plugin.getConfig().getString("Config.Prefix");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Du musst ein Spieler sein!");
            return false;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("GodSurvival.gm"))){
            player.sendMessage(prefix + noperms);
            return false;
        }

        return false;
    }
}
