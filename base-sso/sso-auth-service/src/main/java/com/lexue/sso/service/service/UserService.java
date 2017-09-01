package com.lexue.sso.service.service;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.relation.UserRoleRelation;
import com.lexue.sso.service.mapper.UserMapper;
import com.lexue.sso.service.mapper.UserRoleRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户的服务
 * 
 */
@Service
public class UserService extends BaseService<UserMapper, User> {
	@Autowired
	private UserRoleRelationMapper userRoleRelationMapper;
	/**
	 * 根据角色ID查询用户
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 用户列表
	 */
	@Transactional(readOnly = true)
	public List<User> getUserByRoleId(String roleId) {
		return mapper.getUserByRoleId(roleId);
	}

	/**
	 * 获取用户所有的鉴权码
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> getUserAllAuthCodes(String userId) {
		return mapper.getUserAllAuthCodes(userId);
	}

	/**
	 * 获取用户所有的菜单Id
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> getUserMenuIds(String userId) {
		return mapper.getUserAllMenuIds(userId);
	}

	@Transactional(readOnly = true)
	public List<String> getRids(String userId){
		List<UserRoleRelation> list=userRoleRelationMapper.getRelationToUserId(userId);
		List<String> stringList=new ArrayList<>();
		for(UserRoleRelation user:list){
			stringList.add(user.getRoleId());
		}
		return stringList;
	}
	@Transactional(readOnly = false)
	public void addUser(User user){
		mapper.insert(user);
		updateUserRole(user);
	}
	@Transactional(readOnly = false)
	public void updateUser(User user){
		mapper.update(user);
		updateUserRole(user);
	}
	private void updateUserRole(User user){
		if(user.getRoleIds()!=null){
			userRoleRelationMapper.deleteToUserId(user.getId());
			List<UserRoleRelation> list=UserRoleRelation.assemblyRelation(user);
			for(UserRoleRelation userRoleRelation:list){
				userRoleRelationMapper.insert(userRoleRelation);
			}
		}
	}
	public boolean checkLoginName(String loginName,String id){
		User user=mapper.checkLoginName(loginName,id);
		if(user==null){
			return false;
		}
		return true;
	}
}
