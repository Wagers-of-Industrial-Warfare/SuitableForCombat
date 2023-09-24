package rbasamoyai.suitableforcombat.content.attaching;

import java.util.Set;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import rbasamoyai.suitableforcombat.SFCModClient;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.CustomHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.items.ornaments.OrnamentType;
import rbasamoyai.suitableforcombat.content.items.ornaments.SupportsOrnaments;
import rbasamoyai.suitableforcombat.network.SFCNetwork;
import rbasamoyai.suitableforcombat.network.ServerboundSyncItemPacket;

public class UniformDecoratingScreen extends AbstractContainerScreen<UniformDecoratingMenu> {

	private static final int DISPLAY_WINDOW_HEIGHT = 180;

	private float yaw = -210;
	private float pitch = -30;
	private float zoom = 40;
	private Item previousItem = Items.AIR;

	public UniformDecoratingScreen(UniformDecoratingMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);

		this.imageWidth = 176;
		this.imageHeight = 280;
		this.inventoryLabelY = this.imageHeight - 94;
		this.getMenu().addCallback(this::onItemChanged);
	}

	@Override
	protected void init() {
		super.init();
	}

	public void onItemChanged(ItemStack newStack) {
		if (this.previousItem == newStack.getItem()) return;
		this.previousItem = newStack.getItem();
		this.yaw = -210;
		this.pitch = -30;
		this.zoom = 40;
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, SuitableForCombatMod.resource("textures/gui/attachment_slots.png"));

		this.fillGradient(poseStack, this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + DISPLAY_WINDOW_HEIGHT, 0x3fffffff, 0x3fffffff);
		this.blit(poseStack, this.leftPos, this.topPos + DISPLAY_WINDOW_HEIGHT, 0, 0, this.imageWidth, 100);
		this.blit(poseStack, this.leftPos + 7, this.topPos + 159, 176, 0, 18, 18);

		ItemStack mainItem = this.menu.getMainItem();
		this.renderArmorPiece(poseStack, mainItem, partialTick, mouseX, mouseY);

		if (!mainItem.isEmpty() && mainItem.getItem() instanceof SupportsOrnaments ornamented) {
			Set<? extends OrnamentType> types = ornamented.ornamentTypes();

			for (OrnamentType type : types) {
				RenderSystem.setShader(GameRenderer::getPositionColorShader);
				ItemStack orn = ornamented.getOrnament(mainItem, type);
				int i = this.leftPos + type.slotX();
				int j = this.topPos + type.slotY();

				if (!orn.isEmpty()) {
					this.setBlitOffset(100);
					this.itemRenderer.blitOffset = 100.0F;

					RenderSystem.enableDepthTest();
					this.itemRenderer.renderAndDecorateItem(this.minecraft.player, orn, i, j, i + j * this.imageWidth);
					this.itemRenderer.renderGuiItemDecorations(this.font, orn, i, j, null);

					this.itemRenderer.blitOffset = 0.0F;
					this.setBlitOffset(0);
				}

				if (this.isHovering(type.slotX(), type.slotY(), 16, 16, mouseX, mouseY)) {
					renderSlotHighlight(poseStack, i, j, this.getBlitOffset());
				}
			}
		}
	}

	private void renderArmorPiece(PoseStack poseStack, ItemStack itemStack, float partialTick, double mouseX, double mouseY) {
		if (this.minecraft == null || itemStack.isEmpty() || !(itemStack.getItem() instanceof ArmorItem armor)) return;
		EquipmentSlot slot = armor.getSlot();
		MultiBufferSource.BufferSource buffers = this.minecraft.renderBuffers().bufferSource();

		poseStack.pushPose();
		if (itemStack.getItem() instanceof SupportsOrnaments ornamented) {
			Set<? extends OrnamentType> types = ornamented.ornamentTypes();
			for (OrnamentType type : types) {

			}

			for (OrnamentType type : types) {
				this.blit(poseStack, this.leftPos + type.slotX() - 1, this.topPos + type.slotY() - 1, 176, 0, 18, 18);
			}

//			poseStack.mulPose(Vector3f.ZP.rotationDegrees(45));
//
//			PoseStack.Pose last = poseStack.last();
//			Matrix4f pose = last.pose();
//			Matrix3f normal = last.normal();
//
//			float x = 0;
//
//			float length = 50;
//			float width = 1;
//			RenderSystem.disableBlend();
//			RenderSystem.defaultBlendFunc();
//			RenderSystem.setShader(GameRenderer::getPositionColorShader);
//			Tesselator tess = Tesselator.getInstance();
//			BufferBuilder builder = tess.getBuilder();
//			builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
//
//			builder.vertex(pose, 0, -width, 0).color(255, 255, 255, 255).endVertex();
//			builder.vertex(pose, 0, width, 0).color(255, 255, 255, 255).endVertex();
//			builder.vertex(pose, length, width, 0).color(255, 255, 255, 255).endVertex();
//			builder.vertex(pose, length, -width, 0).color(255, 255, 255, 255).endVertex();
//
//			tess.end();
			RenderSystem.disableBlend();
			RenderSystem.disableDepthTest();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
		} else {

		}
		poseStack.popPose();

		float mx = this.leftPos + this.imageWidth * 0.5f;
		float my = this.topPos + DISPLAY_WINDOW_HEIGHT * 0.5f;

		poseStack.pushPose();
		poseStack.translate(mx, my, 200);
		switch (slot) {
			case FEET -> {
			}
			case LEGS -> {

			}
			case CHEST -> {

			}
			case HEAD -> {

			}
			default -> {
			}
		}

		CustomHumanoidArmorRenderer customRender = SFCModClient.getArmorRenderer(itemStack.getItem());
		if (customRender != null) {
			poseStack.pushPose();

			float f = this.zoom;

			poseStack.mulPose(Vector3f.XP.rotationDegrees(this.pitch));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(this.yaw));
			poseStack.translate(0, f * 0.5, 0);
			poseStack.scale(f, f, f);

			AbstractClientPlayer player = this.minecraft.player;
			PlayerRenderer playerRenderer = (PlayerRenderer) this.minecraft.getEntityRenderDispatcher().getRenderer(player);
			PlayerModel<?> model = playerRenderer.getModel();
			customRender.render(itemStack, poseStack, buffers, player, slot, LightTexture.FULL_BRIGHT, model);
			buffers.endBatch();
			poseStack.popPose();
		} else {

		}
		poseStack.popPose();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.drawShadow(poseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, -1);
		this.font.draw(poseStack, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY, 4210752);
	}

	@Override
	protected void renderTooltip(PoseStack poseStack, int x, int y) {
		super.renderTooltip(poseStack, x, y);
		ItemStack mainItem = this.menu.getMainItem();
		if (!mainItem.isEmpty() && mainItem.getItem() instanceof SupportsOrnaments ornamented) {
			Set<? extends OrnamentType> types = ornamented.ornamentTypes();
			for (OrnamentType type : types) {
				if (!this.isHovering(type.slotX(), type.slotY(), 16, 16, x, y)) continue;
				ItemStack orn = ornamented.getOrnament(mainItem, type);
				if (orn.isEmpty()) {
					this.renderTooltip(poseStack, new TranslatableComponent(type.getDescriptionId()), x, y);
				} else {
					this.renderTooltip(poseStack, orn, x, y);
				}
			}
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTick);
		this.renderTooltip(poseStack, mouseX, mouseY);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
		if (super.mouseScrolled(mouseX, mouseY, delta)) return true;
		if (this.isHovering(0, 0, this.imageWidth, DISPLAY_WINDOW_HEIGHT, mouseX, mouseY)) {
			this.zoom = Mth.clamp(this.zoom + (float) delta, 40, 80);
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (button == 0 && this.getMenu().getCarried().isEmpty()
			&& this.isHovering(0, 0, this.imageWidth, DISPLAY_WINDOW_HEIGHT, mouseX, mouseY)) {
			this.yaw = Mth.wrapDegrees(this.yaw + (float) dragX);
			this.pitch = Mth.clamp(this.pitch - (float) dragY, -90, 90);
			return true;
		}
		return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		ItemStack mainItem = this.menu.getMainItem();
		ItemStack carried = this.menu.getCarried();
		if (!mainItem.isEmpty() && mainItem.getItem() instanceof SupportsOrnaments ornamented) {
			Set<? extends OrnamentType> types = ornamented.ornamentTypes();
			for (OrnamentType type : types) {
				if (!this.isHovering(type.slotX(), type.slotY(), 16, 16, mouseX, mouseY)) continue;

				ItemStack orn = ornamented.getOrnament(mainItem, type);
				boolean modified = false;
				if (carried.isEmpty() && !orn.isEmpty()) {
					this.menu.setCarried(orn);
					ornamented.setOrnament(mainItem, type, ItemStack.EMPTY);
					modified = true;
				} else if (!carried.isEmpty() && orn.isEmpty() && ornamented.supportsOrnament(mainItem, type, carried)) {
					ornamented.setOrnament(mainItem, type, carried.split(1));
					modified = true;
				} else if (!carried.isEmpty() && !orn.isEmpty() && ItemStack.isSameItemSameTags(carried, orn)) {
					int pick = Mth.clamp(orn.getCount(), 0, carried.getMaxStackSize() - carried.getCount());
					if (pick > 0) {
						carried.grow(pick);
						orn.shrink(pick);
						ornamented.setOrnament(mainItem, type, orn.isEmpty() ? ItemStack.EMPTY : orn);
						modified = true;
					}
				} else if (!carried.isEmpty() && !orn.isEmpty() && !ItemStack.isSameItemSameTags(carried, orn) && carried.getCount() == 1) {
					this.menu.setCarried(orn);
					ornamented.setOrnament(mainItem, type, carried);
					modified = true;
				}
				if (modified) {
					SFCNetwork.sendToServer(new ServerboundSyncItemPacket(mainItem, this.menu.getCarried()));
					return true;
				}
			}
		}
		return super.mouseClicked(mouseX, mouseY, button);
	}
}
