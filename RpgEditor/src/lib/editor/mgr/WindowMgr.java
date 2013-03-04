package lib.editor.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;


public class WindowMgr {
    
	public static Stage stage;
	public static Skin skin;
	private static Label fpsLabel;	
	
	public static void init(){
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		//stage.setCamera(Game.camera);
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("assets/gdx/uiskin.json"));
		
		

		
		//table.setFillParent(true);
		
		//table.setPosition(-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-10);
		//stage.addActor(table);
		

		//table.setOrigin(50, 50);
		
		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		stage.addActor(fpsLabel);
		fpsLabel.setAlignment(Align.left | Align.top);

		

	}

	public static void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void update(){
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
                
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		//Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public static void dispose() {
		stage.dispose();
	}
	
}
