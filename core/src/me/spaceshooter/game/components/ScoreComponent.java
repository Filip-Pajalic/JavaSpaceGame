package me.spaceshooter.game.components;

import me.spaceshooter.game.core.Component;

public class ScoreComponent extends Component {

    int score = 0;

    @Override
    public void update(float dt) {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore(int value){
        this.score += value;
    }
}
