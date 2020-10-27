package dev.lors.bloodhack;

import dev.lors.bloodhack.clickgui.ClickGUI;
import dev.lors.bloodhack.command.CommandManager;
import dev.lors.bloodhack.managers.ConfigManager;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.module.ModuleManager;
import dev.lors.bloodhack.util.font.CFontRenderer;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * @author LittleDraily
 * @since 1/8/2020
 **/

@Mod(
        name = BloodHack.name,
        modid = "bloodhack",
        version = BloodHack.version
)

public class BloodHack {
    public static final String name = "Blood Hack";
    public static final String version = "b1.1";
    public static final String appid = "764915024422240296";
    public static final EventBus EVENT_BUS = new EventManager();
    public static String prefix = "*";
    public static ConfigManager configManager;
    public static ModuleManager moduleManager;
    public static ClickGUI gui;
    public static CFontRenderer fontRenderer;
    @Mod.Instance
    private static BloodHack INSTANCE;
    protected Minecraft mc2;

    public BloodHack() {
        INSTANCE = this;
    }

    public static BloodHack getInstance() {
        return INSTANCE;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle(name + " " + version);
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) throws IllegalAccessException {
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        gui = new ClickGUI();
        CommandManager.init();
        MinecraftForge.EVENT_BUS.register(new CommandManager());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        for (Module m : moduleManager.getModules()) {
            if (Keyboard.isKeyDown(m.getKey())) {
                m.toggle();
            }

        }
    }

}

