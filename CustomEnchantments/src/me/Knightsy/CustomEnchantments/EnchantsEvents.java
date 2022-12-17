package me.Knightsy.CustomEnchantments;

import java.util.Collection;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantsEvents implements Listener{
	
	private Main plugin;
	
	/* CONSTRUCTORS */
	public EnchantsEvents(){}
	public EnchantsEvents(Main instance) {
		plugin = instance;
	
	/* END CONSTRUCTORS */
	}
	
	//
	// Custom Enchantment Events
	//
	
	/* TELEPATHY EVENTHANDLER */
	@EventHandler()
	public void telepathy(BlockBreakEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand() == null)
			return;
		if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
			return;
		if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPATHY))
			return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
			return;
		if (event.getPlayer().getInventory().firstEmpty() == -1)
			return;
		if (event.getBlock().getState() instanceof Container)
			return;
		
		event.setDropItems(false);
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
		if(drops.isEmpty())
			return;
		player.getInventory().addItem(drops.iterator().next());
	}
	/* END TELEPATHY EVENTHANDLER */
	
	//
	//
	//
	
	/* EXCAVATOR EVENTHANDLER */
	@EventHandler()
	public void excavator(BlockBreakEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand() == null)
			return;
		if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.EXCAVATOR))
			return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
			return;
		if (event.getPlayer().getInventory().firstEmpty() == -1)
			return;
		
		
	}
	/* END EXCAVATOR EVENTHANDLER */
	
	//
	//
	//
	
	/* BESERKER EVENTHANDLER */
	@EventHandler()
	
	public void beserker(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			double currentHealth = player.getHealth();
			Map<Enchantment, Integer> enchantments = player.getInventory().getChestplate().getEnchantments();
			for (Enchantment enchantment : enchantments.keySet()) {
				Integer power = enchantments.get(enchantment);
				if (player.getInventory().getChestplate().getItemMeta().hasEnchant(CustomEnchants.BESERKER)) {
					if(power == 1) {
						if(currentHealth < 20)
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
					}
					if(power == 2) {
						if(currentHealth < 20)
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
					}
				}
			}
		}
	}
	/* END BESERKER EVENTHANDLER */
	
	//
	// Custom Enchantment Events End 
	//
	
	/* BOOK INTERACT EVENT */
	@EventHandler()
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(event.getPlayer().getInventory().getItemInMainHand() == null)
				return;
			if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
				return;
			Player player = event.getPlayer();
			if(player.getInventory().getItemInMainHand().getType() == Material.BOOK) {
				if(player.getInventory().getItemInMainHand().getItemMeta().hasLore()){
					for (String s : player.getInventory().getItemInMainHand().getItemMeta().getLore()) {
						if(s.contains("Custom Enchantment")) {
							player.openInventory(plugin.InvACe);
							
						}
					}
				}
			}
		}
	}
	/* END BOOK INTERACT EVENT */
}