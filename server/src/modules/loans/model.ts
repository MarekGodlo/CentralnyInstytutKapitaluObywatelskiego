import {t} from "elysia";

export const loanModel = {

    loanBody: t.Object({
        id: t.String(),
        accountId: t.String(),
        originalAmount: t.Number(),
        remainingAmount: t.Number(),
        interestRate: t.Number(),
        repaymentMonths: t.Number(),
        active: t.Boolean()
    }),

    loanResponse: t.Array(
        t.Object({
            id: t.String(),
            accountId: t.String(),
            originalAmount: t.Number(),
            remainingAmount: t.Number(),
            interestRate: t.Number(),
            repaymentMonths: t.Number(),
            active: t.Boolean()
        })
    )
}