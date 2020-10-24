package dev.lors.bloodhack;

import dev.lors.bloodhack.command.CommandManager;
import dev.lors.bloodhack.util.font.CFontRenderer;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.module.ModuleManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import dev.lors.bloodhack.managers.*;

/**
 * @since 1/8/2020
 * @author LittleDraily
 **/

@Mod(
        name = BloodHack.name,
        modid = "bloodhack",
        version = BloodHack.version
)

public class BloodHack {
    public static final String name = "Blood Hack";
    public static final String version = "b1";
    public static final String appid = "764915024422240296";
    public static String prefix = "*";
    public static ConfigManager configManager;
    public static ModuleManager moduleManager;
    public static SettingsManager settingsManager;
    public static final EventBus EVENT_BUS = new EventManager();
    protected Minecraft mc2;
    public static CFontRenderer fontRenderer;



    //pre init (phase 3)
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Display.setTitle(name + " " + version);
    }

    //init (phase 5)
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        CommandManager.init();
        MinecraftForge.EVENT_BUS.register(new CommandManager());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        for (Module m: moduleManager.getModules()) {
            if (Keyboard.isKeyDown(m.getKey())) {
                m.toggle();
            }

        }
    }
    @Mod.Instance
    private static BloodHack INSTANCE;

    public BloodHack() {
        INSTANCE = this;
    }

    public static BloodHack getInstance(){
        return INSTANCE;
    }

}

