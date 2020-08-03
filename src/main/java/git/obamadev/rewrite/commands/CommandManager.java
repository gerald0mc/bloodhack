package git.obamadev.rewrite.commands;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.commands.Commands.bind;
import git.obamadev.rewrite.commands.Commands.toggle;

import java.util.HashSet;

public class CommandManager {
    public static HashSet<Command> commands = new HashSet<>();

    public static void init(){
        commands.clear();
        commands.add(new toggle());
        commands.add(new bind());
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(ClientChatEvent event) {
        String[] args = event.getMessage().split(" ");
        if(event.getMessage().startsWith(ObamaMod.prefix)) {
            event.setCanceled(true);
            for (Command c: commands){
                if(args[0].equalsIgnoreCase(ObamaMod.prefix + c.getCommand())){
                    c.onCommand(args);
                }
            }
        }

    }
}
