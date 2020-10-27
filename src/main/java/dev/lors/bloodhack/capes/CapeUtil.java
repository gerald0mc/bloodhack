package dev.lors.bloodhack.capes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CapeUtil {

    final static ArrayList<String> final_uuid_list = get_uuids();

    public static ArrayList<String> get_uuids() {
        try {
            URL url = new URL("https://pastebin.com/ZzBm5hT4");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final ArrayList<String> uuid_list = new ArrayList<>();

            String s;

            while ((s = reader.readLine()) != null) {
                uuid_list.add(s);
            }

            return uuid_list;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static boolean is_uuid_valid(UUID uuid) {
        for (String u : Objects.requireNonNull(final_uuid_list)) {
            if (u.equals(uuid.toString())) {
                return true;
            }
        }
        return false;
    }

    public static ResourceLocation getCape(){
        try {
            DynamicTexture texture = new DynamicTexture(ImageIO.read(new URL("https://i.imgur.com/W1T3gFG.png")));
            return Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("bloodhack/capes", texture);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
