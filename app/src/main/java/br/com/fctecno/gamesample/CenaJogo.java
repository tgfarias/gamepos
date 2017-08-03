package br.com.fctecno.gamesample;

import java.util.ArrayList;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGInputManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
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
    AGSprite lula = null;
    AGSprite fimJogo = null;
    AGSprite[] seguranca = new AGSprite[7];
    AGSprite btnVoltar = null;

    boolean bPausa = false;


    CenaJogo(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }
    @Override
    public void init()
    {

        vetorTiros = new ArrayList<AGSprite>();
        vetorExplosoes = new ArrayList<AGSprite>();
        planoFundo = createSprite(R.drawable.bg, 1, 1);
        planoFundo.setScreenPercent(100,100);
        planoFundo.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        planoFundo.vrPosition.setY(AGScreenManager.iScreenHeight /2 );

        createSprite(R.drawable.constituicao, 1, 1).bVisible=false;// Carrega imagem na memoria
        createSprite(R.drawable.explosao, 4,2).bVisible = false; // carrega explosao na memoria

        barraSuperior = createSprite(R.drawable.barrasuperior, 1, 1); // sprite sem animação (1,1)
        barraSuperior.setScreenPercent(100,10);
        barraSuperior.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        barraSuperior.vrPosition.fY = AGScreenManager.iScreenHeight - barraSuperior.getSpriteHeight() / 2;
        barraSuperior.bAutoRender = false; // Motor não desenha automaticamente

        btnVoltar = this.createSprite(R.drawable.btnvoltar, 1,1);
        btnVoltar.setScreenPercent(20, 10);
        // btnVoltar.vrPosition.fY = barraSuperior.vrPosition.fY;
        btnVoltar.vrPosition.setX(AGScreenManager.iScreenWidth - btnVoltar.getSpriteWidth()/2);
        btnVoltar.vrPosition.setY(barraSuperior.vrPosition.fY);
        btnVoltar.bAutoRender = false;

        lula = createSprite(R.drawable.lula, 1, 1);
        lula.setScreenPercent(20,12);
        lula.vrDirection.fX = 1;
        lula.vrPosition.fX = -lula.getSpriteWidth() / 2;
        lula.vrPosition.fY = AGScreenManager.iScreenHeight - lula.getSpriteHeight() / 2 - barraSuperior.getSpriteHeight();


        seguranca[0] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[0].setScreenPercent(20,12);
        //seguranca[0].iMirror = AGSprite.HORIZONTAL;
        seguranca[0].vrDirection.fX = 1;
        seguranca[0].vrPosition.fX = -seguranca[0].getSpriteWidth() / 4;
        seguranca[0].vrPosition.fY = AGScreenManager.iScreenHeight -
                seguranca[0].getSpriteHeight() / 2 - (2 * lula.getSpriteHeight());

        seguranca[1] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[1].setScreenPercent(20, 12);
        seguranca[1].vrDirection.fX = -1;

        seguranca[1].vrPosition.fX = AGScreenManager.iScreenWidth +
                seguranca[1].getSpriteWidth() / 4;

        seguranca[1].vrPosition.fY = lula.vrPosition.fY -
                seguranca[1].getSpriteHeight();

        seguranca[2] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[2].setScreenPercent(20, 12);
        seguranca[2].vrDirection.fX = -1;
        seguranca[2].iMirror = AGSprite.HORIZONTAL;
        seguranca[2].vrPosition.fX = AGScreenManager.iScreenWidth + AGScreenManager.iScreenWidth +
                seguranca[2].getSpriteWidth() / 4;

        seguranca[2].vrPosition.fY = lula.vrPosition.fY -
                seguranca[2].getSpriteHeight();


        seguranca[3] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[3].setScreenPercent(20, 12);
        seguranca[3].vrDirection.fX = -1;

        seguranca[3].vrPosition.fX = ( 3 * AGScreenManager.iScreenWidth) +
                seguranca[3].getSpriteWidth() / 4;

        seguranca[3].vrPosition.fY = lula.vrPosition.fY -
                seguranca[3].getSpriteHeight();

        //Segunda faixa de seguranças

        seguranca[4] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[4].setScreenPercent(20, 12);
        seguranca[4].vrDirection.fX = -1;

        seguranca[4].vrPosition.fX = ( 4 * AGScreenManager.iScreenWidth) +
                seguranca[4].getSpriteWidth() / 4;

        seguranca[4].vrPosition.fY = seguranca[0].vrPosition.fY -
                seguranca[4].getSpriteHeight();


        seguranca[5] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[5].setScreenPercent(20, 12);
        seguranca[5].vrDirection.fX = -1;

        seguranca[5].vrPosition.fX = ( 4 * AGScreenManager.iScreenWidth) +
                seguranca[5].getSpriteWidth() / 4;

        seguranca[5].vrPosition.fY = seguranca[0].vrPosition.fY -
                seguranca[5].getSpriteHeight();

        seguranca[6] = createSprite(R.drawable.advogadoperson, 1, 1);
        seguranca[6].setScreenPercent(20, 12);
        seguranca[6].vrDirection.fX = -1;

        seguranca[6].vrPosition.fX = ( 4 * AGScreenManager.iScreenWidth) +
                seguranca[6].getSpriteWidth() / 4;

        seguranca[6].vrPosition.fY = seguranca[0].vrPosition.fY -
                seguranca[6].getSpriteHeight();

        canhao = createSprite(R.drawable.morocomconstituicao, 1, 1);
        canhao.setScreenPercent(20, 20);
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

        fimJogo = createSprite(R.drawable.morocomconstituicao, 1, 1);
        fimJogo.setScreenPercent(60,60);
        fimJogo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        fimJogo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        fimJogo.bVisible=false;

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
        btnVoltar.render();
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
            //bPausa = !bPausa;
        }
        if(bPausa == false) {
            this.atualizaMovimentoMoro();
            this.atualizaBalas();
            this.atualizaPosicaoLula();
            this.criaTiro();
            this.atualizaPosicoes();
            this.verificaColisaoBalasSegurancas();
            this.verificaColisaoComLula();
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

    //movimentacao do lula
    private void atualizaPosicaoLula(){

        lula.vrPosition.fX +=  5 * lula.vrDirection.fX;

            if(lula.vrDirection.fX == 1)
            {
                if(lula.vrPosition.fX >=
                        AGScreenManager.iScreenWidth //+
                    //seg.getSpriteWidth() / 2
                        )
                {
                    lula.iMirror = AGSprite.NONE;
                    lula.vrDirection.fX = -1;
                }
            }
            else
            {
                if(lula.vrPosition.fX <= -lula.getSpriteWidth() / 2 ){
                    lula.iMirror = AGSprite.HORIZONTAL;
                    lula.vrDirection.fX = 1;
                }
            }

    }

    //atualizando a posicao dos navios
    private void atualizaPosicoes(){
        for (AGSprite seg : seguranca)
        {
            seg.vrPosition.fX +=  5 * seg.vrDirection.fX;

            if(seg.vrDirection.fX == 1)
            {
                if(seg.vrPosition.fX >=
                        AGScreenManager.iScreenWidth //+
                                //seg.getSpriteWidth() / 2
                        )
                {
                    seg.iMirror = AGSprite.NONE;
                    seg.vrDirection.fX = -1;
                }
            }
            else
            {
                if(seg.vrPosition.fX <= -seg.getSpriteWidth() / 2 ){
                    seg.iMirror = AGSprite.HORIZONTAL;
                    seg.vrDirection.fX = 1;
                }
            }
        }
    }

    private void atualizaMovimentoMoro(){
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

    private void verificaColisaoBalasSegurancas(){
        for (AGSprite bala : vetorTiros){
            if(bala.bRecycled)
                continue;

            for (AGSprite seg : seguranca)
            {
                if( bala.collide(seg)){

                    tempPontuacao += 50;
                    criaExplosao(seg.vrPosition.fX, seg.vrPosition.fY);
                    bala.bRecycled = true;
                    bala.bVisible = false;

                    AGSoundManager.vrSoundEffects.play(efeitoExplosao);
                    if(seg.vrDirection.fX == 1) {
                        seg.vrDirection.fX = -1;
                        seg.iMirror = AGSprite.NONE;
                        seg.vrPosition.fX = AGScreenManager.iScreenWidth + seg.getSpriteWidth() / 2;
                    }
                    else {
                        seg.vrDirection.fX = 1;
                        seg.iMirror = AGSprite.HORIZONTAL;
                        seg.vrPosition.fX = -seg.getSpriteWidth();
                    }
                    break;
                }
            }
        }
    }


    private void verificaColisaoComLula(){
        for (AGSprite bala : vetorTiros){
            if(bala.bRecycled)
                continue;

            if( bala.collide(lula)){

                tempPontuacao += 1000;
                criaExplosao(lula.vrPosition.fX, lula.vrPosition.fY);
                bala.bRecycled = false;
                bala.bVisible = false;

                AGSoundManager.vrSoundEffects.play(efeitoExplosao);
//                if(lula.vrDirection.fX == 1) {
//                    lula.vrDirection.fX = -1;
//                    lula.iMirror = AGSprite.NONE;
//                    lula.vrPosition.fX = AGScreenManager.iScreenWidth + lula.getSpriteWidth() / 2;
//                }
//                else {
//                    lula.vrDirection.fX = 1;
//                    lula.iMirror = AGSprite.HORIZONTAL;
//                    lula.vrPosition.fX = -lula.getSpriteWidth();
//                }

                limparTela();
                fimJogo.bVisible = true;
                bPausa = true;
                break;
            }


        }

    }

    private void limparTela()
    {
        for (AGSprite b : vetorTiros) {
            b.bRecycled = false;
            b.bVisible = false;
        }

        for (AGSprite seg : seguranca)
        {
            seg.bVisible =false;
        }
        canhao.bVisible = false;
        lula.bVisible = false;

    }

    private void atualizaBalas(){
        float aux = 25.0f;
        for (AGSprite bala :  vetorTiros) {

            bala.vrPosition.fY += 10;

            //BALA SAIU DA TELA

            if(bala.vrPosition.fY > AGScreenManager.iScreenHeight + bala.getSpriteHeight() / 2)
            {
                bala.fAngle = aux;
                bala.bRecycled = true;
                bala.bVisible = false;
            }
            aux += 25.0f;
        }
    }

    private void criaTiro(){

        tempoBala.update();
        float ang = 0.0f;
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
                    bala.fAngle = ang;

                    bala.vrPosition.fX = canhao.vrPosition.fX + 60;
                    bala.vrPosition.fY = canhao.getSpriteWidth() / 3 + bala.getSpriteHeight() / 2;
                    return;
                }
                ang += 30.0f;
            }
            AGSprite novaBala = createSprite(R.drawable.constituicao, 1, 1);
            novaBala.setScreenPercent(8,5);
            novaBala.fAngle = ang;

            novaBala.vrPosition.fX = canhao.vrPosition.fX + 60;
            novaBala.vrPosition.fY = canhao.getSpriteWidth() / 3 + novaBala.getSpriteHeight() / 2;
            vetorTiros.add(novaBala);
        }
    }

    private void atualizaPlacar(){
        if (tempPontuacao > 0){
            tempPontuacao--;
            pontuacao++;
        }
        placar[5].setCurrentAnimation(pontuacao % 10);
        placar[4].setCurrentAnimation((pontuacao % 100 ) / 10);
        placar[3].setCurrentAnimation((pontuacao % 1000 ) / 100);
        placar[2].setCurrentAnimation((pontuacao % 10000 ) / 1000);
        placar[1].setCurrentAnimation((pontuacao % 100000 ) / 10000);
        placar[0].setCurrentAnimation(pontuacao / 100000);
    }
}
