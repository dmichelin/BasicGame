package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.physics.box2d.*;

import javax.swing.*;

import static com.badlogic.gdx.Gdx.input;

public class BasicGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureAtlas textureAtlas;
	Sprite banana;
	World world;
	//PEXML physicsbodies;
	int x =300;
	int y= 300;


	Rectangle bananaBounds;

	Rectangle cherryBounds;

	OrthographicCamera camera;
	ExtendViewport viewport;
	
	@Override
	public void create () {
		//input.setCursorCatched(true);
		// Create the camera
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(1060,800,camera);
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas("sprites.txt");
		banana = textureAtlas.createSprite("banana");
		bananaBounds = new Rectangle(banana.getX(),banana.getY(),banana.getWidth(),banana.getHeight());
		world = new World(new Vector2(0,-10),true);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		drawsprite("banana",0,0);
		drawsprite("cherries",x,y);
		cherryBounds = new Rectangle(x,y,10,10);
		handlePlayerInput();

		batch.end();
		world.step(1/60f, 6, 2);
	}

	public void handlePlayerInput() {
		if(!bananaBounds.overlaps(cherryBounds)) {

			if (input.isKeyPressed(Input.Keys.W)) y = y + 10;

			else if (input.isKeyPressed(Input.Keys.S)) y = y - 10;

			if (input.isKeyPressed(Input.Keys.A)) x = x - 10;

			else if (input.isKeyPressed(Input.Keys.D)) x = x + 10;

		}

	}


	// Logic for drawing sprites on the screen
	public void drawsprite(String name, float x, float y){
		// Create the sprite using the name givne
		Sprite sprite = textureAtlas.createSprite(name);
		// Set the position of where to draw the sprite
		sprite.setPosition(x,y);
		//sprite.setRotation(Gdx.input.getPitch());
		// Draw it using the batch
		sprite.draw(batch);
	}

	// Logic for resizing the screen
	@Override
	public void resize(int width, int height){
		viewport.update(width,height,true);
		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textureAtlas.dispose();
	}
}
