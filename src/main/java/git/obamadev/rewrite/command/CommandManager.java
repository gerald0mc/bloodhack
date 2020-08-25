package git.obamadev.rewrite.command;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.command.commands.Bind;
import git.obamadev.rewrite.command.commands.Toggle;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;

public class CommandManager {
    public static HashSet<Command> commands = new HashSet<>();

    public static void init() {
        commands.clear();
        commands.add(new Toggle());
        commands.add(new Bind());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(ClientChatEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(ObamaMod.prefix)) {
            event.setCanceled(true);
            for (Command c : commands) {
                if (args[0].equalsIgnoreCase(ObamaMod.prefix + c.getCommand())) {
                    c.onCommand(args);
                }
            }
        }
    }
}
