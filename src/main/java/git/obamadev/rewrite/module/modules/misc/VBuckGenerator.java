package git.obamadev.rewrite.module.modules.misc;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.managers.Setting;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class VBuckGenerator extends Module {
    Setting ammount;
    Setting green;
    Setting blue;
    Setting space;
    public VBuckGenerator() {
        super("VBuckGenerator", Category.MISC);
    }

    @Override
    public void setup() {
        ObamaMod.settingsManager.rSetting(ammount = new Setting("Ammount", this, 1, 0, 1000, true, "VBuckGenerated"));
        ObamaMod.settingsManager.rSetting(green = new Setting("Green Text", this, false, "VBuckGreen"));
        ObamaMod.settingsManager.rSetting(blue = new Setting("Blue Text", this, false, "VBuckBlue"));
        ObamaMod.settingsManager.rSetting(space = new Setting("Space", this, false, "VBuckSpace"));
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
