package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Menu;
import com.graduationproject.graduationproject.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // 查询菜单所有信息
    public List<Menu> findAll() {

        return menuRepository.findAll();
    }

    // 初始化菜单
    public void initMenu() {

        List<Menu> menuList = new ArrayList<Menu>();

        Menu menu = new Menu("蒜香鸡翅", 18.0, "元", "热菜");
        Menu menu1 = new Menu("芹菜梗炒牛肉", 24.0, "元", "热菜");
        Menu menu2 = new Menu("黄瓜炒鸡蛋", 10.0, "元", "热菜");
        Menu menu3 = new Menu("京酱肉丝", 18.0, "元", "热菜");
        Menu menu4 = new Menu("香辣鸡块", 15.0, "元", "热菜");
        Menu menu5 = new Menu("蒜苗炒平菇", 10.0, "元", "热菜");
        Menu menu6 = new Menu("五香牛排骨", 26.0, "元", "热菜");
        Menu menu7 = new Menu("清蒸桂鱼", 20.0, "元", "热菜");
        Menu menu8 = new Menu("西红柿炒鸡蛋", 12.0, "元", "热菜");
        Menu menu9 = new Menu("白菜狮子头", 15.0, "元", "热菜");

        Menu menu10 = new Menu("皮蛋拌豆腐", 10.0, "元", "凉菜");
        Menu menu11 = new Menu("香辣口水鸡", 15.0, "元", "凉菜");
        Menu menu12 = new Menu("凉拌海带丝", 10.0, "元", "凉菜");
        Menu menu13 = new Menu("油泼黄瓜片", 8.0, "元", "凉菜");
        Menu menu14 = new Menu("酸辣土豆萝卜丝 ", 9.0, "元", "凉菜");
        Menu menu15 = new Menu("西芹拌腐竹", 10.0, "元", "凉菜");

        Menu menu16 = new Menu("鲫鱼萝卜汤", 16.0, "元", "汤羹");
        Menu menu17 = new Menu("黑蒜番茄牛肉汤", 18.0, "元", "汤羹");
        Menu menu18 = new Menu("油菜蛋花汤", 10.0, "元", "汤羹");
        Menu menu19 = new Menu("羊杂汤", 16.0, "元", "汤羹");
        Menu menu20 = new Menu("当归红枣枸杞炖鸡汤", 13.0, "元", "汤羹");
        Menu menu21 = new Menu("枸杞银耳汤", 11.0, "元", "汤羹");

        Menu menu22 = new Menu("双色馒头", 5.0, "元", "主食");
        Menu menu23 = new Menu("香菇馒头", 5.0, "元", "主食");
        Menu menu24 = new Menu("蛋炒饭", 6.0, "元", "主食");
        Menu menu25 = new Menu("香蕉卷", 6.0, "元", "主食");
        Menu menu26 = new Menu("双米饭", 3.0, "元", "主食");
        Menu menu27 = new Menu("素三鲜水饺", 10.0, "元", "主食");

        menuList.add(menu);
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        menuList.add(menu5);
        menuList.add(menu6);
        menuList.add(menu7);
        menuList.add(menu8);
        menuList.add(menu9);
        menuList.add(menu10);
        menuList.add(menu11);
        menuList.add(menu12);
        menuList.add(menu13);
        menuList.add(menu14);
        menuList.add(menu15);
        menuList.add(menu16);
        menuList.add(menu17);
        menuList.add(menu18);
        menuList.add(menu19);
        menuList.add(menu20);
        menuList.add(menu21);
        menuList.add(menu22);
        menuList.add(menu23);
        menuList.add(menu24);
        menuList.add(menu25);
        menuList.add(menu26);
        menuList.add(menu27);

        menuRepository.saveAll(menuList);
    }
}
