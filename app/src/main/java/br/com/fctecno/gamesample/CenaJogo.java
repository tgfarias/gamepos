package br.com.fctecno.gamesample;

import java.util.ArrayList;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSoundEffect;
import br.com.fctecno.andgraph.AGSoundManager;
import br.com.fctecno.andgraph.AGSprite;
import br.com.fctecno.andgraph.AGTimer;

/**
 * Created by Tiago on 22/06/2017.
 */

public class CenaJogo extends AGScene {
    int efeitoCatraca = 0;
    int efeitoExplosao = 0;
    AGTimer tempoCanhao = null;
    AGTimer tempoBala = null;
    AGSprite planoFundo = null;
    AGSprite canhao = null;
    ArrayList<AGSprite> vetorTiros = null;
    ArrayList<AGSprite> vetorExplosoes = null;
    AGSprite[] navios = new AGSprite[2];


    CenaJogo(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init()
    {




        vetorTiros = new ArrayList<AGSprite>();
        vetorExplosoes = new ArrayList<AGSprite>()
        planoFundo = createSprite(R.drawable.textmar, 1, 1);
        planoFundo.setScreenPercent(100,100);
        planoFundo.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        planoFundo.vrPosition.setY(AGScreenManager.iScreenHeight /2 );

        createSprite(R.drawable.bala, 1, 1).bVisible=false;// Carrega imagem na memoria

        navios[0] = createSprite(R.drawable.navio, 1, 1);
        navios[0].setScreenPercent(20,12);
        navios[0].iMirror = AGSprite.HORIZONTAL;
        navios[0].vrDirection.fX = 1;
        navios[0].vrPosition.fX = -navios[0].getSpriteWidth() / 2;
        navios[0].vrPosition.fY = AGScreenManager.iScreenHeight -
                navios[0].getSpriteHeight() / 2;

        navios[1] = createSprite(R.drawable.navio, 1, 1);
        navios[1].setScreenPercent(20, 12);
        navios[1].vrDirection.fX = -1;

        navios[1].vrPosition.fX = AGScreenManager.iScreenWidth +
                navios[1].getSpriteWidth() / 2;

        navios[1].vrPosition.fY = navios[0].vrPosition.fY -
                navios[1].getSpriteHeight();

        canhao = createSprite(R.drawable.canhao, 1, 1);
        canhao.setScreenPercent(12, 20);
        canhao.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        canhao.vrPosition.setY(canhao.getSpriteHeight() / 2);

        tempoCanhao = new AGTimer(100);
        efeitoCatraca = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");
        efeitoExplosao = AGSoundManager.vrSoundEffects.loadSoundEffect("explodenavio.wav");
        tempoBala = new AGTimer(300);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(AGInputManager.vrTouchEvents.backButtonClicked()){
            vrGameManager.setCurrentScene(1);
            return;
        }
        this.atualizaMovimentoCanhao();
        this.atualizaBalas();
        this.criaTiro();
        this.atualizaNavios();
        this.verificaColisaoBalasNavios();
    }

    private void criaExplosao(int x, int y)
    {
        for (AGSprite explosao : vetorExplosoes){
            if(explosao.bRecycled){
                explosao.bRecycled = false;
                explosao.getCurrentAnimation().restart();
                explosao.vrPosition.fX = x;
                explosao.vrPosition.fY = y;
                return;
            }
        }
    }

    //atualizando a posicao dos navios
    private void atualizaNavios(){
        for (AGSprite navio : navios)
        {
            navio.vrPosition.fX +=  5 * navio.vrDirection.fX;

            if(navio.vrDirection.fX == 1)
            {
                if(navio.vrPosition.fX >=
                        AGScreenManager.iScreenWidth +
                        navio.getSpriteWidth() / 2)
                {
                    navio.iMirror = AGSprite.NONE;
                    navio.vrDirection.fX = -1;
                }
            }
            else
            {
                if(navio.vrPosition.fX <= -navio.getSpriteWidth() / 2 ){
                    navio.iMirror = AGSprite.HORIZONTAL;
                    navio.vrDirection.fX = 1;
                }
            }
        }
    }

    private void atualizaMovimentoCanhao(){
        //atualiza o tempo que estou medindo dentro deste time
        //atualizando o intervalo de tempo a ser medido

        tempoCanhao.update();


        if(tempoCanhao.isTimeEnded())
        {
            tempoCanhao.restart();

            if(AGInputManager.vrAccelerometer.getAccelX() > 2){
                if(canhao.vrPosition.getX() <= AGScreenManager.iScreenWidth - canhao.getSpriteWidth() / 2) {
                    AGSoundManager.vrSoundEffects.play(efeitoCatraca);
                    canhao.vrPosition.setX(canhao.vrPosition.getX() + 10);
                }
            }
            else if(AGInputManager.vrAccelerometer.getAccelX() < -2){
                if(canhao.vrPosition.getX() >= -canhao.getSpriteWidth() / 2) {
                    AGSoundManager.vrSoundEffects.play(efeitoCatraca);
                    canhao.vrPosition.setX(canhao.vrPosition.getX() - 10);
                }
            }
        }
    }

    private void verificaColisaoBalasNavios(){
        for (AGSprite bala : vetorTiros){
            if(bala.bRecycled)
                continue;

            for (AGSprite navio : navios)
            {
                if( bala.collide(navio)){
                    bala.bRecycled = true;
                    bala.bVisible = false;

                    AGSoundManager.vrSoundEffects.play(efeitoExplosao);
                    if(navio.vrDirection.fX == 1) {
                        navio.vrDirection.fX = -1;
                        navio.iMirror = AGSprite.NONE;
                        navio.vrPosition.fX = AGScreenManager.iScreenWidth + navio.getSpriteWidth() / 2;
                    }
                    else {
                        navio.vrDirection.fX = 1;
                        navio.iMirror = AGSprite.HORIZONTAL;
                        navio.vrPosition.fX = -navio.getSpriteWidth();
                    }
                    break;
                }
            }
        }
    }

    private void atualizaBalas(){
        for (AGSprite bala :  vetorTiros) {

            bala.vrPosition.fY += 10;

            //BALA SAIU DA TELA
            if(bala.vrPosition.fY > AGScreenManager.iScreenHeight + bala.getSpriteHeight() / 2)
            {
                bala.bRecycled = true;
                bala.bVisible = false;
            }
        }
    }

    private void criaTiro(){

        tempoBala.update();


        if(AGInputManager.vrTouchEvents.screenClicked()){
            if(!tempoBala.isTimeEnded())
            {
                return;
            }
            tempoBala.restart();
            for (AGSprite bala : vetorTiros){
                if(bala.bRecycled)
                {
                    bala.bRecycled = false;
                    bala.bVisible = true;
                    bala.vrPosition.fX = canhao.vrPosition.fX;
                    bala.vrPosition.fY =
                            canhao.getSpriteWidth() +
                                    bala.getSpriteHeight() / 2;
                    return;
                }
            }
            AGSprite novaBala = createSprite(R.drawable.bala, 1, 1);
            novaBala.setScreenPercent(8,5);
            novaBala.vrPosition.fX = canhao.vrPosition.fX;
            novaBala.vrPosition.fY =
                    canhao.getSpriteWidth() +
                            novaBala.getSpriteHeight() / 2;
            vetorTiros.add(novaBala);
        }
    }
}
