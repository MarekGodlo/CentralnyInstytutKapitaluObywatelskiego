import {Elysia, status} from "elysia";
import {transactionModel} from "./model";
import {TransactionService} from "./service";

export const transactions = new Elysia({ prefix: "/transactions" })
    .post('/', async ({body}) => {
        try {
            const {id, senderId, receiverId, amount} = body;
            await TransactionService.createTransaction(id, senderId, receiverId, amount);
            return status(200);
        } catch (e) {
            console.error(e);
            return status(500);
        }
    }, {
        body: transactionModel.transactionBody
    })
    .get('/', async () => {
        try {
            const txs = await TransactionService.getAllTransactions();
            return status(200, txs);
        } catch (e) {
            console.error(e);
            return status(500);
        }
    }, {
        response: {
            201: transactionModel.transactionResponse
        }
    });
