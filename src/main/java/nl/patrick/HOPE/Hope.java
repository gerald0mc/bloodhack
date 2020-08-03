package nl.patrick.HOPE;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import nl.patrick.HOPE.Managers.MessageManager;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.Module.ModuleManager;
import nl.patrick.HOPE.Managers.SettingsManager;
import nl.patrick.HOPE.commands.Command;
import nl.patrick.HOPE.commands.CommandManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import javax.swing.text.JTextComponent;

/**
 * @since 1/8/20202
 * @author LittleDraily
 **/
@Mod(
        name = "HOPE",
        modid = "hope",
        version = Hope.currentvers
)
public class Hope {
    public static final String name = "HOPE";
    public static final String currentvers = "0.1";
    public static final String discordid = "739219277361709116";
    public static String prefix = ".";

    public static ModuleManager moduleManager = new ModuleManager();
    public static final SettingsManager SETTINGS_MANAGER = new SettingsManager();



    //pre init (phase 3)
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        Display.setTitle(name + " " + currentvers);

    }

    //init (phase 5)
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        moduleManager.loadModules();
        CommandManager.init();
        MinecraftForge.EVENT_BUS.register(new CommandManager());
        MinecraftForge.EVENT_BUS.register(this);


    }
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        for (Module m: moduleManager.getModules()) {
            if(Keyboard.isKeyDown(m.getKey())){
                m.toggle();

            }
            
        }
    }
}
