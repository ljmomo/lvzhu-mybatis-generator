package com.lvzhu.code;

import com.lvzhu.bean.GeneratorConfig;
import com.lvzhu.bean.TableEntity;
import com.lvzhu.freemarker.FreemarkerService;
import com.lvzhu.utils.PathUtils;


public class JavaDOGenerator extends AbstractGenerator{


    public JavaDOGenerator(FreemarkerService freemarkerService, TableEntity tableEntity, GeneratorConfig config){
        super(freemarkerService,tableEntity, config);
    }

    @Override
    public String getFTL() {
        return "java_do";
    }

    @Override
    public String genFileName() {
        return tableEntity.getClassName() + "DO.java";
    }

    @Override
    protected String genFilePath() {
        return PathUtils.getPathByPackage(config.getDOpackage(), config);
    }
}
