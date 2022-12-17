package me.Knightsy.CustomEnchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;



public class Main extends JavaPlugin{
	
	public Economy eco;
	public Inventory InvCe;
	public Inventory CeInv;
	public Inventory InvACe;
	public Inventory BuyInv;
	
	@Override
	public void onEnable() {
		if(!setupEconomy()) {
			System.out.println(ChatColor.RED + "You must have Vault and an Economy Plugin Installed!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		CustomInvent();
		CustomEnchants.register();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		
	}

	private void registerEvents() {
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new GuiEvents(this), this);
		manager.registerEvents(new EnchantsEvents(this), this);
	}
	
	@SuppressWarnings("unused")
	private void registerCommands() {
		
	}
	
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if (economy != null)
			eco = economy.getProvider();
		return (eco != null);
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("ce")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Console can not use this command!");
				return true;
			}
			Player player = (Player) sender;
			player.openInventory(CeInv);
		}
		return true;
	}
	
	/* CREATE INVENTORY */	
	public void CeAdminInv() { 
			
		InvCe = Bukkit.createInventory(null, 54,ChatColor.GOLD + "Custom Enchant List");
			
		ItemStack item = new ItemStack(Material.GLASS);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		for (int slot = 0; slot<53 ;slot++) {
			item.setType(Material.BLACK_STAINED_GLASS_PANE);
			meta.setDisplayName(" ");
			lore.clear();
			item.setItemMeta(meta);
			InvCe.setItem(slot, item);
		}	
		// Telepathy Enchant Book
		item.setType(Material.BOOK);
		meta.setDisplayName(ChatColor.GOLD + "Telepathy");
		lore.clear();
		lore.add(ChatColor.GRAY + "Telepathy Enchant");
		lore.add(ChatColor.GRAY + "When breaking a block all");
		lore.add(ChatColor.GRAY + "items broke go into your inventory");
		meta.setLore(lore);
		item.setItemMeta(meta);
		InvCe.setItem(10, item);

		item.setType(Material.BOOK);
		meta.setDisplayName(ChatColor.GOLD + "Excavator");
		lore.clear();
		lore.add(ChatColor.GRAY + "Excavator Enchant");
		lore.add(ChatColor.GRAY + "");
		lore.add(ChatColor.GRAY + "");
		meta.setLore(lore);
		item.setItemMeta(meta);
		InvCe.setItem(11, item);
		
		// Beserker Enchant Book
		item.setType(Material.BOOK);
		meta.setDisplayName(ChatColor.GOLD + "Beserker");
		lore.clear();
		lore.add(ChatColor.GRAY + "Beserker Enchant");
		lore.add(ChatColor.GRAY + "Gives a short burst of");
		lore.add(ChatColor.GRAY + "strength when health is low.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		InvCe.setItem(12, item);
		
		
		item.setType(Material.BOOK);
		meta.setDisplayName(ChatColor.GOLD + "Frost Walker");
		lore.clear();
		lore.add(ChatColor.GRAY + "Frost Walker Enchant");
		lore.add(ChatColor.GRAY + "Allows you to walk across");
		lore.add(ChatColor.GRAY + "water.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		InvCe.setItem(13, item);
		//Close Button
			
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Return To Menu");
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		InvCe.setItem(53, item);
			
	}
	/* CREATE INVENTORY END*/
	
	
	public void CustomInvent() {
		
		CeInv = Bukkit.createInventory(null, 27,ChatColor.DARK_GRAY + "Custom Enchants");
		
		ItemStack item = new ItemStack(Material.GLASS);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		meta.setLore(lore);	
			
		for (int slot = 0; slot<27 ;slot++) {
			item.setType(Material.BLACK_STAINED_GLASS_PANE);
			meta.setDisplayName(" ");
			lore.clear();
			item.setItemMeta(meta);
			CeInv.setItem(slot, item);
		}
		
		item.setType(Material.BOOK);
		meta.setDisplayName(ChatColor.GRAY + "Custom Enchant Info");
		lore.clear();
		lore.add("Enchants Custom");
		meta.setLore(lore);
		item.setItemMeta(meta);
		CeInv.setItem(11, item);
		
		item.setType(Material.OAK_SIGN);
		meta.setDisplayName(ChatColor.GRAY + "Custom Enchants Help");
		lore.clear();
		lore.add("Stuck or want to know more about Custom Enchants?");
		lore.add("This should give you the answers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		CeInv.setItem(13, item);
		
		item.setType(Material.GOLD_INGOT);
		meta.setDisplayName(ChatColor.GRAY + "Purchase Custom Enchantments");
		lore.clear();
		lore.add("Purchase a random enchantment with you exp");
		lore.add("by clicking one of the 5 tiers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		CeInv.setItem(15, item);
		
		
	}
	
	
	
	public void enchantBuy() {
		
		BuyInv = Bukkit.createInventory(null, 27,ChatColor.DARK_GRAY + "Purchase Enchants");
		
		ItemStack item = new ItemStack(Material.GLASS);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		meta.setLore(lore);
		
		for(int slot = 0; slot<26; slot++) {
			item.setType(Material.BLACK_STAINED_GLASS_PANE);
			meta.setDisplayName(" ");
			lore.clear();
			item.setItemMeta(meta);
			BuyInv.setItem(slot, item);
		}
		
		item.setType(Material.KNOWLEDGE_BOOK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fCommon &7Enchantment"));
		lore.clear();
		lore.add("Once purchased gives you a random common enchantment roll");
		lore.add(ChatColor.WHITE + "Cost | $25000 |");
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(11, item);
		
		item.setType(Material.KNOWLEDGE_BOOK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aUncommon &7Enchantment"));
		lore.clear();
		lore.add("Once purchased gives you a random uncommon enchantment roll");
		lore.add(ChatColor.WHITE + "Cost | $50000 |");
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(12, item);
		
		item.setType(Material.KNOWLEDGE_BOOK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Rare &7Enchantment"));
		lore.clear();
		lore.add("Once purchased gives you a random rare enchantment roll");
		lore.add(ChatColor.WHITE + "Cost | $100000 |");
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(13, item);
		
		item.setType(Material.KNOWLEDGE_BOOK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Legendary &7Enchantment"));
		lore.clear();
		lore.add("Once purchased gives you a random legendary enchantment roll");
		lore.add(ChatColor.WHITE + "Cost | $150000 |");
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(14, item);
		
		item.setType(Material.KNOWLEDGE_BOOK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Exotic &7Enchantment"));
		lore.clear();
		lore.add("Once purchased gives you a random exotic enchantment roll");
		lore.add(ChatColor.WHITE + "Cost | $200000 |");
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(15, item);
		
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.GRAY + "Return To Menu");
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		BuyInv.setItem(26, item);
	}

}



