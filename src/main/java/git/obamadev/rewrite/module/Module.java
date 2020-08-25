package git.obamadev.rewrite.module;

import git.obamadev.rewrite.managers.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import static git.obamadev.rewrite.ObamaMod.settingsManager;

public class Module {
    protected Minecraft mc = Minecraft.getMinecraft();

    private String name, displayName;
    private Category category;
    private boolean toggled;
    private Integer key;

    public Module(String name , Category category) {
        this.name = name;
        this.category = category;
        toggled = false;
        key = Keyboard.KEY_NONE;
        setup();
    }

    public void registerSettings() {
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
        if (this.isToggled()) {
            onUpdate();
        }
    }

    public void onUpdate() {}

    public void selfSettings() {}

    public void rSetting(Setting setting) {
        settingsManager.rSetting(setting);
    }

    public void onToggle() {}

    public void toggle() {
        toggled = !toggled;
        onToggle();
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public Integer getKey(){
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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