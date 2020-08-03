package nl.patrick.HOPE.commands;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.commands.Command;
import nl.patrick.HOPE.commands.Commands.bind;
import nl.patrick.HOPE.commands.Commands.toggle;

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
        if(event.getMessage().startsWith(Hope.prefix)) {
            event.setCanceled(true);
            for (Command c: commands){
                if(args[0].equalsIgnoreCase(Hope.prefix + c.getCommand())){
                    c.onCommand(args);
                }
            }
        }

    }
}
