package com.lvzhu.utils;

import com.lvzhu.exceptions.GeneratorException;
import com.lvzhu.bean.GeneratorConfig;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;


/**
 * @author lvzhu.
 * Time 2019-07-22 17:12
 * Desc 文件路径类
 */
public class PathUtils {

    private static String getProjectHome(){
        ClassLoader loader = PathUtils.class.getClassLoader();
        URL url = loader.getResource("");
        String file = url.getFile();
        File testPath = new File(file);
        return testPath.getParentFile().getParent();
    }

    /**
     * 根据包名找到包的实际路径
     * @param packageName 包名，例如：
     * @return 包的文件路径
     */
    public static String getPathByPackage(String packageName, GeneratorConfig config){
        if (StringUtils.isEmpty(packageName))
            throw new GeneratorException("包名不能为空");
        if (StringUtils.isEmpty(config.getModuleName())) {
            return "src/main/java/" + StringUtils.replace(packageName,"." ,"/");
        } else {
            return config.getModuleName() + File.separator + "src/main/java/" + StringUtils.replace(packageName,"." ,"/");
        }

    }
}
