package br.com.fctecno.gamesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.fctecno.andgraph.AGActivityGame;

public class PrincipalActivity extends AGActivityGame{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        init(this, false);

        SplashScreenCena splash = new SplashScreenCena(this.vrManager);
        MenuCena menuCena = new MenuCena(this.vrManager);
        CreditosCena creditos = new CreditosCena(this.vrManager);

        PrimeiraCena cena1 = new PrimeiraCena(this.vrManager);
        SegundaCena cena2 = new SegundaCena(this.vrManager);


        vrManager.addScene(splash);
        vrManager.addScene(menuCena);
        vrManager.addScene(creditos);
        vrManager.addScene(cena1);
        vrManager.addScene(cena2);
    }
}
