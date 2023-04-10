# Detalhes
**Broker** é uma API que conecta **Parceiras Corretoras** à **Seguradora**, tornando possível gerar apólices de acordo com as informações postadas. Após autenticação, a Parceira Corretora (Broker) tem acesso à área de criação de cotações e apólices.
<br></br>
# Funcionamento
Com o conteúdo do projeto salvo localmente,
</br> 
<br>`git clone git@github.com:debora-be/broker-clj.git`</br>
</br>
`cd broker-clj`</br>
</br>
Adicionamos o arquivo `profiles.clj` à pasta raiz do projeto. Se o seu Postgres estiver localmente conectado à porta :5432, será necessário desabilitá-lo com o comando:
</br>
<br>`systemctl stop postgresql`</br>
</br>
Com todos estes pré-requisitos, montamos a imagem no Docker:
</br>
<br>`docker compose build`</br>
</br>
Para acessar a API Broker de agora em diante, simplesmente subimos o contêiner:
</br>
<br>`docker compose up`</br>
</br>
# Requisições
Podemos acessar os _endpoints_ através de algum _client_ - como Insomnia, Postman ou Curl - através das rotas:
<br></br>

**POST** </br>
`http://localhost:3000/brokers` </br>
Content-Type: application/json
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

**POST** </br>
`http://localhost:3000/:broker_id/quotes` </br>
Content-Type: application/json
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

**POST** </br>
`http://localhost:3000/:broker_id/policies` </br>
Content-Type: application/json
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

**GET** </br>
`http://localhost:3000/brokers/:broker_id/policies?policy_id=policy_id` </br>
Content-Type: application/json </br>
query_params = policy_id
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
</br>

# Extras
Acesso à linha de comando pelo Docker:
</br>
<br>`docker exec -it broker-clj-app-1 bash` </br>
</br>
Postgres pela linha de comando:
</br>
<br>`docker exec -it broker-clj-db-1 psql -U postgres` </br>
</br>
Conectar à _database_ dentro do Postgres:
</br>
<br>`\c postgres` </br>
</br>
Os testes unitários são rodados automaticamente quando o contêiner sobe (_docker compose up_); dessa mesma forma é criada a _database_, totalizando três contêineres.
