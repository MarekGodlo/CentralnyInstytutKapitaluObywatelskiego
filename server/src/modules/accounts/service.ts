import {prisma} from "../../lib/prisma";
export abstract class AccountService {
    static async createOrUpdateAccount(id: string, username: string, password: string, balance: number, debt : number, type: any) : Promise<void> {
        const account =  await prisma.accounts.findFirst({
            where: {
                id: id
            }
        })

        if (!account) {
            await prisma.accounts.create({
                data: {
                    id: id,
                    username: username,
                    password: password,
                    balance: balance,
                    debt: debt,
                    type: type
                }
            })
        } else {
            await prisma.accounts.update({
                where: {
                    id: id
                },
                data: {
                    balance: balance,
                    debt: debt
                }
            })
        }
    }

    static async getAllAccounts() {
        return prisma.accounts.findMany();
    }
}