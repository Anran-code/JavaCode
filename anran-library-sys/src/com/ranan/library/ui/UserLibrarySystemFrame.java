package com.ranan.library.ui;

import com.ranan.library.bean.Book;
import com.ranan.library.bean.Member;
import com.ranan.library.bean.NormalUser;
import com.ranan.library.bean.User;
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

/** 普通用户界面：馆藏查询与个人借阅信息。 */
public class UserLibrarySystemFrame extends JFrame {
    private Member currentMember;
    private final List<Book> books = LibraryData.getBooks();
    private final List<Book> visibleBooks = new ArrayList<>();
    private final List<Book> visibleRentBooks = new ArrayList<>();

    private JTextField searchField;
    private JTable bookTable;
    private JTable rentTable;
    private DefaultTableModel bookTableModel;
    private DefaultTableModel rentTableModel;
    private JLabel nameValue;
    private JLabel idValue;
    private JLabel typeValue;
    private JButton upgradeButton;
    private JLabel balanceValue;
    private JLabel cardValue;
    private JLabel rentCountValue;

    /** 使用登录账号找到对应的会员资料，再创建用户界面。 */
    public UserLibrarySystemFrame(User account) {
        currentMember = LibraryData.findMember(account);
        if (currentMember == null) {
            throw new IllegalStateException("登录账号没有对应的会员资料：" + account.getName());
        }

        setTitle("图书管理系统 - " + currentMember.getName());
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("宋体", Font.PLAIN, 18));
        tabs.addTab("图书馆藏", createBooksPage());
        tabs.addTab("个人信息与借阅", createPersonalPage());
        add(createTopBar(), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        refreshAll();
        setVisible(true);
    }

    /** 在窗口右上角放置退出登录按钮。 */
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        JLabel welcomeLabel = new JLabel("当前用户：" + currentMember.getName());
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

    /** 第一页：搜索框、馆藏表格，以及右键借书菜单。 */
    private JPanel createBooksPage() {
        JPanel page = new JPanel(new BorderLayout(0, 12));
        page.setBorder(BorderFactory.createEmptyBorder(18, 22, 22, 22));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        searchField = new JTextField(24);
        searchField.setFont(new Font("宋体", Font.PLAIN, 16));
        JButton searchButton = new JButton("搜索");
        searchButton.setFont(new Font("宋体", Font.PLAIN, 16));
        searchButton.addActionListener(e -> refreshBooks());
        searchField.addActionListener(e -> refreshBooks());
        searchPanel.add(new JLabel("书名或 ISBN："));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        page.add(searchPanel, BorderLayout.NORTH);

        bookTableModel = createReadOnlyModel("书名", "ISBN", "馆藏总量", "可借数量");
        bookTable = createTable(bookTableModel);
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showBorrowMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showBorrowMenu(e);
            }
        });
        page.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        return page;
    }

    /** 第二页：当前登录用户的会员资料和借书记录。 */
    private JPanel createPersonalPage() {
        JPanel page = new JPanel(new BorderLayout(0, 20));
        page.setBorder(BorderFactory.createEmptyBorder(20, 22, 22, 22));

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 20, 16));
        infoPanel.setBorder(BorderFactory.createTitledBorder("我的资料"));
        nameValue = new JLabel();
        idValue = new JLabel();
        typeValue = new JLabel();
        balanceValue = new JLabel();
        cardValue = new JLabel();
        rentCountValue = new JLabel();
        infoPanel.add(createInfoRow("用户名：", nameValue));
        infoPanel.add(createInfoRow("会员 ID：", idValue));
        JPanel typeRow = createInfoRow("会员类型：", typeValue);
        upgradeButton = new JButton("升级 VIP");
        upgradeButton.addActionListener(e -> upgradeToVip());
        typeRow.add(upgradeButton);
        infoPanel.add(typeRow);

        JPanel balanceRow = createInfoRow("余额：", balanceValue);
        JButton topUpButton = new JButton("充值");
        topUpButton.addActionListener(e -> topUp());
        balanceRow.add(topUpButton);
        infoPanel.add(balanceRow);
        JPanel cardRow = createInfoRow("借书卡：", cardValue);
        JButton getCardButton = new JButton("办卡");
        getCardButton.addActionListener(e -> getCard());
        cardRow.add(getCardButton);
        infoPanel.add(cardRow);
        infoPanel.add(createInfoRow("当前借书：", rentCountValue));
        page.add(infoPanel, BorderLayout.NORTH);

        rentTableModel = createReadOnlyModel("书名", "ISBN");
        rentTable = createTable(rentTableModel);
        rentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showReturnMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showReturnMenu(e);
            }
        });
        JScrollPane rentScroll = new JScrollPane(rentTable);
        rentScroll.setBorder(BorderFactory.createTitledBorder("我的借书记录（右键还书）"));
        page.add(rentScroll, BorderLayout.CENTER);
        return page;
    }

    /** 创建不可直接编辑的表格数据模型。 */
    private DefaultTableModel createReadOnlyModel(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    /** 统一表格的行高和排序设置。 */
    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("宋体", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 15));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        return table;
    }

    /** 创建个人资料中的一个字段。 */
    private JPanel createInfoRow(String title, JLabel value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel titleLabel = new JLabel(title);
        Font font = new Font("宋体", Font.PLAIN, 16);
        titleLabel.setFont(font);
        value.setFont(font);
        row.add(titleLabel);
        row.add(value);
        return row;
    }

    /** 在鼠标右键选中的馆藏行显示借书菜单。 */
    private void showBorrowMenu(MouseEvent event) {
        if (!event.isPopupTrigger()) {
            return;
        }
        int viewRow = bookTable.rowAtPoint(event.getPoint());
        if (viewRow < 0) {
            return;
        }
        bookTable.setRowSelectionInterval(viewRow, viewRow);
        Book book = visibleBooks.get(bookTable.convertRowIndexToModel(viewRow));
        JMenuItem borrowItem = new JMenuItem("借书");
        borrowItem.setEnabled(book.getAvailable() > 0);
        borrowItem.addActionListener(e -> borrowBook(book));
        JPopupMenu menu = new JPopupMenu();
        menu.add(borrowItem);
        menu.show(bookTable, event.getX(), event.getY());
    }

    /** 调用会员对象的借书方法，成功后刷新库存和个人资料。 */
    private void borrowBook(Book book) {
        int limit = currentMember instanceof VipUser ? 10 : 5;
        double price = currentMember instanceof VipUser ? 0.8 : 1.0;
        if (currentMember.getCurrentRentNum() >= limit) {
            showWarning("已达到借书上限（" + limit + " 本）");
            return;
        }
        if (!currentMember.isHasCard() && currentMember.getBalance() < price) {
            showWarning("余额不足，请先充值");
            return;
        }
        if (!currentMember.rentBook(book)) {
            showWarning("借书失败，请检查库存或会员状态");
            return;
        }
        refreshAll();
        JOptionPane.showMessageDialog(this, "借书成功：" + book.getName());
    }

    /** 在鼠标右键选中的借书记录行显示还书菜单。 */
    private void showReturnMenu(MouseEvent event) {
        if (!event.isPopupTrigger()) {
            return;
        }
        int viewRow = rentTable.rowAtPoint(event.getPoint());
        if (viewRow < 0) {
            return;
        }
        rentTable.setRowSelectionInterval(viewRow, viewRow);
        Book book = visibleRentBooks.get(rentTable.convertRowIndexToModel(viewRow));
        JMenuItem returnItem = new JMenuItem("还书");
        returnItem.addActionListener(e -> returnBook(book));
        JPopupMenu menu = new JPopupMenu();
        menu.add(returnItem);
        menu.show(rentTable, event.getX(), event.getY());
    }

    /** 调用会员对象的还书方法，并同步更新两张表。 */
    private void returnBook(Book book) {
        if (!currentMember.returnBook(book)) {
            showWarning("还书失败，该书不在当前借阅记录中");
            return;
        }
        refreshAll();
        JOptionPane.showMessageDialog(this, "还书成功：" + book.getName());
    }

    /** 输入正数金额后调用会员对象的充值方法。 */
    private void topUp() {
        String input = JOptionPane.showInputDialog(this, "请输入充值金额：", "充值", JOptionPane.PLAIN_MESSAGE);
        if (input == null) {
            return;
        }
        try {
            double amount = Double.parseDouble(input.trim());
            if (!Double.isFinite(amount) || amount <= 0 || !Double.isFinite(currentMember.getBalance() + amount)) {
                showWarning("请输入有效的正数金额");
                return;
            }
            currentMember.topUp(amount);
            refreshPersonalInfo();
            JOptionPane.showMessageDialog(this, "充值成功");
        } catch (NumberFormatException exception) {
            showWarning("请输入有效的数字金额");
        }
    }

    /** 确认办卡后调用会员对象的扣费办卡方法。 */
    private void getCard() {
        if (currentMember.isHasCard()) {
            showWarning("你已经办理了借书卡");
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this, "办卡费用将从余额扣除，确定办理吗？",
                "确认办卡", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }
        if (!currentMember.getCard()) {
            showWarning("余额不足，请先充值");
            return;
        }
        refreshPersonalInfo();
        JOptionPane.showMessageDialog(this, "办卡成功");
    }

    /** 将普通会员升级为 VIP，并让本窗口继续使用升级后的会员对象。 */
    private void upgradeToVip() {
        if (!(currentMember instanceof NormalUser)) {
            showWarning("你已经是 VIP 会员");
            return;
        }
        if (currentMember.getBalance() < 50) {
            showWarning("余额不足，升级 VIP 需要 50 元");
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this, "升级 VIP 将扣除 50 元，确定升级吗？",
                "确认升级", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }
        currentMember = LibraryData.upgradeToVip((NormalUser) currentMember);
        refreshPersonalInfo();
        JOptionPane.showMessageDialog(this, "升级 VIP 成功");
    }

    /** 根据搜索词重建馆藏表；输入为空时显示全部图书。 */
    private void refreshBooks() {
        String keyword = searchField.getText().trim().toLowerCase(Locale.ROOT);
        visibleBooks.clear();
        bookTableModel.setRowCount(0);
        for (Book book : books) {
            if (book.getName().toLowerCase(Locale.ROOT).contains(keyword)
                    || book.getISBN().toLowerCase(Locale.ROOT).contains(keyword)) {
                visibleBooks.add(book);
                bookTableModel.addRow(new Object[]{book.getName(), book.getISBN(), book.getCount(), book.getAvailable()});
            }
        }
    }

    /** 刷新当前会员的字段与借书记录。 */
    private void refreshPersonalInfo() {
        nameValue.setText(currentMember.getName());
        idValue.setText(currentMember.getId());
        typeValue.setText(currentMember instanceof VipUser ? "VIP 会员" : "普通会员");
        upgradeButton.setEnabled(currentMember instanceof NormalUser);
        upgradeButton.setText(currentMember instanceof VipUser ? "已是 VIP" : "升级 VIP");
        balanceValue.setText(String.format("%.2f 元", currentMember.getBalance()));
        cardValue.setText(currentMember.isHasCard() ? "已办卡" : "未办卡");
        rentCountValue.setText(currentMember.getCurrentRentNum() + " 本");

        visibleRentBooks.clear();
        visibleRentBooks.addAll(currentMember.getRentList());
        rentTableModel.setRowCount(0);
        for (Book book : visibleRentBooks) {
            rentTableModel.addRow(new Object[]{book.getName(), book.getISBN()});
        }
    }

    /** 借还书后同时刷新馆藏和个人信息。 */
    private void refreshAll() {
        refreshBooks();
        refreshPersonalInfo();
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "操作未完成", JOptionPane.WARNING_MESSAGE);
    }
}
