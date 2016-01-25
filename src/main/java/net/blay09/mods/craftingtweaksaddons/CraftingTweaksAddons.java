package net.blay09.mods.craftingtweaksaddons;

import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.SimpleTweakProvider;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CraftingTweaksAddons.MOD_ID, name = "Crafting Tweaks Addons", acceptableRemoteVersions = "*")
public class CraftingTweaksAddons {

    public static final Logger logger = LogManager.getLogger();
    public static final String MOD_ID = "craftingtweaksaddons";

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ezstorage();
        thaumcraft();
        progressiveautomation();

        registerProvider("uk.binarycraft.storagesilo.blocks.craftingsilo.ContainerCraftingSilo", new CraftingSiloProvider());
    }

    public static void ezstorage() {
        SimpleTweakProvider provider = registerSimpleProvider("ezstorage", "com.zerofall.ezstorage.container.ContainerStorageCoreCrafting");
        if(provider != null) {
            provider.setGrid(90, 9);
            provider.setTweakRotate(true, true, 0, 0);
            provider.setTweakBalance(true, true, 0, 0);
            provider.setTweakClear(true, true, 0, 0);
            provider.setAlignToGrid(EnumFacing.WEST);
        }
    }

    public static void thaumcraft() {
        SimpleTweakProvider provider = registerSimpleProvider("Thaumcraft", "thaumcraft.common.container.ContainerArcaneWorkbench");
        if(provider != null) {
            provider.setGrid(2, 9);
            provider.setTweakRotate(true, true, -12, 46);
            provider.setTweakBalance(true, true, -12, 64);
            provider.setTweakClear(true, true, -12, 82);
        }
    }

    public static void progressiveautomation() {
        SimpleTweakProvider provider = registerSimpleProvider("progressiveautomation", "com.vanhal.progressiveautomation.gui.container.ContainerCrafter");
        if(provider != null) {
            provider.setPhantomItems(true);
            provider.setTweakRotate(true, true, 0, 0);
            provider.setTweakBalance(false, false, 0, 0);
            provider.setTweakClear(true, true, 0, 0);
            provider.setAlignToGrid(EnumFacing.WEST);
        }
    }

    @SuppressWarnings("unchecked")
    private static SimpleTweakProvider registerSimpleProvider(String modid, String className) {
        try {
            return CraftingTweaksAPI.registerSimpleProvider(modid, (Class<? extends Container>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            logger.error("Could not register Crafting Tweaks addon for {} - internal names have changed.", modid);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static void registerProvider(String className, TweakProvider provider) {
        try {
            CraftingTweaksAPI.registerProvider((Class<? extends Container>) Class.forName(className), provider);
        } catch (ClassNotFoundException e) {
            logger.error("Could not register Crafting Tweaks addon for {} - internal names have changed.", provider.getModId());
        }
    }
}
