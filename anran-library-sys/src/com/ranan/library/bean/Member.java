package com.ranan.library.bean;

import java.util.ArrayList;

abstract public class Member {
    private String name;
    private String id;
    private double balance;
    private boolean hasCard;
    private int currentRentNum;

    protected ArrayList<Book> rentList = new ArrayList<>();

    /** 按会员类型扣除一次借书费用，余额不足时返回 false。 */
    abstract public boolean deductBalance();
    /** 扣除办卡费用并激活借书卡，失败时返回 false。 */
    abstract public boolean getCard();
    /** 检查借阅条件并按持卡状态处理费用，成功时更新借阅记录和库存。 */
    abstract public boolean rentBook(Book book);
    /** 归还已借图书，成功时更新借阅记录和库存。 */
    abstract public boolean returnBook(Book book);

    /** 初始化会员的基本资料和当前借阅数量。 */
    public Member(String name, String id, double balance, boolean hasCard, int currentRentNum) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.hasCard = hasCard;
        this.currentRentNum = currentRentNum;
    }

    /** 将指定金额加入会员余额。 */
    public void topUp(double money) {
        this.balance += money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isHasCard() {
        return hasCard;
    }

    public void setHasCard(boolean hasCard) {
        this.hasCard = hasCard;
    }

    public int getCurrentRentNum() {
        return currentRentNum;
    }

    public void setCurrentRentNum(int currentRentNum) {
        this.currentRentNum = currentRentNum;
    }

    /** 返回借阅列表的副本，避免外部直接增删导致借书本数与列表不一致。 */
    public ArrayList<Book> getRentList() {
        return new ArrayList<>(rentList);
    }
}
