package br.com.fctecno.gamesample;

import android.view.Menu;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGScene;

/**
 * Created by root on 11/06/17.
 */

public class MenuCena extends AGScene {

    MenuCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init() {
        setSceneBackgroundColor(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

    }
}
