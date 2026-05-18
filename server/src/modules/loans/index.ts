import {Elysia,status} from "elysia";
import {loanModel} from "./model";
import {LoanService} from "./service";

export const loans = new Elysia({ prefix: "/loans" })


    .post('/', async ({body}) => {
        try {
            const {id, accountId, originalAmount, remainingAmount, interestRate, repaymentMonths, active} = body;
            await LoanService.createOrUpdateLoan(id, accountId, originalAmount, remainingAmount, interestRate, repaymentMonths, active);
            return status(200)
        } catch (e) {
            console.error(e);
            return status(500)
        }
    }, {
        body: loanModel.loanBody
    })


    .get('/', async ({}) => {
        try {
            const loans = await LoanService.getAllLoans();
            return status(200, loans)
        } catch (e) {
            console.error(e);
            return status(500)
        }
    }, {
        response: {
            201: loanModel.loanResponse
        }
    })