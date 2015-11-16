package com.spriteknight.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Aidan on 2015-11-12.
 */
public class LazGame extends ApplicationAdapter {
    LazSpriteanimation character;
    Touchpad touchpad;

    public  void create() {
        character = new Character();
        character.create();
        touchpad = new Touchpad();
        touchpad.create();
        touchpad.setCharacter(character, character.arbDirection, character.bStop);

    }
@Override
public void render(){
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    touchpad.render();
    character.render();}
    }
