import {Elysia,status} from "elysia";
import {accountModel} from "./model";
import {AccountService} from "./service";

export const accounts = new Elysia({ prefix: "/accounts" })


    .post('/', async ({body}) => {
        try {
            const {id,username,password,balance,debt} = body;
            await AccountService.createOrUpdateAccount(id,username,password,balance,debt);
            return status(200)
        } catch (e) {
            console.error(e);
            return status(500)
        }
    }, {
        body: accountModel.accountBody
    })