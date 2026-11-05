package com.ranan.library.bean;

import java.util.Objects;

public class VipUser extends Member{
    private static final double rentPrice = 0.8;
    private static final int cardPrice = 15;
    private static final int rentMaxNum = 10;

    /** 创建 VIP 会员并初始化其基本资料。 */
    public VipUser(String name, String id, double balance, boolean hasCard, int currentRentNum) {
        super(name, id, balance, hasCard, currentRentNum);
    }

    /** 余额足够时扣除 VIP 会员的一次借书费用。 */
    @Override
    public boolean deductBalance() {
        if(this.getBalance() >= rentPrice){
            this.setBalance(getBalance() - rentPrice);
            return true;
        } else {
            return false;
        }
    }

    /** 未办卡且余额足够时扣费并激活借书卡。 */
    @Override
    public boolean getCard() {
        if(!this.isHasCard() && this.getBalance() >= cardPrice){
            this.setBalance(getBalance() - cardPrice);
            this.setHasCard(true);
            return true;
        } else {
            return false;
        }
    }

    /** 检查 VIP 借书上限、库存和费用，成功后更新借阅与库存。 */
    @Override
    public boolean rentBook(Book book) {
        if (book == null || getCurrentRentNum() >= rentMaxNum || book.getAvailable() < 1) {
            return false;
        }
        if (!isHasCard() && !deductBalance()) {
            return false;
        }
        rentList.add(book); // 与馆藏共享同一个 Book 对象，归还时更新原书库存。
        setCurrentRentNum(rentList.size());
        book.setAvailable(book.getAvailable() - 1);
        return true;
    }

    /** 按 ISBN 找到已借图书，移除借阅记录并恢复馆藏库存。 */
    @Override
    public boolean returnBook(Book book) {
        if (book == null) {
            return false;
        }
        for (int i = 0; i < rentList.size(); i++) {
            Book borrowedBook = rentList.get(i);
            if (Objects.equals(borrowedBook.getISBN(), book.getISBN())) {
                rentList.remove(i);
                setCurrentRentNum(rentList.size());
                borrowedBook.setAvailable(borrowedBook.getAvailable() + 1);
                return true;
            }
        }
        return false;
    }
}
