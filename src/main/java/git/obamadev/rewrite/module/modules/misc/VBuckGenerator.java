package git.obamadev.rewrite.module.modules.misc;

import git.obamadev.rewrite.managers.Setting;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class VBuckGenerator extends Module {
    public VBuckGenerator() {
        super("VBuckGenerator", Category.MISC);
    }

    Setting ammount;
    Setting green;
    Setting blue;
    Setting space;

    @Override
    public void setup() {
        rSetting(ammount = new Setting("Ammount", this, 1, 0, 1000, true, "VBuckGenerated"));
        rSetting(green = new Setting("GreenText", this, true, "VBuckGreen"));
        rSetting(blue = new Setting("BlueText", this, true, "VBuckBlue"));
        rSetting(space = new Setting("Space", this, true, "VBuckSpace"));
    }

    public void onEnable() {
        if (green.getValBoolean()) {
            mc.player.sendChatMessage(">I just generated " + ammount.getValInt() + " V-Bucks, thanks to ObamaHack's V-Buck generator!");
            this.toggle();
        } else if (blue.getValBoolean()) {
            mc.player.sendChatMessage("`I just generated " + ammount.getValInt() + " V-Bucks, thanks to ObamaHack's V-Buck generator!");
            this.toggle();
        } else if (green.getValBoolean() && space.getValBoolean()) {
            mc.player.sendChatMessage("> I just generated " + ammount.getValInt() + " V-Bucks, thanks to ObamaHack's V-Buck generator!");
            this.toggle();
        } else if (blue.getValBoolean() && space.getValBoolean()) {
            mc.player.sendChatMessage("` I just generated " + ammount.getValInt() + " V-Bucks, thanks to ObamaHack's V-Buck generator!");
            this.toggle();
        } else {
            mc.player.sendChatMessage("I just generated " + ammount.getValInt() + " V-Bucks, thanks to ObamaHack's V-Buck generator!");
            this.toggle();
        }
    }
}
