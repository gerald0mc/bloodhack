package nl.patrick.HOPE.commands.Commands;

import net.minecraft.util.text.TextFormatting;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Managers.MessageManager;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.commands.Command;

public class toggle extends Command {
    public toggle() {
        super("Toggle", new String[]{"t", "toggle"});
    }

    @Override
    public void onCommand(String[] args) {
        if(args.length > 1){
            try{
            for(Module m: Hope.moduleManager.getModules()) {
                if (m.getName().equalsIgnoreCase(args[1])) {
                    m.toggle();
                    if (m.isToggled()) {
                        MessageManager.sendMessagePrefix(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.GREEN + "ON");
                    } else {
                        MessageManager.sendMessagePrefix(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.RED + "OFF");
                    }
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
