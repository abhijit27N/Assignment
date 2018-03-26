"# Assignment" 

Publisher - This is a rest client publishing periodic price data to Consumer. This calls the WS exposed by consumer and pushes pricing data.

Pricing Consumer - This SpringBoot application has 2 parts.
a) Exposes a Rest WS for publisher to publish price data and then stores it in MongoDB.
b) Exposes another WS for the UI to fetch latest avg price data. It accepts the number of last prices to be looked into from UI, and then fetches data from MongoDB and returns an average.

WebAppPricing - This is another SpringBoot web application which accepts the  user pricing inputs and calls the WS exposed by Consumer.
It diplays the avg price data returned.
