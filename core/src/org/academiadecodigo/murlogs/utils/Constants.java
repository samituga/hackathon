package org.academiadecodigo.murlogs.utils;

import com.badlogic.gdx.math.Vector2;



public class Constants {


    public static final int GAME_WIDTH = 1024;
    public static final int GAME_HEIGHT = 576;
    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -30);
    public static final Vector2 PLAYER_LEFT = new Vector2(-0.4f, 0);
    public static final Vector2 PLAYER_RIGHT = new Vector2(0.4f, 0);
    public static final Vector2 ENEMY_RIGHT = new Vector2(0.5f, 0);
    public static final Vector2 ENEMY_LEFT = new Vector2(-0.3f, 0);

    public static final int LEFT_X = -1;
    public static final int RIGHT_X = 1;

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;

    public static final float LEFT_LIMIT_X = 0f;
    public static final float LEFT_LIMIT_Y = 0;
    public static final float LEFT_LIMIT_WIDTH = 1;
    public static final float LEFT_LIMIT_HEIGHT = 576;
    public static final float RIGHT_LIMIT_X = 20f;
    public static final float RIGHT_LIMIT_Y = 0;
    public static final float RIGHT_LIMIT_WIDTH = 1;
    public static final float RIGHT_LIMIT_HEIGHT = 576;

    public static final float GROUND_WIDTH = 25f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float PLAYER_X = 2;
    public static final float PLAYER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float PLAYER_WIDTH = 1f;
    public static final float PLAYER_HEIGHT = 3f;
    public static final float PLAYER_DENSITY = 0.5f;
    public static final float PLAYER_DODGE_X = 2f;
    public static final float PLAYER_DODGE_Y = 1.5f;
    public static final float PLAYER_GRAVITY_SCALE = 1.5f;

    public static final Vector2 PLAYER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 20f);

    public static final String BACKGROUND_IMAGE_PATH = "stage_one.png";
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;
    public static final float WORLD_TO_SCREEN = 32;
    public static final String CHARACTERS_ATLAS_PATH = "./player/doe.txt";
    public static final String[] PLAYER_RUNNING_REGION_NAMES = new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};
    public static final float ENEMY_X = 18f;
    public static final float ENEMY_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float ENEMY_DODGE_X = 2f;
    public static final float ENEMY_DODGE_Y = 1.5f;
    //public static final String PUNCH_ATLAS = "./player/doe.txt";
    //public static final String JUMP_ATLAS = "./player/doe.txt";
    public static final String[] PLAYER_JUMPING_IMAGE_SET = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
    public static final String[] PLAYER_PUNCHING_IMAGE_SET = new String[]{"sprite0", "sprite1", "sprite2", "sprite3"};
    public static final String PLAYER_LOSE_IMAGE_PATH = "playerLose.png";
    public static final String PLAYER_WIN_IMAGE_PATH = "playerWin.png";
    public static final String[] PLAYER_CROUCHING_IMAGE_SET = new String[]{"crouching0", "crouching1", "crouching2"};
    public static final String[] PLAYER_BLOCKING_IMAGE_SET = new String[]{"sprite20", "sprite21", "sprite22"};
    public static final String CHARACTERS_ATLAS_BLOCK_PATH = "./player/block/block.txt";

    /*public static final String PLAYER_DODGING_REGION_NAME = ;

    public static final String PLAYER_JUMPING_REGION_NAME = ;
    public static final String PLAYER_HIT_REGION_NAME = ; [""];*/
}
