package me.spaceshooter.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.UUID;

import me.spaceshooter.Constants;
import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.GraphicsCompoment;

import me.spaceshooter.game.components.HealthComponent;
import me.spaceshooter.game.components.ParticleComponent;
import me.spaceshooter.game.components.PositionComponent;

import me.spaceshooter.game.components.PowerComponent;
import me.spaceshooter.game.core.Entity;

public class TargetEntity extends Entity {
    private Vector2 spawnPosition;

    private int health;
    private int radiusHitbox, radius, oldradius;
    private float explosionTimer = 1.0f;


    public TargetEntity(String name) {
        super(name);
        addComponent(new PositionComponent());
        addComponent(new CollisionComponent());
        addComponent(new HealthComponent());
        //Power
        addComponent(new PowerComponent());
        getComponent(PowerComponent.class).setPower("Explosion", false);
        //particle
        addComponent(new ParticleComponent());



        randomizeProperties();
        getComponent(PowerComponent.class).setPower("Explosion", false);

        addComponent(new GraphicsCompoment());

        getComponent(GraphicsCompoment.class).setFilled(new Color(1,0,0,0.1f));
        getComponent(GraphicsCompoment.class).setColor(new Color(1,0,0,1));




    }

    public void randomizeProperties(){
        if(!getComponent(ParticleComponent.class).getParticleEffectList().isEmpty()) {
            getComponent(ParticleComponent.class).getParticleEffectList().get(getComponent(ParticleComponent.class).getParticleEffectList().size() - 1).start();
        }
        getComponent(PowerComponent.class).setPower("Explosion", true);
        //getComponent(PowerComponent.class).setPowerTimer("Explosion", explosionTimer);
        this.health = MathUtils.random(20,50);
        this.oldradius = radius;
        setRadius();
        this.spawnPosition =  randomizePosition();
        getComponent(PositionComponent.class).setPosition(spawnPosition);
        getComponent(CollisionComponent.class).setShapeCircle(new Circle(spawnPosition.x,spawnPosition.y,radiusHitbox));
        getComponent(HealthComponent.class).setHealth(this.health);
        getComponent(PositionComponent.class).setLastPosition(spawnPosition);
        getComponent(ParticleComponent.class).addEffect(Gdx.files.internal("targetparticle.p"), Gdx.files.internal(""));
        getComponent(ParticleComponent.class).getParticleEffectList().get(getComponent(ParticleComponent.class).getParticleEffectList().size()-1).getEmitters()
                .first().setPosition(getComponent(PositionComponent.class).getPosition().x,getComponent(PositionComponent.class).getPosition().y);

    }

    public Vector2 randomizePosition(){
        Vector2 lastPosition = getComponent(PositionComponent.class).getLastPosition();
        Vector2 tempPosition = new Vector2(lastPosition.x,lastPosition.y);
        while (CollideCircles(lastPosition,tempPosition,radius,oldradius)){
            tempPosition.x = MathUtils.random(10+this.radius, Constants.WORLD_WIDTH-10-this.radius);
            tempPosition.y = MathUtils.random(30+this.radius, Constants.WORLD_HEIGHT-50-this.radius);
        }
        return tempPosition;
    }

    public boolean CollideCircles(Vector2 c1, Vector2 c2, int r1, int r2) {
        float x = c1.x - c2.x;
        float y = c1.y - c2.y;
        float centerDistanceSq = x * x + y * y;
        float radius = r1 + r2;
        float radiusSq = radius * radius;
        return centerDistanceSq <= radiusSq;
    }

    public void setRadius(){
        //ensure radius is only updated here and not other place in code.
        this.radius = this.health+10;
        this.radiusHitbox = this.health+15;
    }


}
