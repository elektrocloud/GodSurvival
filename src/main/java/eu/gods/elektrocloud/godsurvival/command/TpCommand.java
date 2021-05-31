package eu.gods.elektrocloud.godsurvival.command;

import eu.gods.elektrocloud.godsurvival.GodSurvival;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private String prefix;
    private String noperms;
    private GodSurvival plugin;

    public TpCommand(GodSurvival plugin){
        this.prefix = plugin.getConfig().getString("Config.Prefix");
        this.plugin = plugin;
        this.noperms = plugin.getConfig().getString("Config.NoPerms");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Du musst ein Spieler sein!");
            return false;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("Godsurvival.tp"))){
            player.sendMessage(prefix + noperms);
            return false;
        }

        if(args.length == 0){
            player.sendMessage(prefix + "§7Nutze §b/tp <Spieler>");
            player.sendMessage(prefix + "37Nutze §b/tp <Spieler> <Spieler>");
            return false;
        }

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(!(target.isOnline())){
                player.sendMessage(prefix + "§7Der Spieler §b" + args[0] + " §7ist aktuell §cnicht §7Online!");
                return false;
            }

            player.teleport(target.getLocation());
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5L, 5L);
            player.sendMessage(prefix + "§7Du wurdest zu §b" + args[0] + "§7teleportiert!");
        }

        if(args.length == 2){
            Player target = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);
            if(!(target2.isOnline())){
                player.sendMessage(prefix + "§7Der Spieler §b" + args[0] + " §7ist aktuell §cnicht §7Online!");
                return false;
            }
            if(!(target.isOnline())){
                player.sendMessage(prefix + "§7Der Spieler §b" + args[0] + " §7ist aktuell §cnicht §7Online!");
                return false;
            }

            target.teleport(target2.getLocation());
            target.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5L, 5L);
            player.sendMessage(prefix + "§7Der Spieler §b" + args[0] + " §7wurde zu §b" + args[1] + " §7teleportiert!");
        }


        return false;
    }
}
