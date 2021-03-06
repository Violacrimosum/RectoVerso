package com.rectoverso.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rectoverso.RVGame;
import com.rectoverso.controllers.LevelManager;
import com.rectoverso.controllers.MusicManager.RVMusic;
import com.rectoverso.model.Level;
import com.rectoverso.utils.ButtonListener;
import com.rectoverso.utils.ButtonListener.DefaultInputListener;


public class MenuScreen extends AbstractScreen {

	private final int BUTTONW = 150; 
	private final int BUTTONH = 30; 

	private SpriteBatch batch;
	private TextureRegion menuImage;

	private Label title = new Label("RectoVerso "+RVGame.VER, this.getSkin());
	private Label subtitle = new Label("Dev mode : "+RVGame.DEV_MODE, this.getSkin());

	public MenuScreen(RVGame game) {
		super(game);
	}

	@Override
	public void show()
	{
		super.show();
		// start playing the menu music
		if(RVGame.DEV_MODE)
			game.getMusicManager().play(RVMusic.MENU );
		// retrieve the default table actor
		/*TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images-atlases/pages.atlas"));
        menuImage = atlas.findRegion("titleScreenImage");*/
		batch = this.getBatch();

		Table table = super.getTable();
		table.right().padRight(30).top().padTop(50);
		table.add(title).spaceBottom(0);
		table.row();
		table.add(subtitle).spaceBottom(50).right();
		table.row();
		
		// register the button "new game"
		TextButton startGameButton = new TextButton("Aventure", this.getSkin());
		startGameButton.addListener(new ButtonListener.ChangeScreenListener(game,game.getLevelSelectScreen()));
		
		table.add(startGameButton).size(this.BUTTONW, this.BUTTONH).uniform().spaceBottom(10).right();
		table.row();

		// register the button "options"
		TextButton optionsButton = new TextButton("Options", getSkin());
		optionsButton.addListener(new ButtonListener.ChangeScreenListener(game,game.getOptionsScreen()) );
		
		table.add(optionsButton).size(this.BUTTONW, this.BUTTONH).uniform().spaceBottom(10).right();
		table.row();

		// register the button "editor"
		TextButton editorButton = new TextButton("Editeur", getSkin() );
		editorButton.addListener(new ButtonListener.ChangeScreenListener(game,game.getLevelEditorScreen())); 
		
		table.add(editorButton).size(this.BUTTONW, this.BUTTONH).uniform().spaceBottom(10).right();
		table.row();

		// register the button "credit"
		TextButton creditButton = new TextButton("Credit", getSkin() );
		creditButton.addListener(new ButtonListener.ChangeScreenListener(game,game.getGameScreen(LevelManager.loadLevel(Gdx.files.internal("levels/level1.xml"),new Level())))); 
		//creditButton.addListener(new ButtonListener.ChangeScreenListener(game,game.getCreditScreen())); 
		
		
		table.add(creditButton).size(this.BUTTONW, this.BUTTONH).uniform().spaceBottom(10).right();
		table.row();

		// register the button "quit"
		TextButton quit = new TextButton("Quitter", getSkin());
		quit.addListener(new DefaultInputListener() {
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button )
			{
				super.touchUp( event, x, y, pointer, button );
				Gdx.app.exit();
			}
		} );
		table.add(quit).size(this.BUTTONW, this.BUTTONH).uniform().spaceBottom(10).right();
	}

	/**
	 * 
	 */
	public void render(float delta) {
		super.render(delta);
		this.stage.draw();
	}

}
