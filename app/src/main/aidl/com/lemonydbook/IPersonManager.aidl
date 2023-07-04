package com.lemonydbook;

// 导入所需要使用的非默认支持数据类型的包
import com.lemonydbook.bean.Personon;
import com.lemonydbook.bean.Test;

interface IPersonManager{
    // 要加定向tag
    void addPerson(in Personon person);

    void addTest(in Test test);

    List<Personon> getPersonList();
}