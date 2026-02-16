package com.cognitionbox.petra.examples.trading.strategy;


import com.cognitionbox.petra.examples.trading.strategy.data.Singleton;
import com.cognitionbox.petra.examples.trading.strategy.order.Order;


public final class MarketData {
    private final Order order = Singleton.get(Order.class);
    public boolean updated() {return order.quoteUpdated();}
    public boolean cleared() {return order.quoteReset();}

    public void update() {
        if (cleared()){
            order.updateMarketData();
            assert(updated());
        }
    }

    public void clear() {
        if (updated()){
            order.resetMarketData();
            assert(cleared());
        }
    }
}
