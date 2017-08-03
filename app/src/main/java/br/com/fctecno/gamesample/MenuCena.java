package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSprite;

/**
 * Created by root on 11/06/17.
 */


public class MenuCena extends AGScene {
    AGSprite btnGame = null;
    AGSprite btnCredito = null;
    AGSprite btnFechar = null;
    AGSprite planoFundo = null;


    MenuCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init() {
        // Carrega a imagem de fundo 100x100 centro da tela
        planoFundo = createSprite(R.drawable.bg_tela_principal_com_persons, 1, 1);
        planoFundo.setScreenPercent(100, 100);
        planoFundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        planoFundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);


        btnGame = this.createSprite(R.drawable.btn_jogar, 1,1);
        btnGame.setScreenPercent(20, 10);
        btnGame.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        btnGame.vrPosition.setY(AGScreenManager.iScreenHeight / 3);

        btnCredito = this.createSprite(R.drawable.btn_creditos, 1,1);
        btnCredito.setScreenPercent(20, 10);
        btnCredito.vrPosition.setX(AGScreenManager.iScreenWidth / 6);
        btnCredito.vrPosition.setY(AGScreenManager.iScreenHeight / 3);

        btnFechar = this.createSprite(R.drawable.btn_sair, 1,1);
        btnFechar.setScreenPercent(20, 10);
        btnFechar.vrPosition.setX((float) (AGScreenManager.iScreenWidth / 1.2));
        btnFechar.vrPosition.setY(AGScreenManager.iScreenHeight / 3);

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
//            AGSoundManager.vrSoundEffects.play(codigoExplosao);
            if(btnGame.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(3);
                return;
            }
            if(btnCredito.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(2);
                return;
            }
            if(btnFechar.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(6);
                // vrGameManager.vrActivity.finish();
            }
        }
    }
}