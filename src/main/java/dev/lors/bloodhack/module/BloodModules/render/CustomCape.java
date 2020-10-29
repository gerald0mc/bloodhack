package dev.lors.bloodhack.module.BloodModules.render;

import dev.lors.bloodhack.capes.CapeUtil;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.util.Messages;
import net.minecraft.client.renderer.texture.DynamicTexture;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CustomCape extends Module {
    public CustomCape() {
        super("CustomCape", Category.RENDER);
    }

    Value<Cape> cape = new Value<Cape>("Cape", Cape.BloodCape);
    Value<String> url = new Value<String>("CapeURL", "", c->cape.value == Cape.CustomCape);

    enum Cape {
        BloodCape,
        BloodDrop,
        CustomCape
    }

    @Override
    public void onEnable() {
        if (cape.value == Cape.BloodDrop)
            if(CapeUtil.is_uuid_valid(mc.player.getUniqueID()))
                CapeUtil.startAnimationLoop();
            else
                Messages.sendMessagePrefix("You are not on allowed to have these capes!");
        else if (cape.value == Cape.BloodCape)
                if(CapeUtil.is_uuid_valid(mc.player.getUniqueID())) {
                    try {
                        CapeUtil.setCape(new DynamicTexture(ImageIO.read(new URL("https://i.imgur.com/EkcQlck.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Messages.sendMessagePrefix("You are not on allowed to have these capes!");
        else if(cape.value == Cape.CustomCape){
            try {
                CapeUtil.setCape(new DynamicTexture(ImageIO.read(new URL(new String(url.value.toString().getBytes(), StandardCharsets.UTF_8)))));
            } catch (IOException e) {
                Messages.sendMessagePrefix("An error happened trying to load the cape");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        CapeUtil.stopAnimationLoop();
        CapeUtil.capeTexture = null;
    }
}
