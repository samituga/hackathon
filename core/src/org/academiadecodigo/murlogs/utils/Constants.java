package org.academiadecodigo.murlogs.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final int GAME_WIDTH = 1024;
    public static final int GAME_HEIGHT = 576;
    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -30);
    public static final Vector2 PLAYER_LEFT = new Vector2(-0.5f, 0);
    public static final Vector2 PLAYER_RIGHT = new Vector2(0.5f,0);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 25f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;
    public static final float PLAYER_X = 2;
    public static final float PLAYER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float PLAYER_WIDTH = 1f;
    public static final float PLAYER_HEIGHT = 2f;
    public static final float PLAYER_DENSITY = 0.5f;
    public static final float PLAYER_DODGE_X = 2f;
    public static final float PLAYER_DODGE_Y = 1.5f;
    public static final float PLAYER_GRAVITY_SCALE = 3f;
    public static final Vector2 PLAYER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 20f);

    public static final String BACKGROUND_IMAGE_PATH = "background.png";
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;
    public static final float WORLD_TO_SCREEN = 32;
}
