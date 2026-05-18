import {prisma} from "../../lib/prisma";
export abstract class LoanService {
    static async createOrUpdateLoan(id: string, accountId: string, originalAmount: number, remainingAmount: number, interestRate: number, repaymentMonths: number,  isActive: boolean) : Promise<void> {
        const loan =  await prisma.loan.findFirst({
            where: {
                id: id
            }
        })

        if (!loan) {
            await prisma.loan.create({
                data: {
                    id: id,
                    accountId: accountId,
                    originalAmount: originalAmount,
                    remainingAmount: remainingAmount,
                    interestRate: interestRate,
                    repaymentMonths: repaymentMonths,
                    active: isActive
                }
            })
        } else {
            await prisma.loan.update({
                where: {
                    id: id
                },
                data: {
                    remainingAmount: remainingAmount,
                    repaymentMonths: repaymentMonths,
                    active: isActive
                }
            })
        }
    }

    static async getAllLoans() {
        return prisma.loan.findMany();
    }
}