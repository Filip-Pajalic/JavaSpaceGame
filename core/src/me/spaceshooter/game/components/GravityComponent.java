package me.spaceshooter.game.components;

import me.spaceshooter.game.Component;

public class GravityComponent extends Component {



    public GravityComponent(float gravityConstant){

    }

    @Override
    public void start(){
        System.out.println("starting");

    }

    @Override
    public void update(float dt) {
        //System.out.println("gravity online");
    }
}
