**User guide for Position Book System:**<br />

1. Trade object events are passed to this application which is currently done manually as shown in test class. These objects can be taken/formed from file/DB as a source in realtime based application.
   <br /><br />One of the example is: 
<br />
`new TradeEvent(1, TradeOperations.BUY, new Account("ACC1"), new Securities("SEC1"), 100);`
   <br /><br />
2. These trades events are then processed via application and then maintained in a runtime collection with no modifications to it
    Method used here:<br /><br /> Method: `processTradeEvents(List<TradeEvent> tradeEventsList)`
   <br /><br />
3. At any moment if a user wants to get the realtime position of a trades, 
the trades are calculated according to the activity against each trade and final result is provided.
   <br /><br />
    Method: `fetchPostitions()`
   <br /><br />
4. A client class with one set of example is provided here to run.
