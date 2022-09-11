package net.LiorNadav.rpgmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_RPGMOD = "key.category.rpgmod";
    public static final String KEY_GUIDE_BOOK = "key.rpgmod.guide_book";

    public static final KeyMapping GUIDE_BOOK = new KeyMapping(KEY_GUIDE_BOOK, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_SUBTRACT, KEY_CATEGORY_RPGMOD);
}
