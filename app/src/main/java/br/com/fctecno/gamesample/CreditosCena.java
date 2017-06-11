package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSprite;

/**
 * Created by root on 11/06/17.
 */

public class CreditosCena extends AGScene {
    AGSprite btnVoltar = null;

    CreditosCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init() {
        setSceneBackgroundColor(1.1f, 0.1f, 0.0f);

        btnVoltar = this.createSprite(R.drawable.btnvoltar, 1,1);
        btnVoltar.setScreenPercent(20, 10);
        btnVoltar.vrPosition.setX(AGScreenManager.iScreenWidth - btnVoltar.getSpriteWidth());
        btnVoltar.vrPosition.setY(AGScreenManager.iScreenHeight - btnVoltar.getSpriteHeight());
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(AGInputManager.vrTouchEvents.screenClicked()) {
//            AGSoundManager.vrSoundEffects.play(codigoExplosao);
            if (btnVoltar.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                vrGameManager.setCurrentScene(1);
                return;
            }
        }
    }
}
