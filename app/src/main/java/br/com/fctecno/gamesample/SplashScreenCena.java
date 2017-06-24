package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGTimer;

/**
 * Created by root on 11/06/17.
 */

public class SplashScreenCena extends AGScene {

    AGTimer tempo;
    SplashScreenCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }

    @Override
    public void init() {
        setSceneBackgroundColor(0.0f, 0.1f, 0.1f);
        tempo = new AGTimer(2000);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        tempo.update();
        if(tempo.isTimeEnded()){
            vrGameManager.setCurrentScene(1);
        }
    }
}
