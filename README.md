# Broker

This API connects partner brokers to the main insurer, generating policies according to the given information. In the authenticated area, the broker has full access to quotes and policies creation tools.

## Installing

**Broker** is set to run with Docker Compose. With this source code in your machine and local Postgres (:5432) inactive, build the container that is in the project's root following along this commands:

```bash
docker compose build 
```

```bash
docker compose up -d
```

The container's shell can be accessed by:

```bash
docker exec -it broker-clj-app-1 bash
```

Run Postgres *cli* and see how's our database going:

```bash
docker exec -it broker-clj-db-1 psql -U postgres
\c postgres
SELECT * FROM brokers;
```

There is one container for unitary testing that runs automatically whenever it is up.

## Requests

Access the endpoints through the following routes and parameters:

`POST http://localhost:3000/brokers`
```json
{
  "first_name": "Ruby",
  "last_name" : "Tuesday"
}
```
_RESPONSE_
```json
{
 "broker_id": "71161e53-3c4d-45a5-b499-18650715b995",
 "created_at": "2023-04-10T18:00:41Z",
 "first_name": "Ruby",
 "last_name": "Tuesday"
}
```

`POST http://localhost:3000/:broker_id/quotes`
```json
{
 "age": 29,
 "sex": "f"
}
```
_RESPONSE_
```json
{
 "quotation_id": "515341a1-a5b8-4af3-915d-931deeb7e919",
 "broker_id": "71161e53-3c4d-45a5-b499-18650715b995",
 "created_at": "2023-04-10T18:03:28Z",
 "expire_at": "2023-04-10",
 "price": 86.26
}
```

`POST http://localhost:3000/:broker_id/policies`
```json
{
 "quotation_id": "515341a1-a5b8-4af3-915d-931deeb7e919",
 "name": "Mighty Maria",
 "sex": "f",
 "date_of_birth": "1993-06-26"
}
```
_RESPONSE_
```json
{
 "policy_id": "307ff969-f56c-47dd-b0a3-f2d62ed45e87",
 "broker_id": "71161e53-3c4d-45a5-b499-18650715b995",
 "created_at": "2023-04-10T18:04:31Z",
 "date_of_birth": "1993-06-26",
 "name": "Mighty Maria",
 "sex": "f"
}
```

`GET http://localhost:3000/brokers/:broker_id/policies?policy_id=policy_id` <br>
_RESPONSE_
```json
{
 "quotation_id": "515341a1-a5b8-4af3-915d-931deeb7e919",
 "name": "Mighty Maria",
 "sex": "f",
 "date_of_birth": "1993-06-26",
 "id": "307ff969-f56c-47dd-b0a3-f2d62ed45e87"
}
```