package com.lvzhu.code;


import com.lvzhu.bean.GeneratorConfig;
import com.lvzhu.bean.TableEntity;
import com.lvzhu.freemarker.FreemarkerService;

import java.io.File;


/**
 * 生成XML的类
 */
public class SQLXmlGenerator extends AbstractGenerator{

	public SQLXmlGenerator(FreemarkerService freemarkerService, TableEntity tableEntity, GeneratorConfig config) {
		super(freemarkerService, tableEntity, config);
	}

	@Override
	public String getFTL() {
		return "sql_xml";
	}

	@Override
	public String genFileName() {
		return tableEntity.getClassName() + "Mapper.xml";
	}

	@Override
	protected String genFilePath() {
		return config.getModuleName() + File.separator + "src/main/resources" + config.getXmlLocation();
	}
}
