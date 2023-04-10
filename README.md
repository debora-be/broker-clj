# Detalhes
**Broker** é uma API que conecta **Parceiras Corretoras** à **Seguradora**, tornando possível gerar apólices de acordo com as informações postadas. Após autenticação, a Parceira Corretora (Broker) tem acesso à área de criação de cotações e apólices.
<br></br>
A documentação dos _endpoints_ fornece detalhes sobre os parâmetros e _payloads_ de todas as etapas de autenticação e criação, possibilitando testar as requisições em tempo real.
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

**POST** `http://localhost:3000/brokers` </br>
Content-Type: application/json
```json
{
  "first_name": "Ruby",
  "last_name" : "Tuesday"
}
```

**POST** `http://localhost:3000/:broker_id/quotes` </br>
Content-Type: application/json
```json
{
  "age": 52,
  "sex": "F"
}
```

**POST** `http://localhost:3000/:broker_id/policies` </br>
Content-Type: application/json
```json
{
  "quotation_id":  "8a964dfa-4640-4ad4-824c-20ab097a983f",
  "name": "Ten",
  "sex": "f",
  "date_of_birth": "1987-01-01"
}
```

**GET** `http://localhost:3000/brokers/:broker_id/policies?policy_id=policy_id` </br>
Content-Type: application/json </br>
query_params = policy_id
<br></br>

# Extras
Acesso à linha de comando pelo Docker:
</br>
<br>`docker exec -it broker-clj-app-1 bash` </br>
</br>
Os testes unitários são rodados automaticamente quando o contêiner sobe (_docker compose up_); dessa mesma forma é criada a _database_, totalizando três contêineres.
