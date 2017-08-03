package br.com.fctecno.gamesample;

import android.os.Bundle;

import br.com.fctecno.andgraph.AGActivityGame;
import br.com.fctecno.andgraph.AGGameManager;

public class PrincipalActivity extends AGActivityGame{



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        init(this, true);

        SplashScreenCena splash = new SplashScreenCena(this.vrManager);
        SplashScreenCenaSair splashsair = new SplashScreenCenaSair(this.vrManager);
        MenuCena menuCena = new MenuCena(this.vrManager);
        CreditosCena creditos = new CreditosCena(this.vrManager);
        CenaJogo cena = new CenaJogo(this.vrManager);



        vrManager.addScene(splash);
        vrManager.addScene(menuCena);
        vrManager.addScene(creditos);
        vrManager.addScene(cena);
        vrManager.addScene(splashsair);
    }
}
