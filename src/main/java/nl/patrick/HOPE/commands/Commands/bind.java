package nl.patrick.HOPE.commands.Commands;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Managers.MessageManager;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.commands.Command;
import org.lwjgl.input.Keyboard;

public class bind extends Command {
    public bind() {
        super("bind", new String[]{"t", "toggle"});
    }
    private boolean watching;
    private int cachekey;
    @SubscribeEvent
    public void inputEvent(InputEvent.KeyInputEvent event) {
        if(watching){
            cachekey = Keyboard.getEventKey();

        }
    }

    @Override
    public void onCommand(String[] args) {
        if(args.length > 1){
            try{
                for(Module m: Hope.moduleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        Hope.SETTINGS_MANAGER.updateSettings();
                        watching = true;
                        MessageManager.sendMessagePrefix("press the key u want to bind");
                        try {
                            m.settings.setSetting("keybind", String.valueOf(cachekey));
                            MessageManager.sendMessagePrefix(m.getName() + " is now binded to " + cachekey);
                        } catch (Exception e) {
                            MessageManager.sendMessagePrefix(m.getName() + "something went wrong");

                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
