package com.jpmc.pbs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionBook {

    @Test
    public void runDifferentValidScenarios() {

        PositionBookUtilities positionBookUtilities = new PositionBookUtilities();

        List<TradeEvent> tradeEventsList = new ArrayList<>();
        TradeEvent tradeEvent = new TradeEvent(1, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);
        TradeEvent tradeEvent2 = new TradeEvent(2, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 50);
        tradeEventsList.add(tradeEvent);
        tradeEventsList.add(tradeEvent2);
        positionBookUtilities.processTradeEvents(tradeEventsList);

        assertEquals("[ACC1\tSEC1\t150\t[id: 1,BUY,ACC1,SEC1,100, id: 2,BUY,ACC1,SEC1,50]\n" +
                "]", positionBookUtilities.fetchPostitions().toString(), "Incorrect calculation of current position");

        List<TradeEvent> tradeEventsList2 = new ArrayList<>();
        TradeEvent tradeEvent3 = new TradeEvent(3, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 12);
        TradeEvent tradeEvent4 = new TradeEvent(4, TradeOperations.BUY, new Account("ACC1"), new Securities("SECXYZ"), 50);
        TradeEvent tradeEvent5 = new TradeEvent(5, TradeOperations.BUY, new Account("ACC2"), new Securities("SECXYZ"), 33);
        TradeEvent tradeEvent6 = new TradeEvent(6, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 20);

        tradeEventsList2.add(tradeEvent3);
        tradeEventsList2.add(tradeEvent4);
        tradeEventsList2.add(tradeEvent5);
        tradeEventsList2.add(tradeEvent6);
        positionBookUtilities.processTradeEvents(tradeEventsList2);

        assertEquals(
                "[ACC1\tSECXYZ\t50\t[id: 4,BUY,ACC1,SECXYZ,50]\n" +
                        ", ACC2\tSECXYZ\t33\t[id: 5,BUY,ACC2,SECXYZ,33]\n" +
                        ", ACC1\tSEC1\t182\t[id: 1,BUY,ACC1,SEC1,100, id: 2,BUY,ACC1,SEC1,50, id: 3,BUY,ACC1,SEC1,12, id: 6,BUY,ACC1,SEC1,20]\n" +
                        "]", positionBookUtilities.fetchPostitions().toString(), "Incorrect calculation of current position");

        List<TradeEvent> tradeEventsList3 = new ArrayList<>();
        TradeEvent tradeEvent7 = new TradeEvent(10, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);
        TradeEvent tradeEvent8 = new TradeEvent(11, TradeOperations.SELL, new Account("ACC1"), new Securities("SEC1"), 50);
        tradeEventsList3.add(tradeEvent7);
        tradeEventsList3.add(tradeEvent8);
        positionBookUtilities.processTradeEvents(tradeEventsList3);
        assertEquals(
                "[ACC1\tSECXYZ\t50\t[id: 4,BUY,ACC1,SECXYZ,50]\n" +
                        ", ACC2\tSECXYZ\t33\t[id: 5,BUY,ACC2,SECXYZ,33]\n" +
                        ", ACC1\tSEC1\t232\t[id: 1,BUY,ACC1,SEC1,100, id: 2,BUY,ACC1,SEC1,50, id: 3,BUY,ACC1,SEC1,12, id: 6,BUY,ACC1,SEC1,20, id: 10,BUY,ACC1,SEC1,100, id: 11,SELL,ACC1,SEC1,50]\n" +
                        "]", positionBookUtilities.fetchPostitions().toString(), "Incorrect calculation of current position");


        List<TradeEvent> tradeEventsList4 = new ArrayList<>();
        TradeEvent tradeEvent9 = new TradeEvent(21, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);
        TradeEvent tradeEvent10 = new TradeEvent(21, TradeOperations.CANCEL, new Account("ACC1"), new Securities("SEC1"), 0);
        TradeEvent tradeEvent11 = new TradeEvent(22, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 5);
        tradeEventsList4.add(tradeEvent9);
        tradeEventsList4.add(tradeEvent10);
        tradeEventsList4.add(tradeEvent11);
        positionBookUtilities.processTradeEvents(tradeEventsList4);
        assertEquals(
                "[ACC1\tSECXYZ\t50\t[id: 4,BUY,ACC1,SECXYZ,50]\n" +
                        ", ACC2\tSECXYZ\t33\t[id: 5,BUY,ACC2,SECXYZ,33]\n" +
                        ", ACC1\tSEC1\t237\t[id: 1,BUY,ACC1,SEC1,100, id: 2,BUY,ACC1,SEC1,50, id: 3,BUY,ACC1,SEC1,12, id: 6,BUY,ACC1,SEC1,20, id: 10,BUY,ACC1,SEC1,100, id: 11,SELL,ACC1,SEC1,50, id: 21,BUY,ACC1,SEC1,100]\n" +
                        "]", positionBookUtilities.fetchPostitions().toString(), "Incorrect calculation of current position");


    }

    @Test
    public void runInvalidDataScenarios() {

        PositionBookUtilities positionBookUtilities = new PositionBookUtilities();

        /*List<TradeEvent> tradeEventsList = new ArrayList<>();
        TradeEvent tradeEvent = new TradeEvent(1, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);
        TradeEvent tradeEvent2 = new TradeEvent(2, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 50);
        tradeEventsList.add(tradeEvent);
        tradeEventsList.add(tradeEvent2);
        positionBookUtilities.processTradeEvents(tradeEventsList);
*/
        assertEquals("[]", positionBookUtilities.fetchPostitions().toString(), "Incorrect calculation of current position");

    }
}
