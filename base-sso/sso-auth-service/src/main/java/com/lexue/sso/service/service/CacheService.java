package com.lexue.sso.service.service;

import com.lexue.base.domain.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * token缓存的服务
 * 
 */
@Service
public class CacheService {
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private UserService userService;

	/**
	 * 保存tokenID和userId到缓存
	 * 
	 * @param user
	 */
	public void saveToken(User user) {
		Cache cache = cacheManager.getCache("tokencache");
		Element element = new Element(user.getTokenId(), user.getId());
		cache.put(element);
		cache.flush();

		Cache cacheU = cacheManager.getCache("usercache");
		Element elementU = new Element(user.getId(), user.getTokenId());
		cacheU.put(elementU);
		cacheU.flush();
	}
	/**
	 * 保存MenuLIst和userId到缓存
	 *
	 * @param user
	 */
	public void saveMenu(List<String> list, User user) {
		Cache cache = cacheManager.getCache("menucache");
		Element element = new Element(user.getId(),list);
		cache.put(element);
		cache.flush();
	}
	/**
	 * 从缓存获取登录用户的menu
	 *
	 */
	public List<String> getMenuToUserId(String userId) {
		Cache cache = cacheManager.getCache("menucache");
		Element element = cache.get(userId);
		if (element == null)
			return null;
		return  (List<String>) element.getObjectValue();
	}

	/**
	 * 从缓存获取登录的数据
	 * 
	 */
	public User getTokenUser(String tokenId) {
		Cache cache = cacheManager.getCache("tokencache");
		Element element = cache.get(tokenId);
		if (element == null)
			return null;
		User user = userService.getById(String.valueOf(element.getObjectValue()));
		user.setTokenId(tokenId);
		return user;
	}

	public void clearToken(String tokenId) {
		Cache cache = cacheManager.getCache("tokencache");
		Cache cacheU = cacheManager.getCache("usercache");
		Cache cacheM = cacheManager.getCache("menucache");
		Element element = cache.get(tokenId);
		if (element != null) {
			String userId = element.getObjectValue().toString();
			cache.remove(tokenId);
			cacheU.remove(userId);
			cacheM.remove(userId);
		}
	}
}
