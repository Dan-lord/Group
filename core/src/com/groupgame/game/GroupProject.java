package com.groupgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupgame.game.states.GameStateManager;
import com.groupgame.game.states.MenuState;
import com.groupgame.game.states.PlayState;

public class GroupProject extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final int BRICK=38;

	public static final String TITLE="Group Game";
	private  GameStateManager gsm;

	private SpriteBatch batch;

	private Music music;

	private Stage stage;
	private Label outputLabel;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		int row_height = Gdx.graphics.getWidth() / 12;
		int col_width = Gdx.graphics.getWidth() / 12;
		Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		//ImageTextButton
		ImageButton imageButton = new ImageButton(mySkin);
		imageButton.setSize(96,96);
		imageButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
		imageButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start.png"))));
		imageButton.setPosition(col_width,Gdx.graphics.getHeight()-row_height*2);
		imageButton.addListener(new InputListener(){

			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				PlayState.pause();
				outputLabel.setText("Press up");
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				PlayState.resume();
				outputLabel.setText("Pressed down");
				return true;
			}
		});
		stage.addActor(imageButton);

		outputLabel = new Label("Press a Button",mySkin,"black");
		outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
		outputLabel.setPosition(0,row_height);
		outputLabel.setAlignment(Align.center);
		stage.addActor(outputLabel);


		batch = new SpriteBatch();
		gsm =new GameStateManager();
//		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//        music.setLooping(true);
//        music.setVolume(0.1f);
//        music.play();
		Gdx.gl.glClearColor(0, 0, 0, 1);

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //获取时间间隔
		gsm.render(batch);
		stage.act();
		stage.draw();


	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();

	}
}
