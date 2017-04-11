package net.finksu.First2DGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile stoneTile = new StoneTile(1);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile airTile = new AirTile(3);
    
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id) {
        
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
        
    }
    
    public void tick() {
        
        
        
    }
    
    public void render(Graphics g, int x, int y) {
        
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
        
    }
    
    public int getId() {
        
        return id;
        
    }
    
    public boolean isSolid() {
        
        return false;
        
    }
    
}
