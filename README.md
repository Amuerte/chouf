
<img src="/doc/chouf.png" width="100">

Chouf
======

> Chouf (From Arabic شاف,  « to see »).  
>   1. *n* (slang) a cop watcher in the drug smuggling business
>   2. *n* A fake and tiny alerting system for real estate 

## Run

```bash
docker-compose up -d

./gradlew bootRun
```

## How to perform an end to end test ?

The docker-compose services must be up. At the moment, it is starting 2 services:
- a Postgres database
- a Kafka broker (plaintext)

The database is initialized with test data located in the `.docker` directory

```
├── .docker                                  
│   └── provision                    
│       └── postgre                       
│           └── init                                                                                   
│               ├── 00_create_database_and_user.sh
│               └── 01_create_tables_and_data.sh
```

### Using the API

The application exposes an API (see [OpenAPI definition](/doc/openapi.yaml)) that enables to retreive test datas from the database
and to simulate the publication of an `Offer published` event.

#### Viewing data

```
# Fetch offers
curl http://localhost:8080/api/v1/offer

# Fetch alerts
curl http://localhost:8080/api/v1/alert
```

#### Produce an Offer Published event

```bash
curl -X POST http://localhost:8080/api/v1/offer/2/publish
```

And you should see the following output in the logs.

```text
c.c.a.i.event.OfferPublishedProducer     : Producing Offer Published event OfferPublishedEvent[id=39e1524a-e371-4285-8f4a-ea776362b0a7, offerId=2]
c.c.a.i.event.OfferPublishedConsumer     : Consuming published offer event OfferPublishedEvent[id=39e1524a-e371-4285-8f4a-ea776362b0a7, offerId=2]
c.c.a.i.event.OfferPublishedConsumer     : Publishing Alert Send event AlertSendingEvent[id=7fa197a1-5a31-46be-8bdd-d9217238870f, alertId=1, offerId=2, customerId=1]
c.c.a.i.event.OfferPublishedConsumer     : Publishing Alert Send event AlertSendingEvent[id=a9362b69-208e-4daa-818b-21af395d8dc0, alertId=4, offerId=2, customerId=2]
```

## Architecture

![logo](/doc/architecture.png)

This project enables to decide if user notifications should be sent when an offer is published.
We decided to create an event based system that would be componed of : 
- an alert finder, whose goal is to find which users should be notify when an offer is published.
- an alert sender, whose goal is to send the notifications, depending on the channels available (email, sms, etc).

This project focus on the component `alert finder`.

### Data model

![logo](/doc/model.png)

