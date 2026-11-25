package com.ranan.demo1junit;

import org.junit.Assert;
import org.junit.Test;

// 测试类：junit框架，对业务类中的业务方法进行正确性测试
public class StringUtilTest {
    // 测试方法：必须是公开public：无参，无返回值
    // 测试方法必须加上@Test注解（JUnit的核心步骤）
    @Test
    public void testPrintNumber() {
        // 测试步骤
        StringUtil.printNumber("张三abc"); // 5
        // 测试用例
        StringUtil.printNumber("");
        StringUtil.printNumber(null);
    }

    @Test
    public void testGetMaxIndex() {
        // 测试步骤
        int index = StringUtil.getMaxIndex("abcdefg");
        // 测试用例
        int index2 = StringUtil.getMaxIndex("");
        int index3 = StringUtil.getMaxIndex(null);

        // 做断言：断言结果是否与预测结果一直
        Assert.assertEquals("本轮测试失败",7,index);
        Assert.assertEquals("本轮测试失败",-1,index2);
        Assert.assertEquals("本轮测试失败",-1,index3);
    }
}
