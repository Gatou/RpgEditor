package lib.editor.data;


public class DataTile extends DataBase{

	public boolean isWall;
	public int spriteId;
	
	public DataTile(int id, String name, int spriteId, boolean isWall){
		super(id, name);
		this.spriteId = spriteId;
		this.isWall = isWall;
	}
}
