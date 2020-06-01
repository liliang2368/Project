package com.ly.test;

import java.sql.SQLException;

import org.junit.Test;

import com.ly.util.DBHelper;

public class testMySql {
	DBHelper db=new DBHelper();
	@Test
	public void testMySql() throws SQLException {
		System.out.println(db.getConn().getClass().getName());
	}
	@Test
	public void testFOOD() throws SQLException {
		System.out.println(db.findMutil("select *from home"));
	}
}
