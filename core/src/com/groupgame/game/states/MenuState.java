package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.groupgame.game.NinjaJump;

public class MenuState extends State {

    private Texture background;
    private Texture butt;

    private BitmapFont font = new BitmapFont();

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("background.png");  //设置背景图片
        butt=new Texture("butt.png");  //设置开始按钮
        cam.setToOrtho(false,NinjaJump.WIDTH,NinjaJump.HEIGHT);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);// 防止字体模糊

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.setProjectionMatrix(cam.combined);//set orthographic projection

        sb.begin();
        sb.draw(background,0,0,NinjaJump.WIDTH,NinjaJump.HEIGHT);  //设置背景图位置
        sb.draw(butt,(NinjaJump.WIDTH/2)-(butt.getWidth()/2),NinjaJump.HEIGHT/2);
        font.draw(sb,"!!!Welcome to Ninja Jump!!!",NinjaJump.BRICK+20,NinjaJump.HEIGHT/2+200);
        font.setColor(Color.BLACK);
        font.getData().setScale(2.0f);

        sb.end();

    }

    @Override
    public void dispose() {  //释放内存
        background.dispose();
        butt.dispose();
        font.dispose();
    }
}
