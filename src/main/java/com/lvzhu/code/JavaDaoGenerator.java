package com.lvzhu.code;


import com.lvzhu.bean.GeneratorConfig;
import com.lvzhu.bean.TableEntity;
import com.lvzhu.freemarker.FreemarkerService;
import com.lvzhu.utils.PathUtils;


/**
 * 生成Mapper类
 */
public class JavaDaoGenerator extends AbstractGenerator{

	public JavaDaoGenerator(FreemarkerService freemarkerService, TableEntity tableEntity, GeneratorConfig config) {
		super(freemarkerService, tableEntity, config);
	}

	@Override
	public String getFTL() {
		return "java_dao";
	}

	@Override
	public String genFileName() {
		return tableEntity.getClassName() + "Mapper.java";
	}

	@Override
	protected String genFilePath() {
		return PathUtils.getPathByPackage(config.getDaoPackage(), config);
	}
}
