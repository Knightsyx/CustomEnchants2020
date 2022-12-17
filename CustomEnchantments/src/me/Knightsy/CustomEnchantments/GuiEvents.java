package me.Knightsy.CustomEnchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GuiEvents implements Listener{
	
	private Main plugin;
	
	/* CONSTRUCTORS */
	public GuiEvents(){}
	public GuiEvents(Main instance) {
		plugin = instance;
	
	/* END CONSTRUCTORS */
	}
	
	//
	//
	//
	
	/* INFO GUI EVENTHANDLER */
	@EventHandler()
	public void infoInv(InventoryClickEvent event) {
		if(!event.getInventory().equals(plugin.InvCe))
			return;
		
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		
		if (event.getSlot() == 53 && event.getCurrentItem().getType() == Material.RED_CONCRETE) {
			// closes GUI
			player.openInventory(plugin.CeInv);
		}
		
		return;
	}
	/* INFO GUI EVENTHANDLER END */
	
	//
	//
	//
	
	/* MENU GUI EVENTHANDLER */
	@EventHandler()
	public void invMain(InventoryClickEvent event) {
		if(!event.getInventory().equals(plugin.CeInv))
			return;
		
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		
		if(event.getSlot() == 11 && event.getCurrentItem().getType() == Material.BOOK) {
			plugin.CeAdminInv();
			player.openInventory(plugin.InvCe);
			
		}
		if(event.getSlot() == 13 && event.getCurrentItem().getType() == Material.OAK_SIGN) {
			
		}
		
		if(event.getSlot() == 15 && event.getCurrentItem().getType() == Material.GOLD_INGOT) {
			plugin.enchantBuy();
			player.openInventory(plugin.BuyInv);
		}
		return;
	}
	
	/* MENU GUI EVENTHANDLER END */
	
	//
	//
	//
	
	/* PURCHASE GUI EVENTHANDLER */
	@EventHandler()
	public void purchaseInv(InventoryClickEvent event) {
		if(!event.getInventory().equals(plugin.BuyInv))
			return;
		
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		
		ItemStack item = new ItemStack (Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("Right click to roll a random Enchantment");
		meta.setLore(lore);
		
		int contents = (int) plugin.eco.getBalance(player);
		if(event.getCurrentItem().getType() == Material.KNOWLEDGE_BOOK) {
			if(event.getSlot() == 11) {
				if(plugin.eco.getBalance(player) >= 25000) {
					plugin.eco.withdrawPlayer(player, 25000);
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fCommon &7Enchantment Book"));
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
				}else {
					int balneed = 25000 - contents; 
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Insufficent Funds! Need $" + balneed);
				}
			}
			if(event.getSlot() == 12) {
				if(plugin.eco.getBalance(player) >= 50000) {
					plugin.eco.withdrawPlayer(player, 50000);
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aUnommon &7Enchantment Book"));
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
				}else {
					int balneed = 50000 - contents; 
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Insufficent Funds! Need $" + balneed);
				}
			}
			if(event.getSlot() == 13) {
				if(plugin.eco.getBalance(player) >= 100000) {
					plugin.eco.withdrawPlayer(player, 100000);
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Rare &7Enchantment Book"));
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
				}else {
					int balneed = 100000 - contents;
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Insufficent Funds! Need $" + balneed);
				}
			}
			if(event.getSlot() == 14) {
				if(plugin.eco.getBalance(player) >= 150000) {
					plugin.eco.withdrawPlayer(player, 150000);
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Legendary &7Enchantment Book"));
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
				}else {
					int balneed = 150000 - contents;
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Insufficent Funds! Need $" + balneed);
				}
				
			}
			if(event.getSlot() == 15) {
				if(plugin.eco.getBalance(player) >= 200000) {
					plugin.eco.withdrawPlayer(player, 200000);
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Exotic &7Enchantment Book"));
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
				}else {
					int balneed = 200000 - contents;
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Insufficent Funds! Need $" + balneed);
				}
			}
		}
		
		if(event.getSlot() == 26 && event.getCurrentItem().getType() == Material.RED_CONCRETE) {
			player.openInventory(plugin.CeInv);
		}
	}
	/* PURCHASE GUI EVENTHANDLER END*/
	
	//
	//
	//
	
}
