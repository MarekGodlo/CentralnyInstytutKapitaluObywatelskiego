import { Elysia,t } from 'elysia'
import { cors } from '@elysiajs/cors'
import {accounts} from "./src/modules/accounts";
import {loans} from "./src/modules/loans";


const app = new Elysia({
    name: 'backend server',
    prefix: '/api'
})
    .use(cors())
    .use(accounts)
    .use(loans)


app.listen(3000)
console.log(`🦊 Elysia for foxgirls app is running at ${app.server?.url}`)