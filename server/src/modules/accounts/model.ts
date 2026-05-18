import {t} from "elysia";

export const accountModel = {

    accountBody: t.Object({
        id: t.String(),
        username: t.String(),
        password: t.String(),
        balance: t.Number(),
        debt: t.Number()
    }),

    accountResponse: t.Optional(
        t.Object({
            id: t.String(),
            username: t.String(),
            password: t.String(),
            balance: t.Number(),
            debt: t.Number()
        })
    )
}