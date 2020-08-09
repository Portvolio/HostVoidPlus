package wtf.violet.portvolio.hostvoidplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.hostvoidplus.listener.DamageListener;

import java.util.Collections;
import java.util.List;

public final class HostVoidPlus extends JavaPlugin {

    private List<String> hostsByUsername;

    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        final FileConfiguration config = getConfig();
        config.addDefault("hosts", Collections.singletonList("violetwtf"));

        hostsByUsername = config.getStringList("hosts");

        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
    }

    /**
     * Check whether a player is a host
     * @param player Player to check
     * @return Whether they're a host
     */
    public boolean isHost(final Player player)
    {
        return hostsByUsername.contains(player.getName());
    }

}
