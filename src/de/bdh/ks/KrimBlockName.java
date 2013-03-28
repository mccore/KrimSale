package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;


public class KrimBlockName 
{
	public static HashMap<String,String> names = new HashMap<String,String>();
	
	public KrimBlockName()
	{
		//INIT VALUES
		names.put("air", "0");
		names.put("stone","1");
		names.put("grassblock","2");
		names.put("dirt","3");
		names.put("cobblestone","4");
		names.put("woodenplanks","5");
		names.put("pinewoodenplanks","5:1");
		names.put("birchwoodenplanks","5:2");
		names.put("djunglewoodenplanks","5:3");
		names.put("sapling","6");
		names.put("birchsapling","6:1");
		names.put("pinesapling","6:2");
		names.put("djunglesapling","6:3");
		names.put("bedrock","7");
		names.put("watersource","8");
		names.put("waterstill","9");
		names.put("lavasource","10");
		names.put("lavastill","11");
		names.put("sand","12");
		names.put("gravel","13");
		names.put("goldore","14");
		names.put("ironore","15");
		names.put("coalore","16");
		names.put("wood","17");
		names.put("birchwood","17:1");
		names.put("pinewood","17:2");
		names.put("djunglewood","17:3");
		names.put("leaves","18");
		names.put("birchleaves","18:6");
		names.put("oakleaves","18:4");
		names.put("spruceleaves","18:5");
		names.put("djungleleaves","18:7");
		names.put("sponge","19");
		names.put("glass","20");
		names.put("lapislazuliore","21");
		names.put("lapislazuliblock","22");
		names.put("dispenser","23");
		names.put("sandstone","24");
		names.put("chiseledsandstone","24:1");
		names.put("smoothsandstone","24:2");
		names.put("noteblock","25");
		names.put("poweredtrack","27");
		names.put("detectortrack","28");
		names.put("stickypiston","29");
		names.put("cobweb","30");
		names.put("grass","31:1");
		names.put("fern","31:2");
		names.put("deadbush","32");
		names.put("piston","33");
		names.put("wool","35");
		names.put("orangewool","35:1");
		names.put("magentawool","35:2");
		names.put("lightbluewool","35:3");
		names.put("yellowwool","35:4");
		names.put("limewool","35:5");
		names.put("pinkwool","35:6");
		names.put("graywool","35:7");
		names.put("lightgraywool","35:8");
		names.put("cyanwool","35:9");
		names.put("purplewool","35:10");
		names.put("bluewool","35:11");
		names.put("brownwool","35:12");
		names.put("greenwool","35:13");
		names.put("redwool","35:14");
		names.put("blackwool","35:15");
		names.put("flower","37");
		names.put("rose","38");
		names.put("brownmushroom","39");
		names.put("redmushroom","40");
		names.put("blockofgold","41");
		names.put("blockofiron","42");
		names.put("doubleslab","43");
		names.put("doublesandstoneslab","43:1");
		names.put("doublewoodenslab","43:2");
		names.put("doublecobbelstoneslab","43:3");
		names.put("doublebricksslab","43:4");
		names.put("doublestonebricksslab","43:5");
		names.put("stoneslab","44");
		names.put("sandstoneslab","44:1");
		names.put("woodenslab","44:2");
		names.put("coobelstoneslab","44:3");
		names.put("bricksslab","44:4");
		names.put("stonebricksslab","44:5");
		names.put("bricks","45");
		names.put("tnt","46");
		names.put("bookshelf","47");
		names.put("mossstone","48");
		names.put("obsidian","49");
		names.put("torch","50");
		names.put("fire","51");
		names.put("monsterspawner(creeper)","52:50");
		names.put("monsterspawner(skeleton)","52:51");
		names.put("monsterspawner(spider)","52:52");
		names.put("monsterspawner(giant)","52:53");
		names.put("monsterspawner(zombie)","52:54");
		names.put("monsterspawner(slime)","52:55");
		names.put("monsterspawner(ghast)","52:56");
		names.put("monsterspawner(pigzombie)","52:57");
		names.put("monsterspawner(enderman)","52:58");
		names.put("monsterspawner(cavespider)","52:59");
		names.put("monsterspawner(silverfish)","52:60");
		names.put("monsterspawner(blaze)","52:61");
		names.put("monsterspawner(lavaslime)","52:62");
		names.put("monsterspawner(pig)","52:90");
		names.put("monsterspawner(sheep)","52:91");
		names.put("monsterspawner(cow)","52:92");
		names.put("monsterspawner(chicken)","52:93");
		names.put("monsterspawner(squid)","52:94");
		names.put("monsterspawner(wolf)","52:95");
		names.put("monsterspawner(mushroomcow)","52:96");
		names.put("monsterspawner(snowman)","52:97");
		names.put("monsterspawner(ozelot)","52:98");
		names.put("monsterspawner(irongolem)","52:99");
		names.put("monsterspawner(villager)","52:120");
		names.put("monsterspawner(horse)","52:220");
		names.put("monsterspawner(ogre)","52:221");
		names.put("monsterspawner(fireogre)","52:222");
		names.put("monsterspawner(caveogre)","52:223");
		names.put("monsterspawner(boar)","52:224");
		names.put("monsterspawner(bear)","52:225");
		names.put("monsterspawner(duck)","52:226");
		names.put("monsterspawner(bigcat)","52:227");
		names.put("monsterspawner(deer)","52:228");
		names.put("monsterspawner(wildwolf)","52:229");
		names.put("monsterspawner(polarbear)","52:230");
		names.put("monsterspawner(wraith)","52:231");
		names.put("monsterspawner(flamewraith)","52:232");
		names.put("monsterspawner(bunny)","52:233");
		names.put("monsterspawner(bird)","52:234");
		names.put("monsterspawner(fox)","52:235");
		names.put("monsterspawner(werewolf)","52:236");
		names.put("monsterspawner(shark)","52:237");
		names.put("monsterspawner(dolphin)","52:238");
		names.put("monsterspawner(fishy)","52:239");
		names.put("monsterspawner(kitty)","52:240");
		names.put("monsterspawner(kittybed)","52:241");
		names.put("monsterspawner(litterbox)","52:242");
		names.put("monsterspawner(rat)","52:243");
		names.put("monsterspawner(mouse)","52:244");
		names.put("monsterspawner(hellrat)","52:245");
		names.put("monsterspawner(scorpion)","52:246");
		names.put("monsterspawner(turtle)","52:247");
		names.put("monsterspawner(crocodile)","52:248");
		names.put("monsterspawner(ray)","52:249");
		names.put("monsterspawner(jellyfish)","52:250");
		names.put("monsterspawner(goat)","52:251");
		names.put("monsterspawner(snake)","52:252");
		names.put("monsterspawner(mocegg)","52:253");
		names.put("monsterspawner(fishbowl)","52:254");
		names.put("monsterspawner(ostrich)","52:255");
		names.put("woodenstairs","53");
		names.put("chest","54");
		names.put("diamondore","56");
		names.put("blockofdiamond","57");
		names.put("craftingtable","58");
		names.put("farmland","60");
		names.put("furnace","61");
		names.put("furnace_on","62");
		names.put("ladder","65");
		names.put("track","66");
		names.put("cobblestonestairs","67");
		names.put("lever","69");
		names.put("pressureplate","70");
		names.put("irondoorblock","71");
		names.put("woodenpressureplate","72");
		names.put("redstoneore","73");
		names.put("redstonetorch_glow","74");
		names.put("redstonetorch_off","75");
		names.put("redstonetorch","76");
		names.put("button","77");
		names.put("snow","78");
		names.put("ice","79");
		names.put("snow","80");
		names.put("cactus","81");
		names.put("clay","82");
		names.put("jukebox","84");
		names.put("fence","85");
		names.put("pumpkin","86");
		names.put("netherrack","87");
		names.put("soulsand","88");
		names.put("glowstone","89");
		names.put("portal","90");
		names.put("jackolantern","91");
		names.put("cake","92");
		names.put("lockedchest","95");
		names.put("trapdoor","96");
		names.put("silverfishstone","97");
		names.put("stonebricks","98");
		names.put("mossystonebricks","98:1");
		names.put("crackedstonebricks","98:2");
		names.put("chiseledstonebricks","98:3");
		names.put("hugebrownmushroom","99");
		names.put("hugeredmushroom","100");
		names.put("ironbars","101");
		names.put("glasspanel","102");
		names.put("melon","103");
		names.put("vines","106");
		names.put("fencegate","107");
		names.put("brickstairs","108");
		names.put("stonebricksatirs","109");
		names.put("mycelium","110");
		names.put("lilypad","111");
		names.put("netherbrick","112");
		names.put("netherbrickfence","113");
		names.put("netherbrickstairs","114");
		names.put("netherwart","115");
		names.put("enchantmenttable","116");
		names.put("endportal","119");
		names.put("endportalframe","120");
		names.put("whitestone","121");
		names.put("endstone","121:1");
		names.put("dragonegg","122");
		names.put("redstonelamp_off","123");
		names.put("redstonelamp","124");
		names.put("Oakwoodslab","126");
		names.put("Sprucewoodslab","126:1");
		names.put("Birchwoodslab","126:2");
		names.put("Junglewoodslab","126:3");
		names.put("Sandstonestairs","128");
		names.put("Emeraldore","129");
		names.put("Tripwirehook","131");
		names.put("emeraldblock","133");
		names.put("Sprucewoodstairs","134");
		names.put("Birchwoodstairs","135");
		names.put("Junglewoodstairs","136");
		names.put("Beacon","138");
		names.put("Cobblestonewall","139");
		names.put("Mossycobblestonewall","139:1");
		names.put("Woodenbutton","143");
		names.put("Anvil","145");
		names.put("Anvil(Damaged)","145:1");
		names.put("Anvil(VeryDamaged)","145:2");
		
		names.put("ironpickaxe","257");
		names.put("ironaxe","258");
		names.put("flintandsteel","259");
		names.put("apple","260");
		names.put("bow","261");
		names.put("arrow","262");
		names.put("coal","263");
		names.put("charcoal","263:1");
		names.put("diamond","264");
		names.put("ironingot","265");
		names.put("goldingot","266");
		names.put("ironsword","267");
		names.put("woodensword","268");
		names.put("woodenshovel","269");
		names.put("woodenpickaxe","270");
		names.put("woodenaxe","271");
		names.put("stonesword","272");
		names.put("stoneshovel","273");
		names.put("stonepickaxe","274");
		names.put("stoneaxe","275");
		names.put("diamondsword","276");
		names.put("diamondspade","277");
		names.put("diamondpickaxe","278");
		names.put("diamondaxe","279");
		names.put("stick","280");
		names.put("bowl","281");
		names.put("mushroomsoup","282");
		names.put("goldensword","283");
		names.put("goldenspade","284");
		names.put("goldenpickaxe","285");
		names.put("goldenaxe","286");
		names.put("string","287");
		names.put("feather","288");
		names.put("gunpowder","289");
		names.put("woodenhoe","290");
		names.put("stonehoe","291");
		names.put("ironhoe","292");
		names.put("diamondhoe","293");
		names.put("goldenmhoe","294");
		names.put("seeds","295");
		names.put("wheat","296");
		names.put("bread","297");
		names.put("leathercap","298");
		names.put("leathertunic","299");
		names.put("leathertrousers","300");
		names.put("leatherboots","301");
		names.put("chainmailhelmet","302");
		names.put("chainchestplate","303");
		names.put("chainmailleggings","304");
		names.put("chainmailboots","305");
		names.put("ironhelmet","306");
		names.put("ironchestplate","307");
		names.put("ironleggings","308");
		names.put("ironboots","309");
		names.put("diamondhelmet","310");
		names.put("diamondchestplate","311");
		names.put("diamondleggings","312");
		names.put("industrialdiamond","313");
		names.put("goldenhelmet","314");
		names.put("goldenchestplate","315");
		names.put("goldenleggings","316");
		names.put("goldenboots","317");
		names.put("flint","318");
		names.put("rawporkchop","319");
		names.put("cookedporkchop","320");
		names.put("painting","321");
		names.put("goldenapple","322");
		names.put("sign","323");
		names.put("woodendoor","324");
		names.put("bucket","325");
		names.put("bucketofwater","326");
		names.put("bucketoflava","327");
		names.put("minecart","328");
		names.put("saddle","329");
		names.put("irondoor","330");
		names.put("redstone","331");
		names.put("snowball","332");
		names.put("boat","333");
		names.put("leather","334");
		names.put("bucketofmilk","335");
		names.put("brick","336");
		names.put("clay","337");
		names.put("sugarcanes","338");
		names.put("paper","339");
		names.put("book","340");
		names.put("slimeball","341");
		names.put("chestcart","342");
		names.put("furnacecart","343");
		names.put("egg","344");
		names.put("compass","345");
		names.put("fishingrod","346");
		names.put("watch","347");
		names.put("glowstonedust","348");
		names.put("rawfish","349");
		names.put("cookedfish","350");
		names.put("inksac","351");
		names.put("rosered","351:1");
		names.put("cactusgreen","351:2");
		names.put("cocoabeans","351:3");
		names.put("lapislazuli","351:4");
		names.put("purpledye","351:5");
		names.put("cyandye","351:6");
		names.put("lightgreydye","351:7");
		names.put("greydye","351:8");
		names.put("pinkdye","351:9");
		names.put("limegreendye","351:10");
		names.put("dandelionyellow","351:11");
		names.put("lightbluedye","351:12");
		names.put("magentadye","351:13");
		names.put("orangedye","351:14");
		names.put("bonemeal","351:15");
		names.put("bone","352");
		names.put("sugar","353");
		names.put("cake","354");
		names.put("bed","355");
		names.put("redstonerepeater","356");
		names.put("cookie","357");
		names.put("map","358");
		names.put("shears","359");
		names.put("melon","360");
		names.put("pumpkinseeds","361");
		names.put("melonseeds","362");
		names.put("rawbeef","363");
		names.put("steak","364");
		names.put("rawchicken","365");
		names.put("cookedchicken","366");
		names.put("rottenflesh","367");
		names.put("ender pearl","368");
		names.put("blazerod","369");
		names.put("ghasttear","370");
		names.put("goldennugget","371");
		names.put("netherwart","372");
		names.put("waterbottle","373");
		names.put("awkwardpotion","373:16");
		names.put("thickpotion","373:32");
		names.put("mundanepotion","373:64");
		names.put("Regeneration Potion (0:45)","373:8193");	
		names.put("Swiftness Potion (3:00)","373:8194");	
		names.put("Fire Resistance Potion (3:00)","373:8195");	
		names.put("Poison Potion (0:45)","373:8196");	
		names.put("Healing Potion","373:8197");	
		names.put("Weakness Potion (1:30)","373:8200");	
		names.put("Strength Potion (3:00)","373:8201");	
		names.put("Slowness Potion (1:30)","373:8202");	
		names.put("Harming Potion","373:8204");	
		names.put("Regeneration Potion II (0:22)","373:8225");	
		names.put("Swiftness Potion II (1:30)","373:8226");	
		names.put("Poison Potion II (0:22)","373:8228");	
		names.put("Healing Potion II","373:8229");	
		names.put("Strength Potion II (1:30)","373:8233");	
		names.put("Harming Potion II","373:8236");	
		names.put("Regeneration Potion (2:00)","373:8257");	
		names.put("Swiftness Potion (8:00)","373:8258");	
		names.put("Fire Resistance Potion (8:00)","373:8259");	
		names.put("Poison Potion (2:00)","373:8260");	
		names.put("Weakness Potion (4:00)","373:8264");	
		names.put("Strength Potion (8:00)","373:8265");	
		names.put("Slowness Potion (4:00)","373:8266");	
		names.put("Fire Resistance Splash (2:15)","373:16378");	
		names.put("Regeneration Splash (0:33)","373:16385");	
		names.put("Swiftness Splash (2:15)","373:16386");	
		names.put("Poison Splash (0:33)","373:16388");	
		names.put("Healing Splash","373:16389");	
		names.put("Weakness Splash (1:07)","373:16392");	
		names.put("Strength Splash (2:15)","373:16393");	
		names.put("Slowness Splash (1:07)","373:16394");	
		names.put("Harming Splash","373:16396");	
		names.put("Swiftness Splash II (1:07)","373:16418");	
		names.put("Poison Splash II (0:16)","373:16420");	
		names.put("Healing Splash II","373:16421");	
		names.put("Strength Splash II (1:07)","373:16425");	
		names.put("Harming Splash II","373:16428");	
		names.put("Regeneration Splash (1:30)","373:16449");	
		names.put("Swiftness Splash (6:00)","373:16450");	
		names.put("Fire Resistance Splash (6:00)","373:16451");	
		names.put("Poison Splash (1:30)","373:16452");	
		names.put("Weakness Splash (3:00)","373:16456");	
		names.put("Strength Splash (6:00)","373:16457");	
		names.put("Slowness Splash (3:00)","373:16458");	
		names.put("Regeneration Splash II (0:16)","373:16471");
		names.put("glassbottle","374");
		names.put("spidereye","375");
		names.put("fermentedspidereye","376");
		names.put("blazepowder","377");
		names.put("magmacream","378");
		names.put("brewingstand","379");
		names.put("cauldron","380");
		names.put("eyeofender","381");
		names.put("glisteringmelon","382");
		names.put("spawncreeper","383:50");
		names.put("spawnskeleton","383:51");
		names.put("spawnspider","383:52");
		names.put("spawnzombie","383:54");
		names.put("spawnslime","383:55");
		names.put("spawnghast","383:56");
		names.put("spawnpigzombie","383:57");
		names.put("spawnenderman","383:58");
		names.put("spawncavespider","383:59");
		names.put("spawnsilverfish","383:60");
		names.put("spawnblaze","383:61");
		names.put("spawnmagmacube","383:62");
		names.put("spawnpig","383:90");
		names.put("spawnsheep","383:91");
		names.put("spawncow","383:92");
		names.put("spawnchicken","383:93");
		names.put("spawnsquid","383:94");
		names.put("spawnwolf","383:95");
		names.put("spawnmooshroom","383:96");
		names.put("spawnsnowgolem","383:97");
		names.put("spawnocelot","383:98");
		names.put("spawnirongolem","383:99");
		names.put("spawnvillager","383:120");
		names.put("bottleo´enchanting","384");
		names.put("firecharge","385");
		names.put("bookandquill","386");
		names.put("writtenbook","387");
		names.put("Emerald","388");
		names.put("Itemframe","389");
		names.put("Flowerpot","390");
		names.put("Carrot","391");
		names.put("Potatoe","392");
		names.put("Bakedpotatoe","393");
		names.put("Poisonedpotatoe","394");
		names.put("Emptymap","395");
		names.put("Goldencarrot","396");
		names.put("SkelletonSkull","397");
		names.put("Witherhead","397:1");
		names.put("Zombiehead","397:2");
		names.put("Head","397:3");
		names.put("Creeperhead","397:4");
		names.put("Carrotonastick","398");
		names.put("Netherstar","399");
		names.put("Pumpkinpie","400");
		names.put("FireRocket","401");
		names.put("Firestar","402");
		names.put("Enchantedbook","403");
		names.put("Disk C418-13","2256");
		names.put("Disk C418-Cat","2257");
		names.put("Disk C418-Blocks","2258");
		names.put("Disk C418-Chirp","2259");
		names.put("Disk C418-Far","2260");
		names.put("Disk C418-Mall","2261");
		names.put("Disk C418-Mellohi","2262");
		names.put("Disk C418-Stall","2263");
		names.put("Disk C418-Strad","2264");
		names.put("Disk C418-Ward","2265");
		names.put("Disk 11","2266");
		names.put("Disk C418-Wait","2267");
	}
	
	public static void loadNames(Map<String,String> m)
	{
		for(Entry<?, ?> i : m.entrySet())
		{
			KrimBlockName.names.put(i.getKey().toString().toLowerCase().trim(),(String)i.getValue());
		}
	}
	
	public static String searchName(String name)
	{
		name = name.toLowerCase();
		for (Map.Entry<String,String> entry : KrimBlockName.names.entrySet())
    	{
			if(entry.getKey().contains(name))
				return entry.getKey();
    	}
		
		return "null";
	}
	
	public static String getIdByName(String name)
	{
		name = name.trim().toLowerCase();
		if(KrimBlockName.names.get(name) != null)
			return KrimBlockName.names.get(name);
		else
			return "-1";
	}
	
	public static String getNameById(int type, int typeid)
	{
		String strg = "";
		strg += type;
		if(typeid > 0)
			strg += ":"+typeid;
		
		return getNameById(strg);
	}
	public static String getNameById(String id)
	{
		for (Map.Entry<String,String> entry : KrimBlockName.names.entrySet())
    	{
			if(entry.getValue().trim().equalsIgnoreCase(id.trim()))
				return entry.getKey();
    	}
		
		//Suche Master Block
		String[] subidTmp = id.split(":");
		String nid = "";
		if(subidTmp.length > 1)
		{
			nid = subidTmp[0];
			for (Map.Entry<String,String> entry : KrimBlockName.names.entrySet())
	    	{
				if(entry.getValue().trim().equalsIgnoreCase(nid.trim()))
					return entry.getKey();
	    	}
		}
		return ""+id;
	}
	
	public static String getNameByItemStack(ItemStack i)
	{
		String str = getIdByItemStack(i);
		return getNameById(str);
	}
	
	public static String getIdByItemStack(ItemStack i)
	{
		String str = "";
		str += i.getTypeId();
		if(i.getDurability() > 0 && i.getType().getMaxDurability() == 0)
			str += ":"+i.getDurability();
		return str;
	}
	
	public static ItemStack parseName(String nam)
	{
		ItemStack i = new ItemStack(Material.AIR);
		int n = 0;
		try
		{
			n = Integer.parseInt(nam);
			i.setTypeId(n);
		} catch(NumberFormatException e)
		{
			
		}
		
		if(n == 0)
		{
			try
			{
				if(nam.split(":").length > 1)
				{
					String[] sp = nam.split(":");
					//Block mit ID
					i.setTypeId(Integer.parseInt(sp[0]));
					i.setDurability((short) Integer.parseInt(sp[1]));
				} else
				{
					nam = KrimBlockName.getIdByName(nam);
					if(nam.split(":").length > 1)
					{
						String[] sp = nam.split(":");
						//Block mit ID
						i.setTypeId(Integer.parseInt(sp[0]));
						i.setDurability((short) Integer.parseInt(sp[1]));
					} else
					{
						i.setTypeId(Integer.parseInt(nam));
					}
				}
			} catch(Exception e)
			{
				return null;
			}
		}
		return i;
	}

	public static String getIdByBlock(Block i) 
	{
		String str = "";
		str += i.getTypeId();
		if(i.getData() > 0 && i.getType().getMaxDurability() == 0)
			str += ":"+i.getData();
		return str;
	}
	
	public static ItemStack getStackByBlock(Block i)
	{
		ItemStack s = new ItemStack(Material.AIR);
		s.setType(i.getType());
		if(i.getData() > 0 && i.getType().getMaxDurability() == 0)
			s.setDurability(i.getData());
		return s;
	}
}
