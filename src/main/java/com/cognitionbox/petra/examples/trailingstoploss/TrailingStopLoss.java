package com.cognitionbox.petra.examples.trailingstoploss;

import com.cognitionbox.petra.examples.trailingstoploss.view.Direction;
import com.cognitionbox.petra.examples.trailingstoploss.view.DirectionUpdated;
import com.cognitionbox.petra.examples.trailingstoploss.view.PriceUpdated;
import com.cognitionbox.petra.examples.trailingstoploss.view.StopLoss;

import static com.cognitionbox.petra.ast.interp.util.Program.sep;

public final class TrailingStopLoss {
    private final Direction direction = new Direction();
    private final StopLoss stopLoss = new StopLoss();
    private final PriceUpdated updatedView = new PriceUpdated();
    private final DirectionUpdated directionUpdated = new DirectionUpdated();

    public boolean noDirection(){return direction.none() && stopLoss.noChange() && directionUpdated.directionNotUpdated() && updatedView.priceNotUpdated();}

    public boolean buy(){return direction.buy() && stopLoss.noChange() && directionUpdated.directionUpdated() && updatedView.priceNotUpdated();}

    public boolean sell(){return direction.sell() && stopLoss.noChange() && directionUpdated.directionUpdated() && updatedView.priceNotUpdated();}

    public boolean noChange(){return (direction.buy() || direction.sell()) && stopLoss.noChange() && directionUpdated.directionNotUpdated() && updatedView.priceUpdated();}

    public boolean newMax(){return direction.buy() && stopLoss.newMax() && directionUpdated.directionNotUpdated() && updatedView.priceUpdated();}

    public boolean newMin(){return direction.sell() && stopLoss.newMin() && directionUpdated.directionNotUpdated() && updatedView.priceUpdated();}

    public boolean updatedBuyStop(){return direction.buy() && stopLoss.buyStopUpdated() && updatedView.priceNotUpdated();}

    public boolean updatedSellStop(){return direction.sell() && stopLoss.sellStopUpdated() && updatedView.priceNotUpdated();}

    public boolean hitStop(){return stopLoss.hitStop() && updatedView.priceUpdated();}

    public boolean stopped(){return stopLoss.stopped() && updatedView.priceNotUpdated();}

    public void selectBuy(){
        if (noDirection()){
            sep(()->{
                direction.selectBuy();},()->{directionUpdated.updateDirection();});
            assert(buy());
        }
    }

    public void selectSell(){
        if (noDirection()){
            sep(()->{
                direction.selectSell();},()->{directionUpdated.updateDirection();});
            assert(sell());
        }
    }

    public void update(){
        if (buy() || sell()){
            sep(()->{
                updatedView.updatePrice();},()->{directionUpdated.resetDirectionUpdate();});
            assert(newMax() || newMin() || noChange());
        } else if (newMax()){
            sep(()->{
                stopLoss.updateBuyStop();},()->{updatedView.resetPriceUpdate();});
            assert(updatedBuyStop());
        } else if (newMin()){
            sep(()->{
                stopLoss.updateSellStop();},()->{updatedView.resetPriceUpdate();});
            assert(updatedSellStop());
        } else if (hitStop()){
            sep(()->{
                stopLoss.stop();},()->{updatedView.resetPriceUpdate();});
            assert(stopped());
        }
    }
}
