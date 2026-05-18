import {Elysia,status} from "elysia";
import {accountModel} from "./model";

export const accounts = new Elysia({ prefix: "/accounts" })


    .post('/', async ({body}) => {
        try {


        } catch (e) {
            console.error(e);
            return status(500)
        }
    }, {
        body: accountModel.accountBody
    })