package com.bpermissions.minimap;

import java.nio.ByteBuffer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.spoutcraft.spoutcraftapi.ChatColor;
import org.spoutcraft.spoutcraftapi.addon.java.JavaAddon;
import org.spoutcraft.spoutcraftapi.gui.GenericWidget;
import org.spoutcraft.spoutcraftapi.gui.WidgetType;

public class MiniMapWidget extends GenericWidget {

	private final JavaAddon parent;
	public final MiniMap miniMap;
	private ByteBuffer buff = null;
	
	public static double tx = 0;
	public static double ty = 0;
	public static int scale = 0;
	
	public MiniMapWidget(MiniMapAddon parent) {
		this.parent = parent;
		miniMap = new MiniMap(parent);
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Texture;
	}
	
	boolean keyDown = false;
	@Override
	/**
	 * This is where all the fun stuff happens, we check to see
	 * if a new texture is available (and if it is we update it using
	 * TextureUtils.updateTexture(buff);
	 * 
	 * This can only be done in render();
	 * 
	 * We then draw a quad :)
	 */
	public void render() {
		Keyboard.poll();
		if(Keyboard.isKeyDown(Keyboard.KEY_M)) {
			if(keyDown == false) {
				if(scale == 0) {
				scale = 16;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 1");
				}
				else if(scale == 16) {
				scale = 32;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 2");
				}
				else if(scale == 32) {
				scale = 48;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 3");
				}
				else if(scale == 48) {
				scale = 64;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 4");
				}
				else if(scale == 64) {
				scale = 80;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 5");
				}
				else if(scale == 80) {
				scale = 96;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 6");
				}
				else if(scale == 96) {
				scale = 128;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 7");
				}
				else if(scale == 128) {
				scale = 0;
				getParent().getClient().getActivePlayer().sendMessage(ChatColor.BLUE+"** ZOOM LEVEL 0");
				}
			keyDown = true;
			}
		} else {
			keyDown = false;
		}
				tx = this.getScreen().getWidth()-100;
		// Global translation
		GL11.glTranslated(tx, ty, 0);
		
		// Code to draw the face of the player
		//drawPlayer();
		// Code moved to textureUtils
		buff = TextureUtils.render(miniMap, buff);
		
		// Global untranslation
		GL11.glTranslated(-tx, -ty, 0);
	}
		
	@Override
	public void onTick() {
		// Nothing
	}

	public JavaAddon getParent() {
		return parent;
	}

}
