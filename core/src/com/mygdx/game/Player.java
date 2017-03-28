package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by daniel on 3/28/17.
 */
public class Player {

    float x;
    float y;
    String name;

    public Player(String name){
        this.name = name;
    }


    public void handlePlayerInput(){
        if (input.isKeyPressed(Input.Keys.W)) y = y + 10;

        else if (input.isKeyPressed(Input.Keys.S)) y = y - 10;

        if (input.isKeyPressed(Input.Keys.A)) x = x - 10;

        else if (input.isKeyPressed(Input.Keys.D)) x = x + 10;
    }

    public float getPlayerX(){
        return x;
    }
    public float getPlayerY(){
        return y;
    }


}
