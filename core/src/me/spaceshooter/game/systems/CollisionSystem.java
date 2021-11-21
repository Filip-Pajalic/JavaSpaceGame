package me.spaceshooter.game.systems;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Event;
import me.spaceshooter.event.core.EventTypes;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.event.core.Subject;
import me.spaceshooter.event.events.DebugEvent;
import me.spaceshooter.event.subjects.CollisionSubject;
import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;

public class CollisionSystem extends GameSystem {

    Event event_collision;
    private Subject subject;

    public CollisionSystem(){
        this.subject = new CollisionSubject();
        event_collision = new DebugEvent(EventTypes.DEBUG_COLLISION);
    }

    @Override
    public void update(List<Entity> entityList , float dt) {
        for (Entity entity : entityList) {
            if (entity.getComponent(CollisionComponent.class) != null) {
                for (Entity entity2 : entityList) {
                    if (entity2.getComponent(CollisionComponent.class) != null) {
                        if(!entity2.equals(entity)) {
                            CollisionComponent entityCollision = entity.getComponent(CollisionComponent.class);
                            CollisionComponent entityCollision2 = entity2.getComponent(CollisionComponent.class);
                            Vector2 entitiyPosition = entity.getComponent(PositionComponent.class).getPosition();
                            Vector2 entitiyPosition2 = entity2.getComponent(PositionComponent.class).getPosition();
                            Rectangle rect = null;
                            Circle circle = null;
                            if(entityCollision.getCircle() == null && entityCollision2.getCircle() != null){
                                rect = new Rectangle(entitiyPosition.x,entitiyPosition.y,entityCollision.getRect().width,entityCollision.getRect().height);
                                circle = new Circle(entitiyPosition2.x,entitiyPosition2.y,entityCollision2.getCircle().radius);
                            }else if(entityCollision.getCircle() != null && entityCollision2.getCircle() == null){
                                rect = new Rectangle(entitiyPosition2.x,entitiyPosition2.y,entityCollision2.getRect().width,entityCollision2.getRect().height);
                                circle = new Circle(entitiyPosition.x,entitiyPosition.y,entityCollision.getCircle().radius);
                            }
                            if(rect != null && circle != null ) {
                                if (detectCollisionCircleRect(rect, circle)) {

                                    entityCollision.setCollided(true);
                                    entityCollision2.setCollided(true);
                                }
                                else{
                                    entityCollision.setCollided(false);
                                    entityCollision2.setCollided(false);
                                }
                            }

                            if (Constants.DEBUGUITEXT) {
                                event_collision.setMessage("Collision: " + entityCollision.getCollided());
                                this.subject.notify(entity, event_collision);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addObserver(Observer ob) {
        this.subject.addObserver(ob);
    }

    public boolean detectCollisionCircleRect(Rectangle rect, Circle circle){

        //Simple collision from https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
        //Update this
        Vector2 circleDistance = new Vector2();
        float cornerDistance_sq = 0.0f;
        circleDistance.x = Math.abs(circle.x - rect.x);
        circleDistance.y = Math.abs(circle.y - rect.y);
        if (circleDistance.x > (rect.width/2 + circle.radius)) { return false; }
        if (circleDistance.y > (rect.height/2 + circle.radius)) { return false; }

        if (circleDistance.x <= (rect.width/2)) { return true; }
        if (circleDistance.y <= (rect.height/2)) { return true; }

        cornerDistance_sq = floatPow(circleDistance.x - rect.width/2,2.0f) + floatPow(circleDistance.y - rect.height/2,2.0f);

        return (cornerDistance_sq <= floatPow(circle.radius,2.0f));
    }

    public float floatPow(float x, float p) {
        double dblResult = Math.pow(x, p);
        return (float)dblResult;
    }


}
