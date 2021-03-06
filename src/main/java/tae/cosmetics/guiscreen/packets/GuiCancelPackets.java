package tae.cosmetics.guiscreen.packets;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.status.client.CPacketPing;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.status.server.SPacketServerInfo;
import net.minecraft.util.ResourceLocation;
import tae.cosmetics.exceptions.TAEModException;
import tae.cosmetics.gui.util.ScrollBar;
import tae.cosmetics.gui.util.packet.AbstractPacketModule;
import tae.cosmetics.guiscreen.GuiHome;
import tae.cosmetics.guiscreen.button.GuiOnOffButton;
import tae.cosmetics.mods.VisualizePacketsMod;
import tae.cosmetics.util.FileHelper;

public class GuiCancelPackets extends GuiScreen {

	private static final String fileName = "canceledpackets.txt";
	
	public static final ArrayList<Class<? extends Packet<?>>> client = new ArrayList<>();
	public static final ArrayList<Class<? extends Packet<?>>> server = new ArrayList<>();
	
    private static final ResourceLocation BACKGROUND = new ResourceLocation("taecosmetics","textures/gui/cancelpackets.png");
	
    private static ArrayList<Class<? extends Packet<?>>> canceledPackets = new ArrayList<>();
    
	private static GuiCancelPackets INSTANCE;
	
	private ArrayList<GuiOnOffButton> clientbuttons = new ArrayList<>();
	private ArrayList<GuiOnOffButton> serverbuttons = new ArrayList<>();
	private ScrollBar scroll1;
	private ScrollBar scroll2;
	
	private GuiButton back = new GuiButton(-1, 0, 0, 70, 20, "Back");
	
	static {
		//client.add(C00Handshake.class);
		client.add(CPacketAnimation.class);
		client.add(CPacketChatMessage.class);
		client.add(CPacketClickWindow.class);
		client.add(CPacketClientSettings.class);
		client.add(CPacketClientStatus.class);
		client.add(CPacketCloseWindow.class);
		client.add(CPacketConfirmTeleport.class);
		client.add(CPacketConfirmTransaction.class);
		client.add(CPacketCreativeInventoryAction.class);
		client.add(CPacketCustomPayload.class);
		client.add(CPacketEnchantItem.class);
		//client.add(CPacketEncryptionResponse.class);
		client.add(CPacketEntityAction.class);
		client.add(CPacketHeldItemChange.class);
		client.add(CPacketInput.class);
		client.add(CPacketKeepAlive.class);
		//client.add(CPacketLoginStart.class);
		client.add(CPacketPing.class);
		client.add(CPacketPlayer.class);
		client.add(CPacketPlayerAbilities.class);
		client.add(CPacketPlayerDigging.class);
		client.add(CPacketPlayerTryUseItem.class);
		client.add(CPacketPlayerTryUseItemOnBlock.class);
		client.add(CPacketRecipeInfo.class);
		//client.add(CPacketRecipePlacement.class);
		client.add(CPacketResourcePackStatus.class);
		client.add(CPacketSeenAdvancements.class);
		//client.add(CPacketServerQuery.class);
		client.add(CPacketSpectate.class);
		client.add(CPacketSteerBoat.class);
		client.add(CPacketTabComplete.class);
		client.add(CPacketUpdateSign.class);
		client.add(CPacketUseEntity.class);
		client.add(CPacketVehicleMove.class);

		server.add(SPacketAdvancementInfo.class);
		server.add(SPacketAnimation.class);
		server.add(SPacketBlockAction.class);
		server.add(SPacketBlockBreakAnim.class);
		server.add(SPacketBlockChange.class);
		server.add(SPacketCamera.class);
		server.add(SPacketChangeGameState.class);
		server.add(SPacketChat.class);
		server.add(SPacketChunkData.class);
		server.add(SPacketCloseWindow.class);
		server.add(SPacketCollectItem.class);
		server.add(SPacketCombatEvent.class);
		server.add(SPacketConfirmTransaction.class);
		server.add(SPacketCooldown.class);
		server.add(SPacketCustomPayload.class);
		server.add(SPacketCustomSound.class);
		server.add(SPacketDestroyEntities.class);
		//server.add(SPacketDisconnect.class);
		server.add(SPacketDisplayObjective.class);
		server.add(SPacketEffect.class);
		//server.add(SPacketEnableCompression.class);
		//server.add(SPacketEncryptionRequest.class);
		server.add(SPacketEntity.class);
		server.add(SPacketEntityAttach.class);
		server.add(SPacketEntityEffect.class);
		server.add(SPacketEntityEquipment.class);
		server.add(SPacketEntityHeadLook.class);
		server.add(SPacketEntityMetadata.class);
		server.add(SPacketEntityProperties.class);
		server.add(SPacketEntityStatus.class);
		server.add(SPacketEntityTeleport.class);
		server.add(SPacketEntityVelocity.class);
		server.add(SPacketExplosion.class);
		server.add(SPacketHeldItemChange.class);
		//server.add(SPacketJoinGame.class);
		server.add(SPacketKeepAlive.class);
		//server.add(SPacketLoginSuccess.class);
		server.add(SPacketMaps.class);
		server.add(SPacketMoveVehicle.class);
		server.add(SPacketMultiBlockChange.class);
		server.add(SPacketOpenWindow.class);
		server.add(SPacketParticles.class);
		server.add(SPacketPlayerAbilities.class);
		server.add(SPacketPlayerListHeaderFooter.class);
		server.add(SPacketPlayerListItem.class);
		server.add(SPacketPlayerPosLook.class);
		server.add(SPacketPong.class);
		server.add(SPacketRecipeBook.class);
		server.add(SPacketRemoveEntityEffect.class);
		server.add(SPacketResourcePackSend.class);
		server.add(SPacketRespawn.class);
		server.add(SPacketScoreboardObjective.class);
		server.add(SPacketSelectAdvancementsTab.class);
		server.add(SPacketServerDifficulty.class);
		server.add(SPacketServerInfo.class);
		server.add(SPacketSetExperience.class);
		server.add(SPacketSetPassengers.class);
		server.add(SPacketSetSlot.class);
		server.add(SPacketSignEditorOpen.class);
		server.add(SPacketSoundEffect.class);
		server.add(SPacketSpawnExperienceOrb.class);
		server.add(SPacketSpawnGlobalEntity.class);
		server.add(SPacketSpawnMob.class);
		server.add(SPacketSpawnObject.class);
		server.add(SPacketSpawnPainting.class);
		server.add(SPacketSpawnPlayer.class);
		server.add(SPacketSpawnPosition.class);
		server.add(SPacketStatistics.class);
		server.add(SPacketTabComplete.class);
		server.add(SPacketTeams.class);
		server.add(SPacketTimeUpdate.class);
		server.add(SPacketTitle.class);
		server.add(SPacketUnloadChunk.class);
		server.add(SPacketUpdateBossInfo.class);
		server.add(SPacketUpdateHealth.class);
		server.add(SPacketUpdateScore.class);
		server.add(SPacketUpdateTileEntity.class);
		server.add(SPacketUseBed.class);
		server.add(SPacketWindowItems.class);
		server.add(SPacketWindowProperty.class);
		server.add(SPacketWorldBorder.class);
		
		FileHelper.createFile(fileName);
		
		load();
		
	}
	
	public static void save() {
		StringBuilder builder = new StringBuilder();
		for(Class<? extends Packet<?>> clazz : canceledPackets) {
			builder.append(clazz.getName() + "\n");
		}
		FileHelper.overwriteFile(fileName, builder.toString());
	}
	
	@SuppressWarnings("unchecked")
	private static void load() {
		for(String s : FileHelper.readFile(fileName)) {
			try {
				canceledPackets.add((Class<? extends Packet<?>>) Class.forName(s));
			} catch (Exception e) {
				new TAEModException(GuiCancelPackets.class, "Cannot load canceled packets from config: " + e.getMessage()).post();
			}
		}
	}
	
	public static GuiCancelPackets instance() {
		if(INSTANCE == null) {
			INSTANCE = new GuiCancelPackets();
		}
		return INSTANCE;
	}
	
	private GuiCancelPackets() {
		int counter = -1;
		for(Class<? extends Packet<?>> clazz : client) {
			
			String info;
			
			AbstractPacketModule module = null;

			
			try{
				module = VisualizePacketsMod.getModuleFromPacket(clazz.newInstance(), 0);
			} catch (Exception e) {}
			
			
			if(module == null) {
				info = "";
			} else {
				info = module.getTip();
			}
			clientbuttons.add(new GuiOnOffButton(counter, 0, 0, 150, 20, clazz.getSimpleName().substring(7) + " ", !canceledPackets.contains(clazz), info, 1));
			
		}
		
		counter = -1;
		for(Class<? extends Packet<?>> clazz : server) {
			
			try {
				String info;
				AbstractPacketModule module = VisualizePacketsMod.getModuleFromPacket(clazz.newInstance(), 0);
				if(module == null) {
					info = "";
				} else {
					info = module.getTip();
				}
				serverbuttons.add(new GuiOnOffButton(counter, 0, 0, 150, 20, clazz.getSimpleName().substring(7) + " ", !canceledPackets.contains(clazz), info, 1));
			} catch (Exception e) {
				//new TAEModException(e.getClass(), e.getMessage()).post();
			}
	
		}
		
		scroll1 = new ScrollBar(0, 0, 110, 70);
		scroll2 = new ScrollBar(0, 0, 110, 70);
		
	}
	
	@Override
	public void initGui() {
		
		for(int i = 0; i < serverbuttons.size(); i++) {
			buttonList.add(serverbuttons.get(i));
		}
		
		for(int i = 0; i < clientbuttons.size(); i++) {
			buttonList.add(clientbuttons.get(i));
		}
		
		buttonList.add(back);

	}
	
	@Override
	public void onGuiClosed() {
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		if(button == back) {
			mc.addScheduledTask(() -> {
				mc.displayGuiScreen(new Gui2b2tcp(new GuiHome()));
			});
		}
		
		if(button instanceof GuiOnOffButton) {
			GuiOnOffButton button1 = (GuiOnOffButton) button;
			button1.changeStateOnClick();
			if(button1.getValue()) {
				
				Iterator<Class<? extends Packet<?>>> iter = canceledPackets.iterator();
				
				while(iter.hasNext()) {
					
					Class<?> clazz = iter.next();
					
					if(clientbuttons.contains(button1)) {	
					
						if(("CPacket" + button1.getText()).equals(clazz.getSimpleName() + " ")) {
							iter.remove();
							break;
						}
						
					} else {
						
						if(("SPacket" + button1.getText()).equals(clazz.getSimpleName() + " ")) {
							iter.remove();
							break;
						}
						
					}
					
				}
				
			} else {
					
				if(clientbuttons.contains(button1)) {
					for(Class<? extends Packet<?>> clazz : client) {				
					
						if(button1.getText().equals(clazz.getSimpleName().substring(7) + " ")) {
							canceledPackets.add(clazz);
							break;
						}
					
					}
				} else {
					
					for(Class<? extends Packet<?>> clazz : server) {				
						
						if(button1.getText().equals(clazz.getSimpleName().substring(7) + " ")) {
							canceledPackets.add(clazz);
							break;
						}
					
					}
					
				}
			}
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		int i = this.width / 2;
		int j = this.height / 2;
		
		updateButtonLocations(i, j);
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		
		mc.getTextureManager().bindTexture(BACKGROUND);
		this.drawTexturedModalRect(i - 190, j - 110, 0, 0, 200, 211);
		this.drawTexturedModalRect(i - 10, j - 110, 234, 0, 16, 211);
		this.drawTexturedModalRect(i + 10, j - 110, 70, 0, 186, 211);
		
		scroll1.draw(mouseX, mouseY);
		scroll2.draw(mouseX, mouseY);
				
		this.drawCenteredString(fontRenderer, "Client Packets", i - 100, j - 90, Color.WHITE.getRGB());
		this.drawCenteredString(fontRenderer, "Server Packets", i + 90, j - 90, Color.WHITE.getRGB());
		this.drawString(fontRenderer, "OFF means that the packets will be canceled when the keybind is toggled.", i - 175, j + 80, Color.WHITE.getRGB());
		
		for(GuiOnOffButton button : clientbuttons) {
			
			if(button.y < j - 70 || button.y > j + 50) {
				button.visible = false;
			} else {
				button.visible = true;
			}
			
		}
		
		
		for(GuiOnOffButton button : serverbuttons) {
			
			if(button.y < j - 70 || button.y > j + 50) {
				button.visible = false;
			} else {
				button.visible = true;
			}
			
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);

	}
		
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}	
	
	private void updateButtonLocations(int x, int y) {
		
		scroll1.x = x - 8;
		scroll1.y = y - 58;
				
		float scrollamt1 = scroll1.getScroll();
		
		int maxy1 = y - 70 + 22 * clientbuttons.size();
		
		for(int i = 0; i < clientbuttons.size(); i++) {
			clientbuttons.get(i).x = x - 175;
			clientbuttons.get(i).y = (int) (y - 70 + (22 * i) - Math.ceil(((scrollamt1 * (maxy1 - (y + 62))) / 22)) * 22);
		}
		
		scroll2.x = x + 176;
		scroll2.y = y - 58;
		
		float scrollamt2 = scroll2.getScroll();
		
		int maxy2 = y - 70 + 22 * serverbuttons.size();
		
		for(int i = 0; i < serverbuttons.size(); i++) {
			serverbuttons.get(i).x = x + 15;
			serverbuttons.get(i).y = (int) (y - 70 + (22 * i) - Math.ceil(((scrollamt2 * (maxy2 - (y + 62))) / 22)) * 22);
		}
		
	}
	
	public ArrayList<Class<? extends Packet<?>>> getCancelPackets() {
		return new ArrayList<Class<? extends Packet<?>>>(canceledPackets);
	}
	
	public void toggleButton(int type, int index) {
		if(type == 0) {
			
			clientbuttons.get(index).changeStateOnClick();
			
		} else if(type == 1) {
			
			serverbuttons.get(index).changeStateOnClick();
			
		}
	}
	
	@Override
	protected void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font)
    {
        if (!textLines.isEmpty()) {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int i = 0;

            for (String s : textLines)
            {
                int j = this.fontRenderer.getStringWidth(s);

                if (j > i)
                {
                    i = j;
                }
            }

            int l1 = x + 12;
            int i2 = y - 12;
            int k = 8;

            if (textLines.size() > 1)
            {
                k += 2 + (textLines.size() - 1) * 10;
            }

            if (l1 + i > this.width)
            {
                l1 -= 28 + i;
            }

            if (i2 + k + 6 > this.height)
            {
                i2 = this.height - k - 6;
            }

            this.zLevel = 300.0F;
            this.itemRender.zLevel = 300.0F;
            this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
            this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

            for (int k1 = 0; k1 < textLines.size(); ++k1)
            {
                String s1 = textLines.get(k1);
                this.fontRenderer.drawStringWithShadow(s1, (float)l1, (float)i2, -1);

                if (k1 == 0)
                {
                    i2 += 2;
                }

                i2 += 10;
            }

            this.zLevel = 0.0F;
            this.itemRender.zLevel = 0.0F;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }

	
}
