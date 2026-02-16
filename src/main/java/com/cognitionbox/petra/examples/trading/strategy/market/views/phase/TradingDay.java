package com.cognitionbox.petra.examples.trading.strategy.market.views.phase;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.examples.trading.strategy.data.Singleton;
import com.cognitionbox.petra.examples.trading.strategy.order.Order;

public final class TradingDay {
  private final Order order = Singleton.get(Order.class);
  public boolean open(){return order.isOpen();}
  public boolean midday(){return order.isMidday();}
  public boolean close(){return order.isClose();}
  public boolean none(){return order.isNone();}
}
