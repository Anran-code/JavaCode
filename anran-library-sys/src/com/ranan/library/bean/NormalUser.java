package com.ranan.library.bean;

import java.util.Objects;

public class NormalUser extends Member{
    private static final double rentPrice = 1.0;
    private static final int cardPrice = 20;
    private static final int toBeVipPrice = 50;
    private static final int rentMaxNum = 5;

    /** 创建普通会员并初始化其基本资料。 */
    public NormalUser(String name, String id, double balance, boolean hasCard, int currentRentNum) {
        super(name, id, balance, hasCard, currentRentNum);
    }

    /** 余额足够时扣除普通会员的一次借书费用。 */
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

    /** 检查借书上限、库存和费用，成功后记录借阅并减少可借量。 */
    @Override
    public boolean rentBook(Book book) {
        if (book == null || getCurrentRentNum() >= rentMaxNum || book.getAvailable() < 1) {
            return false;
        }
        // 先完成扣费判断，避免借阅失败后余额或库存已经变化。
        if (!isHasCard() && !deductBalance()) {
            return false;
        }
        rentList.add(book); // 保存馆藏中的同一个 Book 对象，归还时才能恢复真实库存。
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

    /** 创建升级后的 VIP；调用方需要用返回值替换用户集合中的原对象。 */
    public VipUser toBeVipUser(){
        if(this.getBalance() >= toBeVipPrice){
            VipUser vipUser = new VipUser(this.getName(), this.getId(),
                    this.getBalance() - toBeVipPrice, this.isHasCard(), this.rentList.size());
            vipUser.rentList.addAll(this.rentList);
            return vipUser;
        } else {
            throw new RuntimeException("余额不足，无法升级会员");
        }
    }
}
