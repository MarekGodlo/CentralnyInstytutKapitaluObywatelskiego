### 1. Konfiguracja Bazy Danych i Serwera (Backend)

Serwer znajduje się w folderze `server` i wykorzystuje Prisma ORM do komunikacji z bazą MySQL/MariaDB.

#### Krok 1: Przygotowanie pliku `.env`
W folderze `server` utwórz plik `.env` (lub skopiuj `.env.example`) i uzupełnij dane dostępowe do Twojej bazy danych:

```env
DATABASE_URL="mysql://uzytkownik:haslo@localhost:3306/nazwa_bazy"
```


#### Krok 2: Instalacja klienta prisma
Otwórz terminal w folderze `server` i wykonaj następujące komendy:

1.  **Instalacja zależności:**
    ```bash
    bunx install
    ```
2.  **Inicjalizacja Prisma:**
    ```bash
    bunx prisma init
    ```

#### Krok 3: Przygotowanie schematu Prisma
Upewnij się, że w pliku `server/prisma/schema.prisma` znajduje się poniższa treść:

```prisma
// server/prisma/schema.prisma

generator client { 
  provider = "prisma-client-js"
  output   = "../src/generated/prisma"
}

datasource db {
  provider = "mysql" // Jezeli w twoim pliku wystepuje co innego zmien na mysql
}

model Accounts {
  id        String   @id @default(uuid())
  username  String   @unique
  password  String   @db.Text
  balance   Float    @default(0)
  debt      Float    @default(0)

  loans                Loan[]
  sentTransactions     Transaction[] @relation("SentTransactions")
  receivedTransactions Transaction[] @relation("ReceivedTransactions")
}

model Loan {
  id              String   @id @default(uuid())
  accountId       String
  originalAmount  Float
  remainingAmount Float
  interestRate    Float
  repaymentMonths Int
  active        Boolean  @default(true)

  account Accounts @relation(fields: [accountId], references: [id])
}

model Transaction {
  id         String   @id @default(uuid())
  senderId   String
  receiverId String
  amount     Float
  date       DateTime @default(now())

  sender   Accounts @relation("SentTransactions", fields: [senderId], references: [id])
  receiver Accounts @relation("ReceivedTransactions", fields: [receiverId], references: [id])
}
```


3.  **Wykonanie migracji:**
    ```bash
    bunx prisma migrate dev --name init_schema
    ```

4. **Generowanie klienta Prisma:**
    ```bash
    bunx prisma generate
    ```

#### Krok 4: Uruchomienie serwera
Aby uruchomić serwer w trybie deweloperskim:
```bash
bunx start
```
---