package eu.gods.elektrocloud.godsurvival.config;

import eu.gods.elektrocloud.godsurvival.GodSurvival;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private String prefix;
    private String noperms;
    private GodSurvival plugin;

    public ConfigManager(GodSurvival plugin){
        this.plugin = plugin;
    }

    private void loadFile() {
        final File file = new File("plugins//GodSurvival", "config.yml");
        if(!file.exists()) {
            final File folder = new File("plugins//GodSurvival");
            if(!folder.exists()) folder.mkdirs();
            try {
                file.createNewFile();
                this.writeDefault();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeDefault() throws IOException {
        final File file = new File("plugins//GodSurvival", "config.yml");
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("Config.Prefix", "&7» &bSystem &7| ");
        configuration.set("Config.NoPerms", "&cKeine Rechte!");
        configuration.save(file);
    }

    private void loadSettings() {
        final File file = new File("plugins//GodSurvival", "config.yml");
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        setPrefix(configuration.getString("Config.Prefix").replace("&","§"));
        setNoperms(configuration.getString("Config.NoPerms").replace("&", "§"));
    }

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix(){
        return prefix;
    }

    public String getNoperms() {
        return noperms;
    }

    public void setNoperms(String noperms) {
        this.noperms = noperms;
    }
}
