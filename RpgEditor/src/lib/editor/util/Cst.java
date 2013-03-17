/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaetan
 */
public class Cst {
    
    public static final List<String> VALID_IMAGE_FORMAT = new ArrayList<String>() {{
        add("png"); add("jpg"); add("bmp");
    }};
    public static final List<String> VALID_SOUND_FORMAT = new ArrayList<String>() {{
        add("ogg"); add("mp3"); add("wav");
    }};
    public static final List<String> VALID_SCRIPT_FORMAT = new ArrayList<String>() {{
        add("rb");
    }}; 
    
    public static final List<String> ALL_ASSET_FORMAT = new ArrayList<String>() {{
        for(String format : VALID_IMAGE_FORMAT){
            add(format);
        }
        for(String format : VALID_SOUND_FORMAT){
            add(format);
        }
        for(String format : VALID_SCRIPT_FORMAT){
            add(format);
        }
    }}; 
       
    public static final int MAX_MAP_SIZE = 400;
    
    public static final int TILE_W = 32;
    public static final int TILE_H = 16;
    
    public static final int TILE_HW = TILE_W/2;
    public static final int TILE_HH = TILE_H/2;
}
