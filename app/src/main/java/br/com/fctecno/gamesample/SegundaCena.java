package br.com.fctecno.gamesample;

import br.com.fctecno.andgraph.AGGameManager;
import br.com.fctecno.andgraph.AGScene;
import br.com.fctecno.andgraph.AGScreenManager;
import br.com.fctecno.andgraph.AGSprite;

/**
 * Created by catolica2017 on 10/06/17.
 */
public class SegundaCena extends AGScene {
    AGSprite bulldog = null;

    SegundaCena(AGGameManager gerenteJogo){
        super(gerenteJogo);
    }

    @Override
    public void init() {
        setSceneBackgroundColor(0.0f, 0.0f, 0.0f);

        bulldog = createSprite(R.mipmap.ic_budogg, 4, 4);
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

    }
}
