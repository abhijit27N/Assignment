"# Assignment" 

Publisher - This is a rest client publishing periodic price data to Consumer. This calls the WS exposed by consumer and pushes pricing data.

Pricing Consumer - This SpringBoot application has 2 parts.
a) Exposes a Rest WS for publisher to publish price data and then stores it in MongoDB.
b) Exposes another WS for the UI to fetch latest avg price data. It accepts the number of last prices to be looked into from UI, and then fetches data from MongoDB and returns an average.

WebAppPricing - This is another SpringBoot web application which accepts the  user pricing inputs and calls the WS exposed by Consumer.
It diplays the avg price data returned.

**** Additional Questions ***** no code

- if the dataset size and calculation requirements were significantly higher, would you do things differently?

a) If data-set size would be higher it is preferred to use the time-series db like KDB+, Influx Data. These are specifically designed to handle high volume time-series data.

b) If calculation requirements would be higher, the system should distributed, load balanced and stateless. Having the core calculation engine stateless makes it salable.
A micro services  architecture with services dedicated to specific flows would make the system salable and easy to manage independently.

c) Also option could be to use No-SQL like Cassandra and MongoDB as they easily scale horizontally and are also in memory and reliable stores.

d) We could be to use a distribute caching system like Cassandra , GigaSpaces. These could be holding the most recent data published whereas the archived data would be stored in a back end systems like time-series db  or no sqls.

- how would you handle the monitoring of the system?

a) For the Infrastructure monitoring operating system, web server, app server and database server would prefer to use tools like Hyperic HQ, Nagios, Zabbix.
For Application monitoring would prefer to use tools like New Relic, AppDynamics, Compuware APM & Boundary

b) Also would build custom tools for monitoring memory footprints, latency and throughput of the systems. These would be sending out alerts and periodic health checks.

c) Also would write or use Log monitoring tools like - Splunk,LogRhythm. The application/Dev-ops team would write the trigger points and decide the severity levels.
