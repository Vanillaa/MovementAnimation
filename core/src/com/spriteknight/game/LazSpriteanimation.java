package com.spriteknight.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LazSpriteanimation extends ApplicationAdapter {

	SpriteBatch batch;
	TextureAtlas taKnight;
	Sprite[] spKnight;
	int  nCurrentIndex;
	Animation animRight, animLeft;
	float stateTime, fCharacterVelocityX = 0, fCharacterVelocityY = 0, fCharacterX, fCharacterY, fCharacterWidth, fOldX, fOldY;
	int nVelocityX, nVelocityY;
	TextureRegion currentFrame;
	TextureRegion[] atrFront, atrBack, atrLeft, atrRight;
	boolean[] arbDirection = new boolean[2];// 1=right, 2=left
	boolean bStop = true, bCollidedX;
	Sprite sprChar;
	int nSHeight, nSWidth, nLayerCount, nC=0;
	float fTileWidth, fTileHeight;

	public void create() {
		nSHeight = Gdx.graphics.getHeight(); //use to make scaling
		nSWidth = Gdx.graphics.getWidth();
		nVelocityX = nSWidth * 10 / 1794;
		nVelocityY = nSHeight * 10 / 1080;
		fCharacterWidth = nSWidth * 110 / 1794;
		arbDirection[0] = true;
		sprChar=new Sprite();

		// Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();

		//Create an array sprites loaded from the TextureAtlas
		taKnight = new TextureAtlas(Gdx.files.internal("assets/KnightSpriteSheet.pack"));
		//spKnight = new Sprite[4];
		atrRight = new TextureRegion[3];
		atrLeft = new TextureRegion[3];
		for (int j = 0; j <= 2; j++) {
			atrLeft[j] = taKnight.findRegion("knightleft" + (j+1));



		}
		animLeft = new Animation(0.15f, atrLeft);
		for (int j = 0; j <= 2; j++) {
			atrRight[j] = taKnight.findRegion("knightright" + (j+1));

		}
		animRight = new Animation(0.15f, atrRight);
		stateTime = 0f;
		sprChar = new Sprite(atrRight[0]);

	}

	@Override
	public void resize(int width, int height) {

	}


	public void setCharacterVelocity(int _nVx, int _nVy) {
		fCharacterVelocityX = nVelocityX * _nVx;
		fCharacterVelocityY = nVelocityY * _nVy;
	}

	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		for (int i = 0; i < 2; i++) {//set all direction booleans to false unless it's the current direction
			if (nCurrentIndex == i) {
			} else {
				arbDirection[i] = false;
			}
		}  if (arbDirection[0]) {
			if (bStop) {
				currentFrame = atrLeft[0];
			} else {
				currentFrame = animLeft.getKeyFrame(stateTime, true);
			}
		} else if (arbDirection[1]) {
			if (bStop) {
				currentFrame = atrRight[0];
			} else {
				currentFrame = animRight.getKeyFrame(stateTime, true);
			}
		}

		sprChar = new Sprite(currentFrame);//Create the sprite of the character based on the current texture region frame
		fCharacterX += fCharacterVelocityX / 2;
		fCharacterY += fCharacterVelocityY / 2;
		batch.begin();
		batch.draw(sprChar, fCharacterX, fCharacterY);
		batch.end();

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		sprChar.getTexture().dispose();
	}

	public void getBoolsBack(boolean[] _arbDirection, boolean _bStop, int _nCurrentIndex) {
		arbDirection = _arbDirection;
		bStop = _bStop;
		nCurrentIndex = _nCurrentIndex;
	}
}
