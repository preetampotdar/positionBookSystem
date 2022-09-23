package com.jpmc.pbs;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        PositionBookUtilities positionBookUtilities = new PositionBookUtilities();

        List<TradeEvent> tradeEventsList = new ArrayList<>();
        TradeEvent tradeEvent = new TradeEvent(1, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);
        TradeEvent tradeEvent2 = new TradeEvent(2, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 50);
        tradeEventsList.add(tradeEvent);
        tradeEventsList.add(tradeEvent2);
        positionBookUtilities.processTradeEvents(tradeEventsList);

        System.out.println("Realtime state of position book utility based on data entered above is \n" +
                positionBookUtilities.fetchPostitions());
    }
}
