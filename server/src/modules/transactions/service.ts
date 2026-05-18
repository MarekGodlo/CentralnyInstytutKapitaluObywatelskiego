import {prisma} from "../../lib/prisma";

export abstract class TransactionService {
    static async createTransaction(id: string, senderId: string, receiverId: string, amount: number) : Promise<void> {
        await prisma.transaction.create({
            data: {
                id: id,
                senderId: senderId,
                receiverId: receiverId,
                amount: amount
            }
        });
    }

    static async getAllTransactions() {
        return prisma.transaction.findMany();
    }
}
