package git.obamadev.rewrite;

import git.obamadev.rewrite.managers.ConfigManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import git.obamadev.rewrite.module.Module;
import git.obamadev.rewrite.module.ModuleManager;
import git.obamadev.rewrite.managers.SettingsManager;
import git.obamadev.rewrite.command.CommandManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * @since 1/8/2020
 * @author LittleDraily
 **/

@Mod(
        name = ObamaMod.name,
        modid = "obamahack",
        version = ObamaMod.version
)

public class ObamaMod {
    public static final String name = "ObamaHack";
    public static final String version = "b1";
    public static final String appid = "737985708945571861";
    public static String prefix = "=";
    public static ConfigManager configManager;
    public static ModuleManager moduleManager;
    public static SettingsManager settingsManager;

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
}
