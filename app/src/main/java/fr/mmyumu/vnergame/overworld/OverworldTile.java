package fr.mmyumu.vnergame.overworld;

import android.graphics.Rect;

import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.vnergame.Assets;
import fr.mmyumu.vnergame.Background;

public class OverworldTile {

    private int tileX, tileY, speedX;
    public int type;
    public Image tileImage;

    private OverworldCharacter mainCharacter = OverworldScreen.getMainCharacter();
    private Background bg = OverworldScreen.getBg1();

    private Rect r;

    public OverworldTile(int x, int y, int typeInt) {
        tileX = x * 40;
        tileY = y * 40;

        type = typeInt;

        r = new Rect();

        if (type == 5) {
            tileImage = Assets.tiledirt;
        } else if (type == 8) {
            tileImage = Assets.tilegrassTop;
        } else if (type == 4) {
            tileImage = Assets.tilegrassLeft;

        } else if (type == 6) {
            tileImage = Assets.tilegrassRight;

        } else if (type == 2) {
            tileImage = Assets.tilegrassBot;
        } else {
            type = 0;
        }

    }

        public void update() {
            speedX = bg.getSpeedX() * 5;
            tileX += speedX;
            r.set(tileX, tileY, tileX+40, tileY+40);
    
            
            
            if (Rect.intersects(r, OverworldCharacter.yellowRed) && type != 0) {
                checkVerticalCollision(OverworldCharacter.rect, OverworldCharacter.rect2);
                checkSideCollision(OverworldCharacter.rect3, OverworldCharacter.rect4, OverworldCharacter.footleft, OverworldCharacter.footright);
            }
    
        }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public void checkVerticalCollision(Rect rtop, Rect rbot) {
        if (Rect.intersects(rtop, r)) {
            
        }

        if (Rect.intersects(rbot, r) && type == 8) {
            mainCharacter.setJumped(false);
            mainCharacter.setSpeedY(0);
            mainCharacter.setCenterY(tileY - 63);
        }
    }

    public void checkSideCollision(Rect rleft, Rect rright, Rect leftfoot, Rect rightfoot) {
        if (type != 5 && type != 2 && type != 0){
            if (Rect.intersects(rleft, r)) {
                mainCharacter.setCenterX(tileX + 102);
    
                mainCharacter.setSpeedX(0);
    
            }else if (Rect.intersects(leftfoot, r)) {
                
                mainCharacter.setCenterX(tileX + 85);
                mainCharacter.setSpeedX(0);
            }
            
            if (Rect.intersects(rright, r)) {
                mainCharacter.setCenterX(tileX - 62);
    
                mainCharacter.setSpeedX(0);
            }
            
            else if (Rect.intersects(rightfoot, r)) {
                mainCharacter.setCenterX(tileX - 45);
                mainCharacter.setSpeedX(0);
            }
        }
    }

}