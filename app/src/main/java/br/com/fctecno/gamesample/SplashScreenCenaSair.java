package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSprite;
import br.com.fctecno.andgraph.AGTimer;

/**
 * Created by root on 03/08/17.
 */

public class SplashScreenCenaSair extends AGScene  {
    AGTimer tempo;
    AGSprite planoFundo = null;

    SplashScreenCenaSair(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }

    @Override
    public void init() {
        // Carrega a imagem de fundo 100x100 centro da tela
        planoFundo = createSprite(R.drawable.sair, 1, 1);
        planoFundo.setScreenPercent(100, 100);
        planoFundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        planoFundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        tempo = new AGTimer(3000);

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
            vrGameManager.vrActivity.finish();
        }
    }
}