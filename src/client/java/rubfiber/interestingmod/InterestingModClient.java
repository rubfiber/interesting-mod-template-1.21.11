package rubfiber.interestingmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.KeyMapping;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import rubfiber.interestingmod.gui.guiScreen;

public class InterestingModClient implements ClientModInitializer {
	private static KeyMapping guiBinding;
	@Override
	public void onInitializeClient() {
		guiBinding = KeyBindingHelper.registerKeyBinding(            new KeyMapping(
						"key.interestingmod.open_gui",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_G,
						KeyMapping.Category.MISC
				));


		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (guiBinding.isDown()) {
				client.setScreen(new guiScreen(Component.empty()));
			}
		});
	}
}