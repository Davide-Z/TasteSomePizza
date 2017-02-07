import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by tic-tac on 25/01/17.
 */
/*
public class Test{
    ContainerTest containerTest=new ContainerTest("Title");


    public static void main(String[] args) throws SlickException {
        AppGameContainer app=new AppGameContainer(new ContainerTest("Titre"), 640,480,false);
        app.setShowFPS(false);
        app.start();
    }
}
*/
public class Test extends BasicGame {

    GameContainer container;
    TiledMap map;

    //char
    private float x=200,y=150;
    private int direction=0;
    private boolean moving = false;
    private Animation[] animations = new Animation[8];

    public Test() {
        super("Slick Test 1");
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y){
        Animation animation = new Animation();
        for (int x=startX; x<endX;x++){
            animation.addFrame(spriteSheet.getSprite(x,y),100);
        }
        return animation;

    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container=gameContainer;
        this.map=new TiledMap("resources/map/grassed.tmx");
        SpriteSheet spriteSheet=new SpriteSheet("resources/map/tiles/sprites/people/exampleslick.png", 64, 64);

        this.animations[0]=loadAnimation(spriteSheet, 0,1,0);
        this.animations[1]=loadAnimation(spriteSheet, 0,1,1);
        this.animations[2]=loadAnimation(spriteSheet, 0,1,2);
        this.animations[3]=loadAnimation(spriteSheet, 0,1,3);
        this.animations[4]=loadAnimation(spriteSheet, 1,9,0);
        this.animations[5]=loadAnimation(spriteSheet, 1,9,1);
        this.animations[6]=loadAnimation(spriteSheet, 1,9,2);
        this.animations[7]=loadAnimation(spriteSheet, 1,9,3);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (this.moving){
            switch(this.direction){
                case 0:this.y -= 3;break;
                case 1:this.x -= 3;break;
                case 2:this.y += 3;break;
                case 3:this.x += 3;break;
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render(0,0);
        graphics.setColor(new Color(0,0,0,.5f));
        graphics.fillOval(x-17,y+10,32,16);
        graphics.drawAnimation(animations[direction+(moving?4:0)],x-33,y-40);
        graphics.setColor(Color.pink);
        graphics.drawString("Coucou", 200,50);


    }

    @Override
    public void keyReleased(int key, char c){
        if (Input.KEY_ESCAPE==key)
            container.exit();
        this.moving=false;
    }
    @Override
    public void keyPressed(int key, char c){
        switch (key){
            case Input.KEY_UP:this.direction=0;this.moving=true;break;
            case Input.KEY_LEFT:this.direction=1;this.moving=true;break;
            case Input.KEY_DOWN:this.direction=2;this.moving=true;break;
            case Input.KEY_RIGHT:this.direction=3;this.moving=true;break;
        }
    }

    @Override
    public void mousePressed(int button, int x, int y){
        this.x=x;this.y=y;
    }
    @Override
    public void mouseReleased(int button, int x, int y){
        this.moving=false;
    }

    //@Override
    //public void mouseMoved() ou mouseDragged() pour drag and drop les tourelles?

    public static void main(String[] args) throws SlickException{
        AppGameContainer app=new AppGameContainer(new Test(),640,480,false);
        app.setShowFPS(false);
        app.start();
    }
}
