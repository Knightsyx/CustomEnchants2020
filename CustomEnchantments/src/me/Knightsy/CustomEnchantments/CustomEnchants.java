package me.Knightsy.CustomEnchantments;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {
	
	public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1);
	public static final Enchantment EXCAVATOR = new EnchantmentWrapper("excavator", "Excavator", 2);
	public static final Enchantment BESERKER = new EnchantmentWrapper("beserker", "Beserker", 2);
	public static final Enchantment FROSTWALKER = new EnchantmentWrapper("frostwalker", "Frost Walker", 1);
	
	public static void register() {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPATHY);
		boolean registered1 = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(EXCAVATOR);
		boolean registered2 = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(BESERKER);

		if(!registered)
			registerEnchantment(TELEPATHY);
		if(!registered1)
			registerEnchantment(EXCAVATOR);
		if(!registered2)
			registerEnchantment(BESERKER);
	}
	
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch(Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered) {
			//send message if wanted to
		}
	}

}
