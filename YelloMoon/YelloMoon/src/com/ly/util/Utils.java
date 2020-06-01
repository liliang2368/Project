package com.ly.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class Utils {
	/**
	 * 将集合元素转换成对应实体对象
	 */
	public static <T>List<T>castElement(List<Map<String,Object>>list,Class<T>cls){
		List<T>ret=new ArrayList<T>();
		for (Map<String,Object>map : list) {
			try {
				T f=cls.newInstance();//Food.class.newInstance()===>new Food()
				BeanUtils.populate(f, map);
				ret.add(f);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}
}
