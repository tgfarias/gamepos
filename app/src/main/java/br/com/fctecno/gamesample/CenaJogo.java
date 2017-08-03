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

    AGSprite[] placar = new AGSprite[6];
    AGSprite planoFundo = null;
    AGSprite canhao = null;
    AGSprite barraSuperior = null;

    int pontuacao = 0;
    int tempPontuacao = 0;

    ArrayList<AGSprite> vetorTiros = null;
    ArrayList<AGSprite> vetorExplosoes = null;
    AGSprite[] navios = new AGSprite[2];

    boolean bPausa = false;


    CenaJogo(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init()
    {

        vetorTiros = new ArrayList<AGSprite>();
        vetorExplosoes = new ArrayList<AGSprite>();
        planoFundo = createSprite(R.drawable.textmar, 1, 1);
        planoFundo.setScreenPercent(100,100);
        planoFundo.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        planoFundo.vrPosition.setY(AGScreenManager.iScreenHeight /2 );

        createSprite(R.drawable.bala, 1, 1).bVisible=false;// Carrega imagem na memoria
        createSprite(R.drawable.explosao, 4,2).bVisible = false; // carrega explosao na memoria

        barraSuperior = createSprite(R.drawable.barrasuperior, 1, 1); // sprite sem animação (1,1)
        barraSuperior.setScreenPercent(100,10);
        barraSuperior.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        barraSuperior.vrPosition.fY = AGScreenManager.iScreenHeight - barraSuperior.getSpriteHeight() / 2;
        barraSuperior.bAutoRender = false; // Motor não desenha automaticamente

        navios[0] = createSprite(R.drawable.navio, 1, 1);
        navios[0].setScreenPercent(20,12);
        navios[0].iMirror = AGSprite.HORIZONTAL;
        navios[0].vrDirection.fX = 1;
        navios[0].vrPosition.fX = -navios[0].getSpriteWidth() / 2;
        navios[0].vrPosition.fY = AGScreenManager.iScreenHeight -
                navios[0].getSpriteHeight() / 2 - barraSuperior.getSpriteHeight();

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

        //config sprite placa
        int multiplicador = 1;
        for (int pos =0; pos < placar.length; pos++)
        {

            placar[pos] = createSprite(R.drawable.fonte, 4, 4);
            placar[pos].setScreenPercent(10,10);
            placar[pos].vrPosition.fY = barraSuperior.vrPosition.fY;
            placar[pos].vrPosition.fX = 20 + multiplicador * placar[pos].getSpriteWidth();
            placar[pos].bAutoRender = false;
            multiplicador++;
            for (int i=0; i < 10; i++)
            {
                placar[pos].addAnimation(1, false, i);
            }
        }

        tempoCanhao = new AGTimer(100);
        efeitoCatraca = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");
        efeitoExplosao = AGSoundManager.vrSoundEffects.loadSoundEffect("explodenavio.wav");
        tempoBala = new AGTimer(300);



    }
    //Sobreescrita o metodo render para alterar a ordem de desenho
    public void render()
    {
        super.render();
        barraSuperior.render();
        for (int pos =0; pos < placar.length; pos++)
        {
            placar[pos].render();
        }
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
            //vrGameManager.setCurrentScene(1);
            //return;
            bPausa = !bPausa;
        }
        if(bPausa == false) {
            this.atualizaMovimentoCanhao();
            this.atualizaBalas();
            this.criaTiro();
            this.atualizaNavios();
            this.verificaColisaoBalasNavios();
            this.atualizaExplosoes();
            this.atualizaPlacar();
        }
    }

    private void criaExplosao(float x, float y)
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

        AGSprite novaExplosao = createSprite(R.drawable.explosao, 4, 2);
        novaExplosao.setScreenPercent(20, 12);
        novaExplosao.addAnimation(10, false, 0, 7);
        novaExplosao.vrPosition.fX = x;
        novaExplosao.vrPosition.fY = y;
        vetorExplosoes.add(novaExplosao);
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
                if(canhao.vrPosition.getX()  >= canhao.getSpriteWidth()/2) {
                    AGSoundManager.vrSoundEffects.play(efeitoCatraca);
                    canhao.vrPosition.setX(canhao.vrPosition.getX() - 10);
                }
            }
        }
    }

    //metodo para reciclar explosoes
    private void atualizaExplosoes(){
        for (AGSprite explosao : vetorExplosoes){
            if(explosao.getCurrentAnimation().isAnimationEnded()){
                explosao.bRecycled = true;
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

                    tempPontuacao += 50;
                    criaExplosao(navio.vrPosition.fX, navio.vrPosition.fY);
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
                            canhao.getSpriteWidth() /3 +
                                    bala.getSpriteHeight() / 2;
                    return;
                }
            }
            AGSprite novaBala = createSprite(R.drawable.bala, 1, 1);
            novaBala.setScreenPercent(8,5);
            novaBala.vrPosition.fX = canhao.vrPosition.fX;
            novaBala.vrPosition.fY =
                    canhao.getSpriteWidth() / 3 +
                            novaBala.getSpriteHeight() / 2;
            vetorTiros.add(novaBala);
        }
    }

    private void atualizaPlacar(){
        if (tempPontuacao > 0){
//            for (AGSprite digito : placar)
//            {
//                digito.bVisible = !digito.bVisible;
//            }
            tempPontuacao--;
            pontuacao++;
        }
//        else
//        {
//            for (AGSprite digito : placar)
//            {
//                digito.bVisible = true;
//            }
//        }

        placar[5].setCurrentAnimation(pontuacao % 10);
        placar[4].setCurrentAnimation((pontuacao % 100 ) / 10);
        placar[3].setCurrentAnimation((pontuacao % 1000 ) / 100);
        placar[2].setCurrentAnimation((pontuacao % 10000 ) / 1000);
        placar[1].setCurrentAnimation((pontuacao % 100000 ) / 10000);
        placar[0].setCurrentAnimation(pontuacao / 100000);
    }
}
