package git.obamadev.rewrite.module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import git.obamadev.rewrite.managers.Settings;
import org.lwjgl.input.Keyboard;

public class Module {
    protected Minecraft mc = Minecraft.getMinecraft();

    private String name, displayName;
    private Category category;
    private boolean toggled;
    public Settings settings = new Settings();

    public Module(String name , Category category){
        this.name = name;
        this.category = category;
        toggled = false;
    }

    public void registerSettings() {
        settings.addSetting("enabled", false);
        settings.addSetting("keybind", String.valueOf(Keyboard.KEY_NONE));
        selfSettings();
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void gameTickEvent(TickEvent event) {
        if(this.isToggled()) {
            onUpdate();
        }
    }
    public void setSettings(Settings newSettings) {
        settings = newSettings;
    }
    public void onUpdate() {}

    public void selfSettings() {
    }

    public void onToggle() {}
    public void toggle() {
        toggled = !toggled;
        onToggle();
        if (toggled) {
            onEnable();
            settings.setSetting("enabled", true);

        } else {
            onDisable();
            settings.setSetting("enabled", false);
        }
    }
    public Integer getKey(){
        return  Integer.parseInt(settings.getSetting("keybind").toString());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public boolean isToggled() {
        return toggled;
    }
    public String getDisplayName() {
        return displayName == null ? name : displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setup() {}
}