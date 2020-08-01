package nl.patrick.HOPE.commands.Commands;

import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.commands.Command;

public class ToggleCommand extends Command {
    public ToggleCommand(){
        super("Module toggler", new String[]{"toggle"}, "Toggles the specified module.");
    }
    @Override
    public void onCommand(String[] args) {
        if(args.length > 1 && args[1].equals(" ")){
            Boolean exists = false;
            for(Module module: Hope.moduleManager.getModules()){
                if(module.getName().equals(args[1])){
                    module.toggle();
                    exists = true;
                }
            }
        }
    }
}
