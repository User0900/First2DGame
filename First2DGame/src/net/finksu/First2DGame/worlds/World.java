package net.finksu.First2DGame.worlds;

import java.awt.Graphics;
import java.util.Random;
import net.finksu.First2DGame.Handler;
import net.finksu.First2DGame.exceptions.TileNotFoundException;
import net.finksu.First2DGame.exceptions.TileNumberException;
import net.finksu.First2DGame.tiles.Tile;
import net.finksu.First2DGame.utils.Utils;

public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int tiles1, tiles2;
    private int[][] tiles;
    
    public World(Handler handler, String path, int tiles1, int tiles2) {
        
        try{
        this.tiles1 = tiles1;
        this.tiles2 = tiles2;
        this.handler = handler;
        if(path.equalsIgnoreCase("random")) {
            
            this.width = tiles1;
            this.height = tiles2;
            generateWorld();
            
        }else{
            
            loadWorld(path);
            
        }
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    public void tick() {
        
        
        
    }
    
    public void render(Graphics g) {
        
        try{
        for (int y = 0; y < height; y++) {
            
            for (int x = 0; x < width; x++) {
                
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                
            }
            
        }
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    public Tile getTile(int x, int y) {
        
        try{
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        
        Tile t = Tile.tiles[tiles[x][y]];
        
        if(t == null) {
            
            throw new TileNotFoundException("Tile not found.");
            
        }else
            return t;
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
        return null;
        
    }
    
    private void loadWorld(String path) {
        
        try{
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            
            for (int x = 0; x < width; x++) {
                
                try{
                    
                    tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                    
                }catch(ArrayIndexOutOfBoundsException e){
                    
                    String error = e.toString();
                    String errors[] = error.split(" ");
                    int errror = Integer.parseInt(errors[1]);
                    throw new TileNumberException("Needed amount of tiles is " + (width * height) + ", but the map you're loading has only " + (errror - 4)); 
                    
                }
                
            }
            
        }
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    private void generateWorld() {
        
        try{
        System.out.println("Generating world...");
        
        Random rn = new Random();
        
        tiles = new int[this.tiles1][this.tiles2];
        
        for (int y = 0; y < tiles1; y++) {
            
            for (int x = 0; x < tiles2; x++) {
                
                int tile = rn.nextInt(3);
                
                tiles[x][y] = tile;
                  
                
            }
            
        }
        
        System.out.println("World generated successfully!");
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    public int getWidth() {
        
        return width;
        
    }
    
    public int getHeight() {
        
        return height;
        
    }
    
}
