package me.spaceshooter.game.core;


import java.util.ArrayList;
import java.util.List;

public class Enitity {
    private String name;
    private List<Component> componentList;
    public Enitity(String name)
    {
        componentList = new ArrayList<>();
        this.name = name;
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component c : componentList) {
            if (componentClass.isAssignableFrom(c.getClass())) { //vad exakt gör denna
                try {
                    return componentClass.cast(c); //hur fungerar cast i java?
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error casting component";
                }
            }
        } return null;
    }
    public <T extends Component> void removeComponent(Class<T> componentClass){ //concurrentmodificationerror?
        for(int i = 0 ; i< componentList.size(); i++){
            Component c = componentList.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                componentList.remove(i);
                return;
            }
        }
    }

    public  void addComponent(Component c){
        componentList.add(c);
        c.enitity = this;
    }

    public void update(float dt){
        for(int i = 0 ; i< componentList.size(); i++){
            componentList.get(i).update(dt);
        }
    }

    public void start(){
        for(int i = 0 ; i< componentList.size(); i++){
            componentList.get(i).start();
        }
    }
}
