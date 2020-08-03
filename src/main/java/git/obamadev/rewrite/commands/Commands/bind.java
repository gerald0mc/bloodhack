package git.obamadev.rewrite.commands.Commands;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.Managers.MessageManager;
import git.obamadev.rewrite.Module.Module;
import git.obamadev.rewrite.commands.Command;
import org.lwjgl.input.Keyboard;

public class bind extends Command {
    public bind() {
        super("bind", new String[]{"b", "bind"});
    }
    @Override
    public void onCommand(String[] args) {
        if(args.length > 2){
            try{
                for(Module m: ObamaMod.moduleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        try {
                            m.settings.setSetting("keybind", String.valueOf(Keyboard.getKeyIndex(args[2].toUpperCase())));
                            MessageManager.sendMessagePrefix(m.getName() + " is now binded to " + args[2].toUpperCase() +"(" + Keyboard.getKeyIndex(args[2].toUpperCase() + "") + ")");
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
