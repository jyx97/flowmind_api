# 🌿 FlowMind API — Saúde Mental e Rotinas Inteligentes para o Trabalho Moderno  

O **FlowMind** é uma plataforma voltada para **bem-estar emocional, autocuidado e produtividade**, desenvolvida para auxiliar pessoas que trabalham:

- **presencialmente**,  
- **de forma híbrida**, ou  
- **remotamente**.

A ideia central do FlowMind é ajudar o usuário a manter uma rotina equilibrada mesmo em cenários de alta demanda mental. A solução combina:

- **Check-ins emocionais diários**  
- **Análise automática dos dados**  
- **Geração de rotina personalizada por IA**  
- **Chatbot inteligente para suporte**  

A API deste repositório é responsável por toda a **lógica de negócios, integração com IA e persistência dos dados**.

---

## 🎯 Objetivo do FlowMind

Em um contexto onde muitos profissionais enfrentam cansaço mental, falta de organização diária, dificuldade de equilibrar vida pessoal e profissional e jornadas longas…

O **FlowMind** atua como um assistente de bem-estar.

🧠 A IA lê padrões emocionais dos últimos 7 dias  
📅 Cria rotinas personalizadas  
💬 Conversa com o usuário para suporte e organização  
✨ Ajuda a prevenir sobrecarga mental  

Tudo isso projetado para **promover equilíbrio**, reduzir estresse e melhorar a produtividade de forma saudável.

---
## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Spring AI (OpenAI)**
- **PostgreSQL**
- **Swagger (SpringDoc)**

---

## 🗂️ Estrutura da API

| Módulo     | Função                                    |
|------------|-------------------------------------------|
| `auth`     | Cadastro e login                          |
| `checkin`  | Registra humor/energia/sono               |
| `rotina`   | Gerencia rotinas geradas pela IA          |
| `chat`     | IA conversacional + geração de rotina     |
| `usuario`  | Dados do usuário                          |
| `ai`       | Integração Spring AI                      |
| `swagger`  | Documentação da API                       |

---

## 🛢️ Banco de Dados - Configuração PostgreSQL

Crie o banco:

```sql
CREATE DATABASE equilibrio_db;
````

Configurar no **application.properties**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/equilibrio_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.ai.openai.api-key=SUA_API_KEY
```
---

## 📌 Como Rodar

```bash
./gradlew bootRun
```

Swagger:

```bash
http://localhost:8080/swagger-ui/index.html
```
---

# 🔐 Autenticação

### ➤ Cadastro

**POST `/auth/cadastrar`**

```json
{
  "nome": "Ana",
  "email": "ana9767@gmail.com",
  "senha": "1234"
}
```

### ➤ Login

**POST `/auth/login`**

```json
{
  "email": "ana9767@gmail.com",
  "senha": "1234"
}
```

Retorna o usuário se estiver correto.

---

# 📝 Check-in Diário

### Criar check-in

**POST `/checkin`**

```json
{
  "idUsuario": 1,
  "humor": 7,
  "energia": 6,
  "sono": 8
}
```

### Listar por usuário

**GET `/checkin/usuario/1`**

---

# 🧠 Chatbot

### Conversar

**POST `/chat`**

```json
"Olá, preciso organizar meu dia."
```
A resposta já vem com suporte emocional + sugestão de rotina quando necessário.

---

# 🕒 Rotina Gerada pela IA

### Gerar e salvar rotina

**GET `/chat/rotina/{idUsuario}`**

### Buscar rotina do dia

**GET `/rotina/{idUsuario}`**

### Atualizar manualmente  

**PUT `/rotina/{idUsuario}`**

⚠️ **IMPORTANTE:**  
O corpo da requisição **deve ser raw text** contendo a rotina editada.

---

# 🔄 Fluxo completo com a API de IA (Python)

- Usuário faz check-in → salvo aqui
- Usuário conversa no chat → salvo aqui
- Rotina é gerada/editada → salvo aqui
- A API Python (flowmind_ai) lê esses dados do Firebase/PostgreSQL em tempo real
- Gera relatórios longos, gráficos e alertas de burnout
- App mobile (React Native) exibe tudo bonitinho


---

## 👩‍💻 Integrantes

| Nome                              | RM      |
|-----------------------------------|---------|
| Júlia Soares Farias dos Santos    | 554609  |
| Sofia Domingues GOnçalves         | 554920  |


---
