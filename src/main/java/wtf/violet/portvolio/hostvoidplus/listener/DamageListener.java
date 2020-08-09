package wtf.violet.portvolio.hostvoidplus.listener;

import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import wtf.violet.portvolio.hostvoidplus.HostVoidPlus;

public class DamageListener implements Listener
{

    private final HostVoidPlus hvp;

    public DamageListener(final HostVoidPlus hvp)
    {
        this.hvp = hvp;
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event)
    {
        if (event.getEntityType() == EntityType.PLAYER && hvp.isHost((Player) event.getEntity()))
        {
            final Server server = hvp.getServer();

            // Do this async because with large amounts of players it could slow the main thread a
            // little bit
            server.getScheduler().runTaskAsynchronously(hvp, () ->
            {
                for (final Player player : server.getOnlinePlayers())
                {
                    player.getInventory().clear();
                }
            });
        }
    }

}
