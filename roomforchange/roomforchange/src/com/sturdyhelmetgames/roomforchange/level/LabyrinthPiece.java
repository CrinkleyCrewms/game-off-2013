package com.sturdyhelmetgames.roomforchange.level;

import com.badlogic.gdx.math.Rectangle;
import com.sturdyhelmetgames.roomforchange.level.Level.LevelTile;
import com.sturdyhelmetgames.roomforchange.level.Level.LevelTileType;

public class LabyrinthPiece {

	public static final int WIDTH = 12;
	public static final int HEIGHT = 8;
	private final LevelTile[][] tiles;
	private final Rectangle bounds = new Rectangle();
	private final int orderNumber;
	public final boolean[] doorsOpen = new boolean[4];
	public final RoomTemplate roomTemplate;

	public LabyrinthPieceState state = LabyrinthPieceState.LIGHTS_OFF;

	public enum LabyrinthPieceState {
		LIGHTS_OFF, LIGHTS_ON, LIGHTS_DIMMED;
	}

	public LevelTile[][] getTiles() {
		return tiles;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public LabyrinthPiece(PieceTemplate pieceTemplate,
			RoomTemplate roomTemplate, int orderNumber, Level level) {
		tiles = new LevelTile[WIDTH][HEIGHT];
		this.orderNumber = orderNumber;
		this.roomTemplate = roomTemplate;
		for (int i = 0; i < pieceTemplate.doorsOpen.length; i++) {
			this.doorsOpen[i] = pieceTemplate.doorsOpen[i];
		}

		LevelTileType[][] pieceTemplates = pieceTemplate.getTileTypes();
		for (int x = 0; x < PieceTemplate.WIDTH; x++) {
			for (int y = 0; y < PieceTemplate.HEIGHT; y++) {
				tiles[x][y] = new LevelTile(this, pieceTemplates[x][y]);
			}
		}

		LevelTileType[][] roomTemplates = roomTemplate.getTileTypes();
		for (int x = 0; x < RoomTemplate.WIDTH; x++) {
			for (int y = 0; y < RoomTemplate.HEIGHT; y++) {
				tiles[x + 1][y + 1] = new LevelTile(this,
						roomTemplates[x][y] != null ? roomTemplates[x][y]
								: LevelTileType.GROUND);
			}
		}

	}

	public void updateBounds(float x, float y) {
		bounds.set(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
	}

	@Override
	public String toString() {
		return "LabyrinthPiece " + orderNumber;
	}

}
