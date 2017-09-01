package com.lexue.base.mybatis;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import com.lexue.base.domain.ParentEntity;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 基本的sql生成类
 * 
 */
@Log
public class BaseSqlProvider {

	/**
	 * 插入实体的SQL
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String insert(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.INSERT_INTO(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			MyId id = field.getAnnotation(MyId.class);
			if (column != null && colValue == null)
				continue;
			if (id != null) {
				if (colValue == null
						|| (field.getType().equals(String.class) && StringUtils.isBlank(String.valueOf(colValue)))) {
					colValue = UUID.randomUUID().toString().replace("-", "");
					field.set(obj, colValue);
				}
			}
			String colName = "";
			if (column != null)
				colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
			else
				colName = StringUtils.isBlank(id.value()) ? field.getName() : id.value();
			sql.VALUES(colName, String.format("#{%s}", field.getName()));

		}
		log.info("insert sql is :"+sql.toString());
		return sql.toString();
	}

	/**
	 * 更新实体的SQL
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String update(Object obj) throws Exception {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		sql.UPDATE(table.value());
		List<Field> list = getAllField(obj);
		Field idField = null;
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			MyId id = field.getAnnotation(MyId.class);
			if (column != null && colValue == null)
				continue;
			if (id != null) {
				idField = field;
				continue;
			}
			String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
			sql.SET(String.format("`%s` = #{%s}", colName, field.getName()));
		}
		if (idField != null) {
			MyId id = idField.getAnnotation(MyId.class);
			String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
			sql.WHERE(String.format("`%s` = #{%s}", idColName, idField.getName()));
		}
		log.info("update sql is :"+sql.toString());
		return sql.toString();
	}

	/**
	 * 通过ID获取实体的sql
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String getById(Object obj) throws Exception {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		sql.SELECT(getAllFieldString(obj));
		sql.FROM(table.value());
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		sql.WHERE(String.format("`%s` = #{%s}", idColName, idField.getName()));
		return sql.toString();
	}

	/**
	 * 根据ID删除的sql
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String deleteById(Object obj) throws Exception {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		sql.WHERE(String.format("`%s` = #{%s}", idColName, idField.getName()));
		return sql.toString();
	}

	/**
	 * 模糊查询的SQL
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String seleteVague(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.SELECT(getAllFieldString(obj));
		sql.FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` like '%s'", colName, "%" + String.valueOf(colValue) + "%"));
			}
		}
		return sql.toString();
	}

	/**
	 * 精确查询的sql生成
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String seleteAccuracy(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.SELECT(getAllFieldString(obj));
		sql.FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` = #{%s}", colName, field.getName()));
			}
		}
		return sql.toString();
	}

	/**
	 * 查询所有的sql
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String findAll(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.SELECT(getAllFieldString(obj));
		sql.FROM(table.value());
		return sql.toString();
	}

	/**
	 * 删除所有的SQL
	 * 
	 * @param obj
	 * @return
	 */
	public String deleteAll(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		return sql.toString();
	}

	/**
	 * 模糊匹配删除的SQL
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String deleteVague(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` like '%s'", colName, "%" + String.valueOf(colValue) + "%"));
			}
		}
		return sql.toString();
	}

	/**
	 * 精确匹配删除的SQL
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String deleteAccuracy(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` = #{%s}", colName, field.getName()));
			}
		}
		return sql.toString();
	}

	/**
	 * 统计总数的SQL
	 * 
	 * @param obj
	 * @return
	 */
	public String countAll(Object obj) {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		sql.SELECT("count(`" + idColName + "`)");
		sql.FROM(table.value());
		return sql.toString();
	}

	/**
	 * 模糊匹配统计总数
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String countVague(Object obj) throws Exception {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		sql.SELECT("count(`" + idColName + "`)");
		sql.FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` like '%s'", colName, "%" + String.valueOf(colValue) + "%"));
			}
		}
		return sql.toString();
	}

	/**
	 * 精确查询统计总数
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String countAccuracy(Object obj) throws Exception {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		sql.SELECT("count(`" + idColName + "`)");
		sql.FROM(table.value());
		List<Field> list = getAllField(obj);
		for (Field field : list) {
			field.setAccessible(true);
			Object colValue = field.get(obj);
			MyColumn column = field.getAnnotation(MyColumn.class);
			if (column != null && colValue != null) {
				if ((colValue instanceof String) && StringUtils.isBlank(String.valueOf(colValue)))
					continue;
				String colName = StringUtils.isBlank(column.value()) ? field.getName() : column.value();
				sql.WHERE(String.format("`%s` = #{%s}", colName, field.getName()));
			}
		}
		return sql.toString();
	}

	/**
	 * parent的更新
	 * 
	 * @param obj
	 * @return
	 */
	public String updateParentIds(Object obj) {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		sql.UPDATE(table.value());
		ParentEntity entity = ParentEntity.class.cast(obj);
		String oldstr = entity.getOldparentIds();
		String newStr = entity.getParentIds();
		String parentIds = entity.getOldparentIds() + entity.getId() + ",";
		sql.SET(String.format("parent_ids = REPLACE(parent_ids,'%s','%s')", oldstr, newStr));
		sql.WHERE(String.format("parent_ids = '%s'", parentIds));
		return sql.toString();
	}

	public String deleteByParentIdsLike(Object obj) {
		SQL sql = new SQL();
		MyTable table = obj.getClass().getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		ParentEntity parent = ParentEntity.class.cast(obj);
		sql.WHERE(String.format("parent_ids like '%s'", "%" + parent.getParentIds() + parent.getId() + "%"));
		return sql.toString();
	}

	public String selectByIds(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.SELECT(getAllFieldString(obj));
		sql.FROM(table.value());
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		String idColValue = String.valueOf(idField.get(obj));
		String ids[] = idColValue.split(",");
		StringBuilder inString = new StringBuilder(100);
		for (String idString : ids)
			inString.append(",'").append(idString).append("'");
		if (inString.length() > 0)
			inString.setCharAt(0, ' ');
		sql.WHERE("`" + idColName + "` in (" + inString.toString().trim() + ")");
		return sql.toString();
	}
	
	public String deleteByIds(Object obj) throws Exception {
		SQL sql = new SQL();
		Class<?> clazz = obj.getClass();
		MyTable table = clazz.getAnnotation(MyTable.class);
		sql.DELETE_FROM(table.value());
		Field idField = getIdField(obj);
		idField.setAccessible(true);
		MyId id = idField.getAnnotation(MyId.class);
		String idColName = StringUtils.isBlank(id.value()) ? idField.getName() : id.value();
		String idColValue = String.valueOf(idField.get(obj));
		String ids[] = idColValue.split(",");
		StringBuilder inString = new StringBuilder(100);
		for (String idString : ids)
			inString.append(",'").append(idString).append("'");
		if (inString.length() > 0)
			inString.setCharAt(0, ' ');
		sql.WHERE("`" + idColName + "` in (" + inString.toString().trim() + ")");
		return sql.toString();
	}

	/**
	 * 获取所有的带有@MyId和@MyColumn的列
	 * 
	 * @param obj
	 * @return
	 */
	private List<Field> getAllField(Object obj) {
		Class<?> clazz = obj.getClass();
		List<Field> list = new ArrayList<Field>();
		while (true) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				MyColumn column = field.getAnnotation(MyColumn.class);
				MyId id = field.getAnnotation(MyId.class);
				if (column != null || id != null)
					list.add(field);
			}
			if (clazz.getSuperclass() == Object.class)
				break;
			clazz = clazz.getSuperclass();
		}
		return list;
	}

	/**
	 * 获取所有的带有@MyId和@MyColumn的列的，inser，select字符串
	 * 
	 * @param obj
	 * @return
	 */
	private String getAllFieldString(Object obj) {
		StringBuilder sb = new StringBuilder(300);
		Class<?> clazz = obj.getClass();
		while (true) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				MyColumn column = field.getAnnotation(MyColumn.class);
				MyId id = field.getAnnotation(MyId.class);
				if (column != null)
					sb.append("`").append(StringUtils.isBlank(column.value()) ? field.getName() : column.value()).append("`");
				else if (id != null)
					sb.append("`").append(StringUtils.isBlank(id.value()) ? field.getName() : id.value()).append("`");
				if (column != null || id != null)
					sb.append(" as ").append(field.getName()).append(",");
			}
			if (clazz.getSuperclass() == Object.class)
				break;
			clazz = clazz.getSuperclass();
		}
		sb.setCharAt(sb.length() - 1, ' ');
		return sb.toString();
	}

	/**
	 * 获取带有@MyId的field
	 * 
	 * @param obj
	 * @return
	 */
	private Field getIdField(Object obj) {
		Class<?> clazz = obj.getClass();
		Field idField = null;
		while (true) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				MyId id = field.getAnnotation(MyId.class);
				if (id != null) {
					idField = field;
					break;
				}
			}
			if (idField != null || clazz.getSuperclass() == Object.class)
				break;
			clazz = clazz.getSuperclass();
		}
		return idField;
	}
}
