package com.lvzhu;

import com.lvzhu.bean.GeneratorConfig;

import java.io.IOException;


/**
 * @author lvzhu.
 * Time 2019-07-22 17:24
 * Desc Mybatis代码生成工具
 */
public class MybatisGenerator {

    public static void main(String[] args) {
        //数据库的连接信息、用户名、密码
        String DB_URL = "jdbc:mysql://localhost:3306/lvzhu?characterEncoding=utf8";
        String user = "root";
        String pwd = "123456";

        GeneratorConfig config = new GeneratorConfig(DB_URL, user, pwd);
        //设置生成的 XXXXMapper.java所在的包名
        config.setDaoPackage("com.lvzhu.dal.dao");
        //设置生成的 XXXXDO.java所在的包名
        config.setDOpackage("com.lvzhu.dal.dataobject");
        //设置生成的XXX.java存放的工程模块名
        config.setModuleName("/Users/lvzhu/myproject/lvzhu-mybatis-generator");
        //设置生成的XXXXMapper.xml存在的相对路径，相对module的resources目录
        config.setXmlLocation("/mybatis/mapper");
        //设置数据库名，库名填错会找不到表
        config.setTableCatalog("lvzhu");
        //设置表名，生成器根据设置的表生成代码
        config.setTableName("sys_user");
        //设置数据表前缀，比如tb_activity的前缀为tb，设置了此项之后生成的do、dao、mapper.xml不包含前缀tb
        config.setTablePrefix("sys_");

        //代码生成
        try {
            Generator.genCode(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
