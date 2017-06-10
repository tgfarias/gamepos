package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSoundManager;
import br.com.fctecno.andgraph.AGSprite;

/**
 * Created by catolica2017 on 10/06/17.
 */
public class PrimeiraCena extends AGScene {
    AGSprite botao = null;
    int codigoExplosao = 0;

    PrimeiraCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }

    @Override
    public void init() {
        codigoExplosao = AGSoundManager.vrSoundEffects.loadSoundEffect("efeito.mp3");
        botao = this.createSprite(R.mipmap.ic_riei, 1,1);
        botao.setScreenPercent(40, 20);
        botao.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        botao.vrPosition.setY(AGScreenManager.iScreenHeight / 2);

        //FADE
        botao.fadeIn(4000);
        botao.moveTo(4000, 300, 300);

        //Adicionando musica ao fundo
        AGSoundManager.vrMusic.loadMusic("fundo.mp3", true);//true indicica loop no som
        AGSoundManager.vrMusic.play();
        setSceneBackgroundColor(1.0f, 0.0f, 0.0f);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(AGInputManager.vrTouchEvents.screenClicked()){
            AGSoundManager.vrSoundEffects.play(codigoExplosao);
            if(botao.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(1);
            }
        }

    }
}
