package com.ranan.library.ui;

import com.ranan.library.bean.Book;
import com.ranan.library.bean.Member;
import com.ranan.library.bean.VipUser;
import com.ranan.library.data.LibraryData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

/** 管理员界面：查看馆藏、会员和全部借阅，并代会员办理借还书及充值。 */
public class AdminLibrarySystemFrame extends JFrame {
    private final List<Book> books = LibraryData.getBooks();
    private final List<Member> members = LibraryData.getMembers();
    private final List<Book> visibleBooks = new ArrayList<>();
    private final List<Member> visibleMembers = new ArrayList<>();
    private final List<LoanEntry> visibleLoans = new ArrayList<>();

    private JTextField bookSearchField;
    private JTextField memberSearchField;
    private JTable bookTable;
    private JTable memberTable;
    private JTable loanTable;
    private DefaultTableModel bookModel;
    private DefaultTableModel memberModel;
    private DefaultTableModel loanModel;

    /** 建立管理员的三个页面。 */
    public AdminLibrarySystemFrame() {
        setTitle("图书管理系统 - 管理员");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("宋体", Font.PLAIN, 18));
        tabs.addTab("图书馆藏", createBookPage());
        tabs.addTab("会员信息", createMemberPage());
        tabs.addTab("全部借阅", createLoanPage());
        add(createTopBar(), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        refreshAll();
        setVisible(true);
    }

    /** 在窗口右上角放置退出登录按钮。 */
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        JLabel welcomeLabel = new JLabel("管理员界面");
        welcomeLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        JButton logoutButton = new JButton("退出登录");
        logoutButton.setFont(new Font("宋体", Font.PLAIN, 15));
        logoutButton.addActionListener(e -> logout());
        topBar.add(welcomeLabel, BorderLayout.WEST);
        topBar.add(logoutButton, BorderLayout.EAST);
        return topBar;
    }

    /** 返回登录界面，不清空本次运行中的馆藏和会员数据。 */
    private void logout() {
        new LoginFrame();
        dispose();
    }

    /** 馆藏页：按书名或 ISBN 搜索，右键借书或查看借书人。 */
    private JPanel createBookPage() {
        JPanel page = createPage();
        bookSearchField = new JTextField(25);
        page.add(createSearchBar("书名或 ISBN：", bookSearchField, this::refreshBooks), BorderLayout.NORTH);

        bookModel = createReadOnlyModel("书名", "ISBN", "馆藏总量", "可借数量");
        bookTable = createTable(bookModel);
        installPopup(bookTable, this::showBookMenu);
        page.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        return page;
    }

    /** 会员页：按用户名或会员 ID 搜索，右键查看借阅、充值或办卡。 */
    private JPanel createMemberPage() {
        JPanel page = createPage();
        memberSearchField = new JTextField(25);
        page.add(createSearchBar("用户名或会员 ID：", memberSearchField, this::refreshMembers), BorderLayout.NORTH);

        memberModel = createReadOnlyModel("会员 ID", "用户名", "类型", "余额", "借书卡", "当前借书");
        memberTable = createTable(memberModel);
        installPopup(memberTable, this::showMemberMenu);
        page.add(new JScrollPane(memberTable), BorderLayout.CENTER);
        return page;
    }

    /** 全部借阅页：显示每条借书记录，右键可直接还书。 */
    private JPanel createLoanPage() {
        JPanel page = createPage();
        JLabel tip = new JLabel("所有会员的借书记录（右键可还书）");
        tip.setFont(new Font("宋体", Font.PLAIN, 16));
        page.add(tip, BorderLayout.NORTH);

        loanModel = createReadOnlyModel("借书人 ID", "借书人", "书名", "ISBN");
        loanTable = createTable(loanModel);
        installPopup(loanTable, (row, event) -> {
            LoanEntry loan = visibleLoans.get(row);
            JPopupMenu menu = new JPopupMenu();
            JMenuItem returnItem = new JMenuItem("还书");
            returnItem.addActionListener(e -> returnBook(loan, this));
            menu.add(returnItem);
            menu.show(loanTable, event.getX(), event.getY());
        });
        page.add(new JScrollPane(loanTable), BorderLayout.CENTER);
        return page;
    }

    /** 给各页设置相同的边距。 */
    private JPanel createPage() {
        JPanel page = new JPanel(new BorderLayout(0, 14));
        page.setBorder(BorderFactory.createEmptyBorder(20, 24, 24, 24));
        return page;
    }

    /** 创建支持按钮和回车触发的搜索栏。 */
    private JPanel createSearchBar(String labelText, JTextField field, Runnable searchAction) {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        JLabel label = new JLabel(labelText);
        JButton button = new JButton("搜索");
        Font font = new Font("宋体", Font.PLAIN, 16);
        label.setFont(font);
        field.setFont(font);
        button.setFont(font);
        button.addActionListener(e -> searchAction.run());
        field.addActionListener(e -> searchAction.run());
        bar.add(label);
        bar.add(field);
        bar.add(button);
        return bar;
    }

    /** 表格只显示数据，不允许直接改动业务对象。 */
    private DefaultTableModel createReadOnlyModel(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("宋体", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 15));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        return table;
    }

    /** 右键时选中鼠标所在行，并把视图行号转换为数据模型行号。 */
    private void installPopup(JTable table, BiConsumer<Integer, MouseEvent> handler) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                showPopup(event);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                showPopup(event);
            }

            private void showPopup(MouseEvent event) {
                if (!event.isPopupTrigger()) {
                    return;
                }
                int viewRow = table.rowAtPoint(event.getPoint());
                if (viewRow < 0) {
                    return;
                }
                table.setRowSelectionInterval(viewRow, viewRow);
                handler.accept(table.convertRowIndexToModel(viewRow), event);
            }
        });
    }

    /** 图书行的菜单：无库存时禁用借书，但仍可查看借书人。 */
    private void showBookMenu(int row, MouseEvent event) {
        Book book = visibleBooks.get(row);
        JPopupMenu menu = new JPopupMenu();
        JMenuItem borrowItem = new JMenuItem("借书");
        borrowItem.setEnabled(book.getAvailable() > 0);
        borrowItem.addActionListener(e -> borrowBook(book));
        JMenuItem borrowersItem = new JMenuItem("查看被借情况");
        borrowersItem.addActionListener(e -> showLoansDialog("《" + book.getName() + "》的借书人", book, null));
        menu.add(borrowItem);
        menu.add(borrowersItem);
        menu.show(bookTable, event.getX(), event.getY());
    }

    /** 会员行的菜单：查看借阅详情，或为该会员充值、办卡。 */
    private void showMemberMenu(int row, MouseEvent event) {
        Member member = visibleMembers.get(row);
        JPopupMenu menu = new JPopupMenu();
        JMenuItem loansItem = new JMenuItem("查看借书情况");
        loansItem.addActionListener(e -> showLoansDialog(member.getName() + "的借书记录", null, member));
        JMenuItem topUpItem = new JMenuItem("充值");
        topUpItem.addActionListener(e -> topUp(member));
        JMenuItem getCardItem = new JMenuItem("办卡");
        getCardItem.addActionListener(e -> getCard(member));
        menu.add(loansItem);
        menu.add(topUpItem);
        menu.add(getCardItem);
        menu.show(memberTable, event.getX(), event.getY());
    }

    /** 按会员 ID 找人，再调用该会员自己的借书方法。 */
    private void borrowBook(Book book) {
        String input = JOptionPane.showInputDialog(this, "请输入借书人的会员 ID：", "管理员借书", JOptionPane.PLAIN_MESSAGE);
        if (input == null) {
            return;
        }
        Member member = findMemberById(input.trim());
        if (member == null) {
            showWarning("未找到该会员 ID");
            return;
        }
        if (book.getAvailable() < 1) {
            showWarning("这本书已经没有可借库存");
            return;
        }
        int limit = member instanceof VipUser ? 10 : 5;
        double price = member instanceof VipUser ? 0.8 : 1.0;
        if (member.getCurrentRentNum() >= limit) {
            showWarning("该会员已达到借书上限（" + limit + " 本）");
            return;
        }
        if (!member.isHasCard() && member.getBalance() < price) {
            showWarning("该会员余额不足");
            return;
        }
        if (!member.rentBook(book)) {
            showWarning("借书失败，请检查会员状态和库存");
            return;
        }
        refreshAll();
        JOptionPane.showMessageDialog(this, "已为 " + member.getName() + " 借出《" + book.getName() + "》");
    }

    /** 两种详情弹窗共用同一张表：可按图书或会员筛选借阅记录。 */
    private void showLoansDialog(String title, Book selectedBook, Member selectedMember) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(950, 430);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        DefaultTableModel model = createReadOnlyModel("会员 ID", "用户名", "会员类型", "余额", "借书卡", "借书数", "书名", "ISBN");
        JTable table = createTable(model);
        List<LoanEntry> displayedLoans = new ArrayList<>();
        Runnable reload = () -> {
            displayedLoans.clear();
            displayedLoans.addAll(collectLoans(selectedBook, selectedMember));
            model.setRowCount(0);
            for (LoanEntry loan : displayedLoans) {
                Member member = loan.member;
                model.addRow(new Object[]{member.getId(), member.getName(), memberType(member),
                        String.format("%.2f", member.getBalance()), cardStatus(member),
                        member.getCurrentRentNum(), loan.book.getName(), loan.book.getISBN()});
            }
        };
        reload.run();

        installPopup(table, (row, event) -> {
            LoanEntry loan = displayedLoans.get(row);
            JPopupMenu menu = new JPopupMenu();
            JMenuItem returnItem = new JMenuItem("还书");
            returnItem.addActionListener(e -> {
                if (returnBook(loan, dialog)) {
                    reload.run();
                }
            });
            menu.add(returnItem);
            menu.show(table, event.getX(), event.getY());
        });
        dialog.add(new JScrollPane(table), BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    /** 从各会员的借阅列表汇总记录；null 过滤条件表示不过滤。 */
    private List<LoanEntry> collectLoans(Book selectedBook, Member selectedMember) {
        List<LoanEntry> result = new ArrayList<>();
        for (Member member : members) {
            if (selectedMember != null && member != selectedMember) {
                continue;
            }
            for (Book book : member.getRentList()) {
                if (selectedBook == null || book == selectedBook) {
                    result.add(new LoanEntry(member, book));
                }
            }
        }
        return result;
    }

    /** 在任一借阅表中归还所选书，并刷新所有页面。 */
    private boolean returnBook(LoanEntry loan, Component parent) {
        if (!loan.member.returnBook(loan.book)) {
            JOptionPane.showMessageDialog(parent, "还书失败，借阅记录可能已变化", "操作未完成", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        refreshAll();
        JOptionPane.showMessageDialog(parent, "已为 " + loan.member.getName() + " 归还《" + loan.book.getName() + "》");
        return true;
    }

    /** 给指定会员充值，并检查输入金额。 */
    private void topUp(Member member) {
        String input = JOptionPane.showInputDialog(this, "给 " + member.getName() + " 充值金额：", "管理员充值", JOptionPane.PLAIN_MESSAGE);
        if (input == null) {
            return;
        }
        try {
            double amount = Double.parseDouble(input.trim());
            if (!Double.isFinite(amount) || amount <= 0 || !Double.isFinite(member.getBalance() + amount)) {
                showWarning("请输入有效的正数金额");
                return;
            }
            member.topUp(amount);
            refreshAll();
            JOptionPane.showMessageDialog(this, "充值成功");
        } catch (NumberFormatException exception) {
            showWarning("请输入有效的数字金额");
        }
    }

    /** 为指定会员扣费办卡，并刷新会员余额和借书卡状态。 */
    private void getCard(Member member) {
        if (member.isHasCard()) {
            showWarning(member.getName() + " 已经办理了借书卡");
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this,
                "为 " + member.getName() + " 办卡？费用将从该会员余额扣除。",
                "确认办卡", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }
        if (!member.getCard()) {
            showWarning("该会员余额不足，请先充值");
            return;
        }
        refreshAll();
        JOptionPane.showMessageDialog(this, "已为 " + member.getName() + " 办卡成功");
    }

    private Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    /** 搜索词为空时显示全部馆藏。 */
    private void refreshBooks() {
        String keyword = bookSearchField.getText().trim().toLowerCase(Locale.ROOT);
        visibleBooks.clear();
        bookModel.setRowCount(0);
        for (Book book : books) {
            if (book.getName().toLowerCase(Locale.ROOT).contains(keyword)
                    || book.getISBN().toLowerCase(Locale.ROOT).contains(keyword)) {
                visibleBooks.add(book);
                bookModel.addRow(new Object[]{book.getName(), book.getISBN(), book.getCount(), book.getAvailable()});
            }
        }
    }

    /** 搜索词为空时显示全部会员，包括刚注册的用户。 */
    private void refreshMembers() {
        String keyword = memberSearchField.getText().trim().toLowerCase(Locale.ROOT);
        visibleMembers.clear();
        memberModel.setRowCount(0);
        for (Member member : members) {
            if (member.getName().toLowerCase(Locale.ROOT).contains(keyword)
                    || member.getId().toLowerCase(Locale.ROOT).contains(keyword)) {
                visibleMembers.add(member);
                memberModel.addRow(new Object[]{member.getId(), member.getName(), memberType(member),
                        String.format("%.2f", member.getBalance()), cardStatus(member), member.getCurrentRentNum()});
            }
        }
    }

    /** 重建全部借阅表，不额外维护一份容易过期的借书数据。 */
    private void refreshLoans() {
        visibleLoans.clear();
        visibleLoans.addAll(collectLoans(null, null));
        loanModel.setRowCount(0);
        for (LoanEntry loan : visibleLoans) {
            loanModel.addRow(new Object[]{loan.member.getId(), loan.member.getName(),
                    loan.book.getName(), loan.book.getISBN()});
        }
    }

    private void refreshAll() {
        refreshBooks();
        refreshMembers();
        refreshLoans();
    }

    private String memberType(Member member) {
        return member instanceof VipUser ? "VIP 会员" : "普通会员";
    }

    private String cardStatus(Member member) {
        return member.isHasCard() ? "已办卡" : "未办卡";
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "操作未完成", JOptionPane.WARNING_MESSAGE);
    }

    /** 一条借阅记录由借书人和馆藏中的同一个 Book 对象组成。 */
    private static class LoanEntry {
        private final Member member;
        private final Book book;

        private LoanEntry(Member member, Book book) {
            this.member = member;
            this.book = book;
        }
    }
}
