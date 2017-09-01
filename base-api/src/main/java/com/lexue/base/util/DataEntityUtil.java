package com.lexue.base.util;

import com.lexue.base.domain.DataEntity;
import com.lexue.base.domain.User;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * dataEntity 添加数据
 * <P>
 * 
 */
public class DataEntityUtil {

	public static void assemblyDataEntity(DataEntity entity, User user) {
		if (entity != null && user != null) {
			if (StringUtils.isBlank(entity.getId())) {
				entity.setCreateDate(new Date());
				entity.setCreateBy(user.getId());
			}
			entity.setUpdateDate(new Date());
			entity.setUpdateBy(user.getId());
		}
	}
}
