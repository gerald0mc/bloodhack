package git.obamadev.rewrite.commands.Commands;

import net.minecraft.util.text.TextFormatting;
import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.Managers.MessageManager;
import git.obamadev.rewrite.Module.Module;
import git.obamadev.rewrite.commands.Command;

public class toggle extends Command {
    public toggle() {
        super("Toggle", new String[]{"t", "toggle"});
    }

    @Override
    public void onCommand(String[] args) {
        if(args.length > 1){
            try{
            for(Module m: ObamaMod.moduleManager.getModules()) {
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
