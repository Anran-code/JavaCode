package com.ranan.library.data;

import com.ranan.library.bean.Admin;
import com.ranan.library.bean.Book;
import com.ranan.library.bean.LoginUser;
import com.ranan.library.bean.Member;
import com.ranan.library.bean.NormalUser;
import com.ranan.library.bean.User;
import com.ranan.library.bean.VipUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 暂时把登录账号、会员资料和馆藏放在内存中，供各个界面共用。 */
public final class LibraryData {
    private static final ArrayList<LoginUser> loginList = new ArrayList<>();
    private static final ArrayList<Member> memberList = new ArrayList<>();
    private static final ArrayList<Book> bookList = new ArrayList<>();
    private static int nextMemberNumber = 3;

    static {
        loginList.add(new Admin("Anran", "Yzm20060714"));

        loginList.add(new User("Lihua", "123456"));
        memberList.add(new NormalUser("Lihua", "U001", 35.0, false, 0));

        loginList.add(new User("Liming", "111111"));
        memberList.add(new VipUser("Liming", "U002", 80.0, true, 0));

        bookList.add(new Book("Java 核心技术", "9787115475524", 6, 6));
        bookList.add(new Book("Java 编程思想", "9787111213826", 5, 5));
        bookList.add(new Book("Effective Java", "9787111612728", 4, 4));
        bookList.add(new Book("数据结构与算法分析", "9787111559210", 5, 5));
        bookList.add(new Book("深入理解计算机系统", "9787111544937", 3, 3));
        bookList.add(new Book("算法导论", "9787111407010", 1, 1));

        // 用现有借书方法生成示例记录，使库存、会员借书数和借阅表一致。
        memberList.get(0).rentBook(bookList.get(0));
        memberList.get(1).rentBook(bookList.get(0));
        memberList.get(1).rentBook(bookList.get(1));
        memberList.get(1).rentBook(bookList.get(5));
    }

    private LibraryData() {
    }

    /** 按登录名查找账号，管理员和普通用户都在同一个列表中。 */
    public static LoginUser findAccount(String name) {
        for (LoginUser account : loginList) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    /** 根据普通用户登录账号，取得与其同名的会员资料。 */
    public static Member findMember(User account) {
        for (Member member : memberList) {
            if (member.getName().equals(account.getName())) {
                return member;
            }
        }
        return null;
    }

    /** 注册时同步建立登录账号和普通会员资料。 */
    public static User registerUser(String name, String password) {
        if (name == null || name.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("用户名和密码都不能为空");
        }
        String loginName = name.trim();
        if (findAccount(loginName) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        User account = new User(loginName, password);
        String memberId = String.format("U%03d", nextMemberNumber++);
        NormalUser member = new NormalUser(loginName, memberId, 0, false, 0);
        loginList.add(account);
        memberList.add(member);
        return account;
    }

    /** 升级后用新的 VIP 对象替换原会员，保留登录账号与现有借阅记录。 */
    public static VipUser upgradeToVip(NormalUser member) {
        int index = memberList.indexOf(member);
        if (index < 0) {
            throw new IllegalArgumentException("会员不在系统中");
        }
        VipUser vip = member.toBeVipUser();
        memberList.set(index, vip);
        return vip;
    }

    /** 返回馆藏列表；借还书时修改的是列表中同一个 Book 对象。 */
    public static List<Book> getBooks() {
        return Collections.unmodifiableList(bookList);
    }

    /** 返回会员列表，管理员界面可查看注册用户及其借书记录。 */
    public static List<Member> getMembers() {
        return Collections.unmodifiableList(memberList);
    }
}
