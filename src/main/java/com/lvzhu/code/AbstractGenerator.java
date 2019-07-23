package com.lvzhu.code;

import com.lvzhu.exceptions.GeneratorException;
import com.lvzhu.bean.GeneratorConfig;
import com.lvzhu.bean.TableEntity;
import com.lvzhu.freemarker.FreemarkerService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvzhu.
 * Time 2019-07-22 16:51
 * Desc 抽象生成类
 */
public abstract class AbstractGenerator {

    protected FreemarkerService freemarkerService;

    protected TableEntity tableEntity;

    protected GeneratorConfig config;

    protected Map<String, Object> params = new HashMap<>();

    public AbstractGenerator(FreemarkerService freemarkerService, TableEntity tableEntity, GeneratorConfig config) {
        this.freemarkerService = freemarkerService;
        this.tableEntity = tableEntity;
        this.config = config;

        params.put("table", tableEntity);
        params.put("pk", tableEntity.getPk());
        params.put("pathName", tableEntity.getClassname().toLowerCase());
        params.put("columns", tableEntity.getColumns());
        params.put("doPackage", config.getDOpackage());
        params.put("daoPackage", config.getDaoPackage());
        params.put("uks", tableEntity.getUniqueKeyMap());

        params.put("pre", "#{");
        params.put("end", "}");
    }

    public final void gen() {
        //利用Freemarker生成代码字符串
        String genStr = freemarkerService.merge(getFTL(), params);
        //生成文件名
        String fileName = genFileName();
        String filePath = genFilePath();
        //写文件
        this.writeFile(genStr, filePath + File.separator + fileName);
    }

    /**
     * 文件不存在的情况下生成文件,文件存在则覆盖
     *
     * @param content
     * @param file
     */
    private void writeFile(String content, String file) {
        File ff = new File(file);
        try {
            if (ff.exists()) {
                ff.delete();
            }
            ff.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new GeneratorException(file + " error " + e);
        }

    }

    protected abstract String getFTL();

    protected abstract String genFileName();

    protected abstract String genFilePath();
}
