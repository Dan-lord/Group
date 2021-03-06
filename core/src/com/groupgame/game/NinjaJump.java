package com.groupgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
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
import com.groupgame.game.sprites.Obstacle;
import com.groupgame.game.states.GameStateManager;
import com.groupgame.game.states.MenuState;
import com.groupgame.game.states.PlayState;

public class NinjaJump extends ApplicationAdapter {
	
	//define screen
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final int BRICK=38;

	public static final String TITLE="NinjaJump";
	private  GameStateManager gsm;

	//only need one spritebatch, make public
	public SpriteBatch batch;

	private static Music music;

	private Stage stage;
	public static Label outputLabel;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		int row_height = Gdx.graphics.getWidth() / 12;
		int col_width = Gdx.graphics.getWidth() / 12;
		Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		//TextButton
		Button button1 = new TextButton("Pause",mySkin,"small");
		button1.setSize(100,50);
		//button1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
		//imageButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start.png"))));
		button1.setPosition(Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()-row_height);
		button1.addListener(new InputListener(){
			@Override
			public boolean handle(Event e) {
				PlayState.pause();
				outputLabel.setText("Press Screen to Resume");
				return super.handle(e);
			}

		});
		stage.addActor(button1);

		// Text Button
//		Button button2 = new TextButton("Resume",mySkin,"small");
//		button2.setSize(90,40);
//		button2.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-row_height);
//		button2.addListener(new InputListener(){
//			@Override
//			public boolean handle(Event e) {
//				PlayState.resume();
//				outputLabel.setText("");
//				return super.handle(e);
//			}
//
//		});
//		stage.addActor(button2);

//		//TextButton
//		Button button3 = new TextButton("Boost",mySkin,"small");
//		button3.setSize(100,50);
//		//button1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
//		//imageButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start.png"))));
//		button3.setPosition(Gdx.graphics.getWidth()/2-50,row_height);
//		button3.addListener(new InputListener(){
//			@Override
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				super.touchUp(event, x, y, pointer, button);
//				Obstacle.fake=false;
//			}
//
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				if(PlayState.count>10){
//					Obstacle.fake=true;
//					PlayState.count-=5;
//				}
//				return super.touchDown(event, x, y, pointer, button);
//
//			}
//		});
//		stage.addActor(button3);

		outputLabel = new Label("",mySkin,"black");
		outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
		outputLabel.setPosition(0,row_height*4);
		outputLabel.setFontScale(2.0f);
		outputLabel.setAlignment(Align.center);
		stage.addActor(outputLabel);


		batch = new SpriteBatch();
		gsm =new GameStateManager();
		
		//background music
		music = Gdx.audio.newMusic(Gdx.files.internal("FateAndDestiny.mp3"));
        music.setLooping(true);
        music.setVolume(0.15f);
        music.play();
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
		music.pause();
		super.pause();
	}

	@Override
	public void resume() {
		music.play();
		super.resume();
	}

	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
		music.dispose();

	}


}
