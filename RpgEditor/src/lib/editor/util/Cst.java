/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

/**
 *
 * @author gaetan
 */
public class Cst {
    
    public static final String[] VALID_IMAGE_FORMAT = {"png", "jpg", "bmp"};
    public static final String[] VALID_SOUND_FORMAT = {"ogg", "mp3", "wma", "wav"};
    public static final String[] VALID_SCRIPT_FORMAT = {"rb"};
    //public static final String[] VALID_ASSET_FORMAT = {
       
    public static final int MAX_MAP_SIZE = 400;
    
    public static final int TILE_W = 32;
    public static final int TILE_H = 16;
    
    public static final int TILE_HW = TILE_W/2;
    public static final int TILE_HH = TILE_H/2;
}
