/**********************************************
 * [Copyright ©]
 * @File: AUtil.java 
 * @author Frank-Wang
 * @Date: 2019.05.16
 * @Version: 1.0
 * @Since: JDK 1.8.0_92
 **********************************************/
package com.anselm.tools.agile;

import java.util.HashMap;

import com.agile.api.APIException;
import com.agile.api.AgileSessionFactory;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;
import com.agile.api.ITable;
import com.agile.api.ITwoWayIterator;

/**
 * &emsp;&emsp;&emsp;&emsp;<em><b>Class AUtil</em></b><BR/>
 * <BR/>
 * 
 * @author Frank-Wang
 */
public class AUtil {
	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getAgileSession</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getAgileSession({@linkplain #String},
	 * {@linkplain #String}, {@linkplain #String})</font><BR/>
	 * <BR/>
	 * 
	 * @param agileurl
	 * @param agileusr
	 * @param agilepwd
	 * @return
	 */
	public static IAgileSession getAgileSession(String agileurl, String agileusr, String agilepwd) {
		try {
			HashMap<Integer, String> params = new HashMap<Integer, String>();
			params.put(AgileSessionFactory.USERNAME, agileusr);
			params.put(AgileSessionFactory.PASSWORD, agilepwd);
			params.put(AgileSessionFactory.URL, agileurl);
			return com.agile.api.AgileSessionFactory.createSessionEx(params);
		} catch (Exception e) {
			System.out.println("<getAgileSession>error: " + agileusr + "/" + agilepwd + "," + agileurl);
			e.printStackTrace();
			return null;
		}
	}

	public static ITwoWayIterator tableRowSortingWithASC(ITable iTable, IAgileClass iAgileClass, Integer baseID)
			throws APIException {
		ITable.ISortBy tableSort = iTable.createSortBy(iAgileClass.getAttribute(baseID),
				ITable.ISortBy.Order.ASCENDING);
		ITwoWayIterator iterator = iTable.getTableIterator(new ITable.ISortBy[] { tableSort });

		return iterator;
	}

	public static ITwoWayIterator tableRowSortingWithDESC(ITable iTable, IAgileClass iAgileClass, Integer baseID)
			throws APIException {
		ITable.ISortBy tableSort = iTable.createSortBy(iAgileClass.getAttribute(baseID),
				ITable.ISortBy.Order.DESCENDING);
		ITwoWayIterator iterator = iTable.getTableIterator(new ITable.ISortBy[] { tableSort });

		return iterator;
	}
}
