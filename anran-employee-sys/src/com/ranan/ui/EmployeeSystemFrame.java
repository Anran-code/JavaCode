package com.ranan.ui;

import com.ranan.bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

public class EmployeeSystemFrame extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JMenuItem editMenuItem;
    private JMenuItem deleteMenuItem;
    // 接收登陆成功的用户名
    private String username;
    // 存放初始职员和后续新增职员；以下均为虚构的练习数据。
    private static ArrayList<Employee> allEmplotees = new ArrayList<>();

    static {
        allEmplotees.add(new Employee(1, "张晨", "男", 34, "138****1001", "后端工程师", "2019-03-18", 18000, "研发部"));
        allEmplotees.add(new Employee(2, "李欣", "女", 29, "138****1002", "前端工程师", "2021-07-05", 15000, "研发部"));
        allEmplotees.add(new Employee(3, "王浩", "男", 38, "138****1003", "研发主管", "2017-09-12", 26000, "研发部"));
        allEmplotees.add(new Employee(4, "陈雨", "女", 26, "138****1004", "测试工程师", "2023-02-20", 11000, "研发部"));
        allEmplotees.add(new Employee(5, "刘洋", "男", 31, "138****1005", "运维工程师", "2020-11-09", 14500, "研发部"));
        allEmplotees.add(new Employee(6, "赵婷", "女", 36, "138****1006", "人事经理", "2018-04-16", 16500, "人事部"));
        allEmplotees.add(new Employee(7, "孙悦", "女", 25, "138****1007", "招聘专员", "2024-06-03", 7800, "人事部"));
        allEmplotees.add(new Employee(8, "周磊", "男", 30, "138****1008", "培训专员", "2022-01-10", 9000, "人事部"));
        allEmplotees.add(new Employee(9, "吴静", "女", 39, "138****1009", "财务经理", "2016-08-22", 19000, "财务部"));
        allEmplotees.add(new Employee(10, "郑凯", "男", 28, "138****1010", "会计", "2022-05-16", 9500, "财务部"));
        allEmplotees.add(new Employee(11, "何敏", "女", 27, "138****1011", "出纳", "2023-10-09", 7200, "财务部"));
        allEmplotees.add(new Employee(12, "高峰", "男", 42, "138****1012", "市场总监", "2017-02-13", 28000, "市场部"));
        allEmplotees.add(new Employee(13, "林洁", "女", 32, "138****1013", "市场策划", "2020-06-29", 12500, "市场部"));
        allEmplotees.add(new Employee(14, "胡杰", "男", 24, "138****1014", "品牌专员", "2025-03-17", 7500, "市场部"));
        allEmplotees.add(new Employee(15, "郭倩", "女", 35, "138****1015", "销售经理", "2019-10-08", 20000, "销售部"));
        allEmplotees.add(new Employee(16, "马超", "男", 29, "138****1016", "销售顾问", "2021-12-06", 10500, "销售部"));
        allEmplotees.add(new Employee(17, "朱琳", "女", 26, "138****1017", "销售顾问", "2024-02-19", 8800, "销售部"));
        allEmplotees.add(new Employee(18, "许宁", "男", 33, "138****1018", "行政主管", "2020-01-13", 12000, "行政部"));
        allEmplotees.add(new Employee(19, "蒋雯", "女", 28, "138****1019", "行政专员", "2022-08-01", 8000, "行政部"));
        allEmplotees.add(new Employee(20, "唐宇", "男", 27, "138****1020", "客服专员", "2023-04-24", 7600, "客服部"));
    }

    /** 创建员工信息管理窗口。 */
    public EmployeeSystemFrame(String username) {
        this.username = username;
        this.setTitle("欢迎" + username + "访问人事管理系统 - 员工信息");
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        init();
        this.setVisible(true);
    }

    /** 将顶部操作栏和中间的员工表格放入窗口。 */
    private void init() {
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createTablePanel(), BorderLayout.CENTER);
    }

    /** 创建水平居中的搜索框、搜索按钮和添加按钮。 */
    private JPanel createToolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 14));
        Font font = new Font("宋体", Font.PLAIN, 16);

        searchField = new JTextField(24);
        searchField.setFont(font);
        searchField.setPreferredSize(new Dimension(280, 36));
        searchButton = new JButton("搜索");
        addButton = new JButton("添加");

        addButton.addActionListener(e -> addEmployee());
        searchButton.addActionListener(e -> refreshEmployeeTable());
        searchField.addActionListener(e -> refreshEmployeeTable()); // 在输入框按回车也可以搜索

        searchButton.setFont(font);
        addButton.setFont(font);

        toolbar.add(searchField);
        toolbar.add(searchButton);
        toolbar.add(addButton);
        return toolbar;
    }

    /** 接收添加窗口的结果，保存到集合并刷新表格。 */
    private void addEmployee() {
        Employee employee = new AddEmployeeUI().showDialog(this, allEmplotees);
        if (employee != null) {
            allEmplotees.add(employee);
            refreshEmployeeTable();
            JOptionPane.showMessageDialog(this, "添加职员成功");
        }
    }

    /** 根据搜索框内容重建表格；留空时显示全部职员。 */
    private void refreshEmployeeTable() {
        String keyword = searchField.getText().trim();
        tableModel.setRowCount(0);

        // 输入完整 ID 时优先精确查找，避免搜索“1”时匹配到所有电话号码。
        for (Employee employee : allEmplotees) {
            if (!keyword.isEmpty() && String.valueOf(employee.getId()).equals(keyword)) {
                tableModel.addRow(createTableRow(employee));
                return;
            }
        }

        String lowerKeyword = keyword.toLowerCase(Locale.ROOT);
        for (Employee employee : allEmplotees) {
            Object[] row = createTableRow(employee);
            if (keyword.isEmpty() || rowContains(row, lowerKeyword)) {
                tableModel.addRow(row);
            }
        }
    }

    /** 判断表格中的任一列是否包含搜索词。 */
    private boolean rowContains(Object[] row, String lowerKeyword) {
        for (Object value : row) {
            if (String.valueOf(value).toLowerCase(Locale.ROOT).contains(lowerKeyword)) {
                return true;
            }
        }
        return false;
    }

    /** 将 Employee 转换为表格中的一行，顺序与表头一致。 */
    private Object[] createTableRow(Employee employee) {
        return new Object[]{employee.getId(), employee.getName(), employee.getSex(),
                employee.getPhone(), employee.getPosition(), employee.getEntryDate(),
                employee.getSalary(), employee.getDepartment()};
    }

    /** 创建包含全部员工字段的表格，并放入滚动面板。 */
    private JPanel createTablePanel() {
        String[] columns = {"ID", "姓名", "性别", "电话", "职位", "入职时间", "薪水", "部门"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 修改操作以后由右键菜单处理
            }
        };
        refreshEmployeeTable();

        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("宋体", Font.PLAIN, 16));
        employeeTable.getTableHeader().setFont(new Font("宋体", Font.BOLD, 16));
        employeeTable.setRowHeight(27);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        installRowPopupMenu();

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        tablePanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        return tablePanel;
    }

    /** 右键点击数据行时，先选中该行，再显示“修改 / 删除”菜单。 */
    private void installRowPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        editMenuItem = new JMenuItem("修改");
        deleteMenuItem = new JMenuItem("删除");
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);

        editMenuItem.addActionListener(e -> {
            Integer employeeId = getSelectedEmployeeId();
            if (employeeId != null) {
                editEmployee(employeeId);
            }
        });
        deleteMenuItem.addActionListener(e -> {
            Integer employeeId = getSelectedEmployeeId();
            if (employeeId != null) {
                deleteEmployee(employeeId);
            }
        });

        employeeTable.addMouseListener(new MouseAdapter() {
            private void showMenuIfNeeded(MouseEvent e) {
                if (!e.isPopupTrigger()) {
                    return;
                }
                int row = employeeTable.rowAtPoint(e.getPoint());
                if (row < 0) {
                    return; // 表格空白处不显示行菜单
                }
                employeeTable.setRowSelectionInterval(row, row);
                popupMenu.show(employeeTable, e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showMenuIfNeeded(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showMenuIfNeeded(e);
            }
        });
    }

    /** 找到右键选中的职员，确认编辑后更新集合和表格。 */
    private void editEmployee(int employeeId) {
        for (int i = 0; i < allEmplotees.size(); i++) {
            Employee original = allEmplotees.get(i);
            if (original.getId() == employeeId) {
                Employee updated = new EditEmployeeUI().showDialog(this, original, allEmplotees);
                if (updated != null) {
                    allEmplotees.set(i, updated);
                    refreshEmployeeTable();
                    JOptionPane.showMessageDialog(this, "修改职员成功");
                }
                return;
            }
        }
    }

    /** 删除选中的职员；ArrayList.remove(index) 会自动让后面的元素前移。 */
    private void deleteEmployee(int employeeId) {
        for (int i = 0; i < allEmplotees.size(); i++) {
            Employee employee = allEmplotees.get(i);
            if (employee.getId() == employeeId) {
                int choice = JOptionPane.showConfirmDialog(this,
                        "确定删除职员“" + employee.getName() + "”（ID：" + employeeId + "）吗？",
                        "确认删除", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                allEmplotees.remove(i);
                if (searchField.getText().trim().equals(String.valueOf(employeeId))) {
                    searchField.setText(""); // 删掉按 ID 搜到的唯一结果后，恢复显示全部职员
                }
                refreshEmployeeTable();
                JOptionPane.showMessageDialog(this, "删除职员成功");
                return;
            }
        }
    }

    /** 从当前选中行读取员工 ID；排序后也能找到正确的数据行。 */
    private Integer getSelectedEmployeeId() {
        int viewRow = employeeTable.getSelectedRow();
        if (viewRow < 0) {
            return null;
        }
        int modelRow = employeeTable.convertRowIndexToModel(viewRow);
        Object id = tableModel.getValueAt(modelRow, 0);
        return ((Number) id).intValue();
    }
}
