package com.lvzhu;

import com.lvzhu.bean.GeneratorConfig;
import com.lvzhu.bean.TableEntity;
import com.lvzhu.code.JavaDOGenerator;
import com.lvzhu.code.JavaDaoGenerator;
import com.lvzhu.code.SQLXmlGenerator;
import com.lvzhu.exceptions.GeneratorException;
import com.lvzhu.freemarker.FreemarkerService;
import com.lvzhu.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author lvzhu.
 * Time 2019/7/22 4:11 PM
 * Desc 代码生成器
 */
public class Generator {
    /**
     * 代码生成器主类
     *
     * @return
     */
    public static void genCode(GeneratorConfig config) throws IOException {
        check(config);

        FreemarkerService freemarkerService = new FreemarkerService();
        TableEntity tableEntity;
        try {
            tableEntity = DBUtils.getTableInfo(config);
        } catch (SQLException e) {
            throw new GeneratorException(e);
        }

        //生成do文件
        JavaDOGenerator doGenerator = new JavaDOGenerator(freemarkerService, tableEntity, config);
        doGenerator.gen();
        //生成dao文件
        JavaDaoGenerator daoGenerator = new JavaDaoGenerator(freemarkerService, tableEntity, config);
        daoGenerator.gen();
        //生成xml文件
        SQLXmlGenerator sqlXmlGenerator = new SQLXmlGenerator(freemarkerService, tableEntity, config);
        sqlXmlGenerator.gen();
    }

    private static void check(GeneratorConfig config) {
        if (config == null)
            throw new GeneratorException("生成代码前请设置");

        config.check();
    }

    public static Connection getConnection(String url, String user, String pwd){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user,pwd);
        } catch (ClassNotFoundException e) {
            throw new GeneratorException(e);
        } catch (SQLException e) {
            throw new GeneratorException(e);
        }
        return connection;
    }
}
