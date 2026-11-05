import com.ranan.library.bean.LoginUser;
import com.ranan.library.bean.Book;
import com.ranan.library.bean.Member;
import com.ranan.library.bean.NormalUser;
import com.ranan.library.bean.User;
import com.ranan.library.bean.VipUser;
import com.ranan.library.data.LibraryData;

public class LibraryDataTest {
    public static void main(String[] args) {
        checkMemberMatches("Lihua");
        checkMemberMatches("Liming");

        User registered = LibraryData.registerUser("NewReader", "test123");
        check(LibraryData.findAccount("NewReader") == registered, "注册账号应能用于登录查找");
        Member member = LibraryData.findMember(registered);
        check(member instanceof NormalUser, "新注册用户应对应普通会员");
        check(member.getName().equals(registered.getName()), "会员与登录账号用户名应一致");
        check(member.getBalance() == 0 && member.getCurrentRentNum() == 0, "新会员应从空状态开始");

        boolean duplicateRejected = false;
        try {
            LibraryData.registerUser("NewReader", "anotherPassword");
        } catch (IllegalArgumentException exception) {
            duplicateRejected = true;
        }
        check(duplicateRejected, "重复用户名应被拒绝");
        check(LibraryData.getBooks().size() >= 1, "馆藏应有初始数据");

        member.topUp(60);
        Book borrowedBook = LibraryData.getBooks().get(2);
        check(member.rentBook(borrowedBook), "普通会员升级前应能借书");
        VipUser vip = LibraryData.upgradeToVip((NormalUser) member);
        check(LibraryData.findMember(registered) == vip, "升级后会员列表应保存 VIP 对象");
        check(vip.getBalance() == 9 && vip.getId().equals(member.getId()), "升级应扣费并保留会员 ID");
        check(vip.getRentList().size() == 1 && vip.getRentList().get(0) == borrowedBook,
                "升级后应保留原有借书记录");

        for (Member existingMember : LibraryData.getMembers()) {
            check(existingMember.getCurrentRentNum() == existingMember.getRentList().size(),
                    "会员借书数量应与借阅记录一致");
        }
        for (Book book : LibraryData.getBooks()) {
            int borrowed = 0;
            for (Member existingMember : LibraryData.getMembers()) {
                for (Book rentedBook : existingMember.getRentList()) {
                    if (rentedBook == book) {
                        borrowed++;
                    }
                }
            }
            check(book.getCount() - book.getAvailable() == borrowed,
                    "馆藏可借量应与全部借阅记录一致：" + book.getName());
        }
        System.out.println("登录账号、会员资料和馆藏初始化检查通过");
    }

    private static void checkMemberMatches(String name) {
        LoginUser account = LibraryData.findAccount(name);
        check(account instanceof User, "预置普通用户账号应存在：" + name);
        Member member = LibraryData.findMember((User) account);
        check(member != null && member.getName().equals(name), "预置会员资料应与账号对应：" + name);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
