package com.ranan.ui;

import com.ranan.bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/** 负责显示添加职员的表单，并将填写结果封装为 Employee。 */
public class AddEmployeeUI {
    /** 点击确定时返回新职员；点击取消或关闭弹窗时返回 null。 */
    public Employee showDialog(Component parent, List<Employee> employees) {
        Font formFont = new Font("宋体", Font.PLAIN, 16);
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JTextField idField = new JTextField(18);
        JTextField nameField = new JTextField(18);
        JComboBox<String> sexBox = new JComboBox<>(new String[]{"请选择", "男", "女"});
        JTextField ageField = new JTextField(18);
        JTextField phoneField = new JTextField(18);
        JTextField positionField = new JTextField(18);
        JTextField entryDateField = new JTextField(18);
        JTextField salaryField = new JTextField(18);
        JTextField departmentField = new JTextField(18);

        String[] labels = {"ID：", "姓名：", "性别：", "年龄：", "电话：", "职位：", "入职时间（yyyy-MM-dd）：", "薪水：", "部门："};
        JComponent[] inputs = {idField, nameField, sexBox, ageField, phoneField,
                positionField, entryDateField, salaryField, departmentField};
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);
            label.setFont(formFont);
            inputs[i].setFont(formFont);
            formPanel.add(label);
            formPanel.add(inputs[i]);
        }

        while (true) {
            int choice = JOptionPane.showConfirmDialog(parent, formPanel, "添加职员",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (choice != JOptionPane.OK_OPTION) {
                return null;
            }

            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String position = positionField.getText().trim();
            String entryDate = entryDateField.getText().trim();
            String department = departmentField.getText().trim();
            if (idField.getText().trim().isEmpty() || name.isEmpty() || sexBox.getSelectedIndex() == 0
                    || ageField.getText().trim().isEmpty() || phone.isEmpty() || position.isEmpty()
                    || entryDate.isEmpty() || salaryField.getText().trim().isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "请填写完整的职员信息");
                continue;
            }

            int id;
            int age;
            double salary;
            try {
                id = Integer.parseInt(idField.getText().trim());
                age = Integer.parseInt(ageField.getText().trim());
                salary = Double.parseDouble(salaryField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "ID 和年龄应为整数，薪水应为数字");
                continue;
            }
            if (id <= 0 || age < 0 || !Double.isFinite(salary) || salary < 0) {
                JOptionPane.showMessageDialog(parent, "ID 应大于 0，年龄和薪水不能为负数");
                continue;
            }

            boolean idExists = false;
            for (Employee existing : employees) {
                if (existing.getId() == id) {
                    idExists = true;
                    break;
                }
            }
            if (idExists) {
                JOptionPane.showMessageDialog(parent, "职员 ID 已存在");
                continue;
            }

            return new Employee(id, name, (String) sexBox.getSelectedItem(), age,
                    phone, position, entryDate, salary, department);
        }
    }
}
