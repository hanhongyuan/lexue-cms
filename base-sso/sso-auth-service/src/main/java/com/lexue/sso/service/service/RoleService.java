package com.lexue.sso.service.service;

import com.lexue.base.domain.Role;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.relation.RoleMenuRelation;
import com.lexue.base.relation.UserRoleRelation;
import com.lexue.sso.service.mapper.RoleMapper;
import com.lexue.sso.service.mapper.RoleMenuRelationMapper;
import com.lexue.sso.service.mapper.UserRoleRelationMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 角色的service
 * 
 */
@Service
public class RoleService extends BaseService<RoleMapper, Role> {

	@Autowired
	private RoleMenuRelationMapper roleMenuRelationMapper;

	@Autowired
	private UserRoleRelationMapper userRoleRelationMapper;

	@Override
	@Transactional
	public boolean add(Role role) {
		if (StringUtils.isEmpty(role.getId()))
			role.setId(UUID.randomUUID().toString().replace("-", ""));
		mapper.insert(role);
		List<RoleMenuRelation> roleMenuRelations = RoleMenuRelation.assemblyRelation(role);
		for (RoleMenuRelation roleMenuRelation : roleMenuRelations)
			roleMenuRelationMapper.insert(roleMenuRelation);
		return true;
	}

	@Override
	@Transactional
	public boolean update(Role role) {
		RoleMenuRelation relationM = new RoleMenuRelation();
		relationM.setRoleId(role.getId());
		roleMenuRelationMapper.deleteAccuracy(relationM);
		mapper.update(role);
		List<RoleMenuRelation> relations = RoleMenuRelation.assemblyRelation(role);
		for (RoleMenuRelation roleMenuRelation : relations)
			roleMenuRelationMapper.insert(roleMenuRelation);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(Object id) {
		Role role = new Role();
		role.setId(String.valueOf(id));
		mapper.deleteById(role);
		RoleMenuRelation relationM = new RoleMenuRelation();
		relationM.setRoleId(role.getId());
		roleMenuRelationMapper.deleteAccuracy(relationM);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Role getById(Object id) {
		Role role = new Role();
		role.setId(String.valueOf(id));
		role = mapper.getById(role);
		RoleMenuRelation relationRM = new RoleMenuRelation();
		relationRM.setRoleId(role.getId());
		List<RoleMenuRelation> listRM = roleMenuRelationMapper.seleteAccuracy(relationRM);
		String menuIds = "", officeIds = "";
		if (listRM != null)
			for (RoleMenuRelation roleMenuRelation : listRM)
				menuIds += "," + roleMenuRelation.getMenuId();
		if (menuIds.length() > 0)
			menuIds = menuIds.substring(1);
		role.setMenuIds(menuIds);
		return role;
	}

	@Transactional
	public void outrole(String roleId, String userId) {
		UserRoleRelation userRoleRelation = new UserRoleRelation(userId, roleId);
		userRoleRelationMapper.deleteAccuracy(userRoleRelation);
	}

	@Transactional
	public void assignrole(String roleId, String userId) {
		UserRoleRelation userRoleRelation = new UserRoleRelation(userId, roleId);
		userRoleRelationMapper.deleteAccuracy(userRoleRelation);
		userRoleRelationMapper.insert(userRoleRelation);
	}
	public List<Role> findPage(int pageIndex,int pageSize){
		return mapper.findPage((pageIndex-1)*pageSize,pageSize);
	}
	public int findPageCount(){
		return mapper.findPageCount();
	}

	public String getMenuIdsToRoleId(String id){
		List<RoleMenuRelation> list=roleMenuRelationMapper.getMenuIdsToRoleId(id);
		StringBuffer stringBuffer=new StringBuffer();
		for(RoleMenuRelation rm:list){
			stringBuffer.append(rm.getMenuId()).append(",");
		}
		return stringBuffer.toString();
	}
	public List<Role> findAllRole(){
		return mapper.findAllRole();
	}
}
