package git.obamadev.rewrite;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import git.obamadev.rewrite.Module.Module;
import git.obamadev.rewrite.Module.ModuleManager;
import git.obamadev.rewrite.Managers.SettingsManager;
import git.obamadev.rewrite.commands.CommandManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * @since 1/8/20202
 * @author LittleDraily
 **/
@Mod(
        name = ObamaMod.name,
        modid = "obamahack",
        version = ObamaMod.currentvers
)
public class ObamaMod {
    public static final String name = "ObamaHack";
    public static final String currentvers = "0.1";
    public static final String discordid = "710085123814588416";
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
