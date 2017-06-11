package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSprite;

/**
 * Created by catolica2017 on 10/06/17.
 */
public class SegundaCena extends AGScene {
    AGSprite bulldog = null;
    AGSprite briga = null;
    AGSprite gato = null;
    int indiceAnimacao = 0;
    boolean pausa = false;

    SegundaCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }

    @Override
    public void init() {
        setSceneBackgroundColor(0.0f, 0.0f, 0.0f);
        gato = createSprite(R.drawable.gato, 8, 8);
        gato.setScreenPercent(20, 40);
        gato.vrPosition.setX(gato.getSpriteWidth() / 2);
        gato.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        gato.addAnimation(10, true, 0, 15);
        gato.addAnimation(10, true, 16, 28);
        gato.addAnimation(10, true, 29, 40);
        gato.setCurrentAnimation(2);

        briga = createSprite(R.drawable.briga, 8, 4);
        briga.setScreenPercent(20,40);
        briga.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        briga.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        briga.addAnimation(15, false, 0, 27);

        briga.bVisible = false;

        bulldog = createSprite(R.drawable.buldogue, 4, 4);
        bulldog.setScreenPercent(20, 40);
        bulldog.vrPosition.setX((AGScreenManager.iScreenWidth - bulldog.getSpriteWidth())/2);
        bulldog.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        bulldog.addAnimation(10, true, 0, 11);

        bulldog.iMirror = AGSprite.HORIZONTAL;
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
            pausa = !pausa;
        }
        if(!pausa) {
            logicaJogo();
        }
//            indiceAnimacao = (indiceAnimacao == 2) ? 0 : ++indiceAnimacao;
//            gato.setCurrentAnimation(indiceAnimacao);
//        }
    }

    private void logicaJogo(){
        gato.bVisible = !gato.bVisible;
        gato.vrPosition.fX += 5;
        bulldog.vrPosition.fX -= 5;
        if(bulldog.collide(gato))
        {
            bulldog.bVisible = false;
            gato.bVisible = false;
            briga.bVisible = true;
        }
    }
}
