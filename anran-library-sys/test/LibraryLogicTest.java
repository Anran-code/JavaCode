import com.ranan.library.bean.Book;
import com.ranan.library.bean.NormalUser;
import com.ranan.library.bean.VipUser;

public class LibraryLogicTest {
    public static void main(String[] args) {
        Book book = new Book("测试图书", "ISBN-1", 2, 2);
        NormalUser normal = new NormalUser("小明", "U1", 100, false, 0);

        check(normal.rentBook(book), "普通用户应能借书");
        check(normal.getBalance() == 99, "无卡借书应扣 1 元");
        check(book.getAvailable() == 1 && normal.getCurrentRentNum() == 1, "库存和借阅数量应减少/增加");
        check(normal.returnBook(new Book(book)), "按相同 ISBN 应能归还图书");
        check(book.getAvailable() == 2 && normal.getCurrentRentNum() == 0, "归还应恢复原书库存和借阅数量");

        NormalUser noMoney = new NormalUser("小红", "U2", 0, false, 0);
        check(!noMoney.rentBook(book), "余额不足时不能借书");
        check(book.getAvailable() == 2, "借书失败不应改变库存");

        NormalUser cardHolder = new NormalUser("小兰", "U3", 0, true, 0);
        check(cardHolder.rentBook(book), "有卡用户可以免费借书");
        check(cardHolder.getBalance() == 0, "有卡借书不应扣余额");
        check(cardHolder.returnBook(book), "有卡用户应能还书");

        check(normal.rentBook(book), "升级前应能借书");
        normal.getRentList().clear();
        check(normal.getCurrentRentNum() == 1 && normal.getRentList().size() == 1,
                "外部修改借阅列表副本不应影响会员数据");
        VipUser vip = normal.toBeVipUser();
        check(vip.getBalance() == 48, "升级应扣 50 元");
        check(vip.getCurrentRentNum() == 1 && vip.getRentList().size() == 1, "升级应保留借阅记录");
        check(vip.returnBook(book) && book.getAvailable() == 2, "升级后仍应能归还原书");

        VipUser vipWithoutCard = new VipUser("小华", "U4", 1, false, 0);
        check(vipWithoutCard.rentBook(book), "VIP 应能借书");
        check(Math.abs(vipWithoutCard.getBalance() - 0.2) < 0.000001, "VIP 无卡借书应扣 0.8 元");
        check(vipWithoutCard.returnBook(book), "VIP 应能还书");

        NormalUser cardBuyer = new NormalUser("小青", "U5", 20, false, 0);
        check(cardBuyer.getCard() && cardBuyer.isHasCard(), "余额足够时应能办卡");
        check(!cardBuyer.getCard() && cardBuyer.getBalance() == 0, "已经办卡时不应重复扣费");

        System.out.println("借书、还书、扣费和会员升级检查通过");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
