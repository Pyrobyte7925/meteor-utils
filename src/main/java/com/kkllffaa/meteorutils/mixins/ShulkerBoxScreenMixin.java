package com.kkllffaa.meteorutils.mixins;

import com.kkllffaa.meteorutils.modules.ShulkerDupe;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.utils.render.ContainerButtonWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxScreenMixin extends HandledScreen<ShulkerBoxScreenHandler> {
	public ShulkerBoxScreenMixin(ShulkerBoxScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}
	
	private boolean f = false;
	
	@Inject(method = "render", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		if (!f) {
			f = true;
			System.out.println("debil");
			if (Modules.get().isActive(ShulkerDupe.class)) {
				addDrawableChild(new ContainerButtonWidget(
						x - 62,
						y + 3,
						60,
						12,
						new LiteralText("DUPE ONE"),
						button -> Modules.get().get(ShulkerDupe.class).shoulddupe = ShulkerDupe.ShouldDupe.ONE
				));
				addDrawableChild(new ContainerButtonWidget(
						x - 62,
						y + 20,
						60,
						12,
						new LiteralText("DUPE ALL"),
						button -> Modules.get().get(ShulkerDupe.class).shoulddupe = ShulkerDupe.ShouldDupe.ALL
				));
			}
		}
	}

}
