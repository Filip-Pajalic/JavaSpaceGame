package me.spaceshooter.game.components;

import me.spaceshooter.game.Component;

public class PhysicsComponent extends Component {


    private boolean gravity;

    public PhysicsComponent(){

    }

    @Override
    public void start(){
        System.out.println("starting");

    }

    @Override
    public void update(float dt) {
        //System.out.println("gravity online");
    }

    public boolean isGravity(){
        return gravity;
    }
}
