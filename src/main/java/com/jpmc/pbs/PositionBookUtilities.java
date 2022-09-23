package com.jpmc.pbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionBookUtilities {

    private static Map<String, List<TradeEvent>> tradeStoreMap;

    PositionBookUtilities() {
        tradeStoreMap = new HashMap<>();
    }

    public List<OutputPosition> fetchPostitions() {
        List<OutputPosition> outputPositionList = new ArrayList<>();
        for (List<TradeEvent> tradeEvents: tradeStoreMap.values()) {
            OutputPosition outputPosition = null;

            if (tradeEvents != null) {
                TradeEvent previousTradeEvent = null;
                for (TradeEvent tradeEvent : tradeEvents) {
                    outputPosition = performOperation(tradeEvent, outputPosition, previousTradeEvent);
                }
                if (outputPosition.getTradeEvents()==null) {
                    outputPosition.setTradeEvents(tradeEvents);
                }
                outputPositionList.add(outputPosition);
            }
        }

        return outputPositionList;
    }

    public OutputPosition performOperation(TradeEvent tradeEvent, OutputPosition outputPosition, TradeEvent previousTrade) {

        if (outputPosition == null) {
            outputPosition = new OutputPosition(tradeEvent.getAccount(), tradeEvent.getSecurities(), tradeEvent.getNumberOfSecuritiesToTrade(), null);
        } else if (tradeEvent.getTradeOperations() == TradeOperations.BUY){
            outputPosition.setFinalQuantity(outputPosition.getFinalQuantity() + tradeEvent.getNumberOfSecuritiesToTrade());
        } else if (tradeEvent.getTradeOperations() == TradeOperations.SELL) {
            outputPosition.setFinalQuantity(outputPosition.getFinalQuantity() - tradeEvent.getNumberOfSecuritiesToTrade());
        } else {
            List<TradeEvent> tradeEventList = new ArrayList<>();
            TradeEvent previousTradeEventToAdjust = removeTradeEvent(tradeEvent, tradeEventList);
            outputPosition.setTradeEvents(tradeEventList);

            outputPosition.setFinalQuantity(outputPosition.getFinalQuantity() - previousTradeEventToAdjust.getNumberOfSecuritiesToTrade());
        }
        previousTrade = tradeEvent;
        return outputPosition;
    }

    public TradeEvent removeTradeEvent(TradeEvent tradeEvent, List<TradeEvent> tradeEventList) {
        for (TradeEvent previoustradeEvent: tradeStoreMap.get(tradeEvent.getAccount().getAccountName() + tradeEvent.getSecurities().getSecurityName())) {
            tradeEventList.add(previoustradeEvent);
            if (previoustradeEvent.getId() == tradeEvent.getId()) {
                tradeEventList.remove(tradeEvent);
                return previoustradeEvent;
            }
        }
        return null;
    }

    public void processTradeEvents(List<TradeEvent> tradeEventsList) {
        for (TradeEvent tradeEvent: tradeEventsList) {
            persistTradeEvent(tradeEvent);
        }
    }

    public void persistTradeEvent(TradeEvent tradeEvent) {
        List<TradeEvent> tradeEvents = new ArrayList<>();

        if (tradeStoreMap.containsKey(tradeEvent.getAccount().getAccountName() + tradeEvent.getSecurities().getSecurityName())) {
            tradeEvents = tradeStoreMap.get(tradeEvent.getAccount().getAccountName() + tradeEvent.getSecurities().getSecurityName());
        }
        tradeEvents.add(tradeEvent);
        tradeStoreMap.put(tradeEvent.getAccount().getAccountName() + tradeEvent.getSecurities().getSecurityName(), tradeEvents);
    }

}