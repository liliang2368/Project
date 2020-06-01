
package com.ly.util;

import java.util.Map;
import java.util.prefs.Preferences;

/**
 * 操作注册表
 * @author Administrator
 *
 */
public class RegisterUtils {

	/**
	 * 记住密码，保存一个map到注册表中
	 * @param entry
	 */
	public static void add(Map<String, String> entry){
		/*首选项数据的层次结构 collection 中的节点。此类允许应用程序存储和获取用户和系统首选项以及配置数据。
		此数据持久存储在依赖于实现的内部存储中。典型实现包括纯文本文件、特定于操作系统的注册表、目录服务器和 SQL 数据库。
		此类的用户无需关注内部存储的细节。*/
		
		//从调用用户首选项树（根据约定，它与指定类的包相关联）返回首选项节点
		Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
		if(entry!=null){
			for(Map.Entry<String, String> m:entry.entrySet() ){
				pre.put(m.getKey(), m.getValue());
			}
		}	
	}
	
	//删除注册表信息
		public static void deleteInfo(String key){
			Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
			pre.remove(key);
			
		}
	
	//查看注册表信息，是否记住了登录密码
	public static String findInfo(String key){
		Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
		String result=pre.get(key, null);
		return result;
	}
	
	
	
	
	
}
