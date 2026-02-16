package com.cognitionbox.petra.examples.trading.strategy.market.views.sma;

import com.cognitionbox.petra.examples.trading.strategy.data.Singleton;
import com.cognitionbox.petra.examples.trading.strategy.order.Order;

public final class MovingAverage {
    private final Order order = Singleton.get(Order.class);
    public boolean midAboveSma(){return order.midAboveSma();}
    public boolean midBelowSma(){return order.midBelowSma();}
    public boolean midEqualSma() {return order.midEqualSma();}
}