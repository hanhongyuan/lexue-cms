/**
 * 
 */
package com.lexue.sso.service.service;

import com.lexue.base.domain.Menu;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 菜单的service
 * <P>
 * 
 */
@Service
public class MenuService extends BaseService<MenuMapper, Menu> {
    @Transactional(readOnly = true)
    public List<Menu> getUserMenuTree(String userId) {
        return mapper.getUserMenuTree(userId);
    }
    @Transactional(readOnly = true)
    public List<Menu> findUserChindMenu(String id) {
        return mapper.findUserChindMenu(id);
    }
    @Transactional(readOnly = true)
    public List<Menu> findChindMenu(String id) {
        return mapper.findChindMenu(id);
    }
    @Transactional(readOnly = true)
    public List<Menu> findChindMenuToUserId(String id,String userId) {
        return mapper.findChindMenuToUserId(id,userId);
    }
    @Transactional(readOnly = true)
    public List<Menu> findAllMenu() {
        return mapper.findAllMenu();
    }
    @Transactional(readOnly = true)
    public List<Menu> getAllMenuToRoleId(String roleId){
        return mapper.getAllMenuToRoleId(roleId);
    }
    @Transactional(readOnly = true)
    public List<Menu> getUserMenuToUserId(String userId){
        return mapper.getUserMenuToUserId(userId);
    }
//	@Override
//	public Menu getById(Object id) {
//		Menu menu = super.getById(id);
//		if (StringUtils.isNotBlank(menu.getParentId())) {
//			Menu parent = super.getById(menu.getParentId());
//			if (parent != null)
//				menu.setParentName(parent.getName());
//		}
//		return menu;
//	}
//
//	/*
//	 * （非 Javadoc）
//	 * 
//	 * @see com.wolfking.back.core.mybatis.BaseService#update(java.lang.Object)
//	 */
//	@Transactional
//	public boolean update(Menu menu) {
//		mapper.update(menu);
//		String parentIds = menu.getParentIds() + menu.getId();
//		mapper.updateParentIdsByParentId(menu.getId(), parentIds);
//		return true;
//	}
//
//	@Transactional
//	public boolean deleteById(Object id) {
//		super.deleteById(id);
//		Menu menu = new Menu();
//		if (id instanceof String)
//			menu.setParentId(String.valueOf(id));
//		else
//			menu.setParentId(Menu.class.cast(id).getId());
//		mapper.deleteAccuracy(menu);
//		return true;
//	}
}
