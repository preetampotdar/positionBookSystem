package com.jpmc.pbs;

import java.util.List;

public class OutputPosition {
    private Account account;
    Securities securities;
    int finalQuantity;
    List<TradeEvent> tradeEvents;

    public OutputPosition(Account account, Securities securities, int finalQuantity, List<TradeEvent> tradeEvents) {
        this.account = account;
        this.securities = securities;
        this.finalQuantity = finalQuantity;
        this.tradeEvents = tradeEvents;
    }

    public int getFinalQuantity() {
        return finalQuantity;
    }

    public void setFinalQuantity(int finalQuantity) {
        this.finalQuantity = finalQuantity;
    }

    public List<TradeEvent> getTradeEvents() {
        return tradeEvents;
    }

    public void setTradeEvents(List<TradeEvent> tradeEvents) {
        this.tradeEvents = tradeEvents;
    }

    @Override
    public String toString() {
        return account.getAccountName() + "\t" + securities.getSecurityName() + "\t" + finalQuantity + "\t" + tradeEvents + "\n";
    }
}
