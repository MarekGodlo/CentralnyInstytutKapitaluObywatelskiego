import {t} from "elysia";

export const transactionModel = {
    transactionBody: t.Object({
        id: t.String(),
        senderId: t.String(),
        receiverId: t.String(),
        amount: t.Number(),
        date: t.Optional(t.String())
    }),

    transactionResponse: t.Array(
        t.Object({
            id: t.String(),
            senderId: t.String(),
            receiverId: t.String(),
            amount: t.Number(),
            date: t.Any()
        })
    )
}
