package com.inc.jmosprograms.multidimension.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

/**
 *
 * ********************************* IMPLEMENTACION MYSQL
 * *********************************
 *
 *
 * @author Juan Miguel Olguin Salguero
 *
 */
@Component
public class MultidimensionDAO {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.inc.jmosprograms.multidimension.dao.MultidimensionDAO#
	 * findAllMultidimensionResultset(java.util.ArrayList)
	 */
	public ArrayList<String> findAllMultidimensionResultset(ArrayList<String> queries) throws Exception {
		ArrayList<String> fileContentList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/machinelearning", "root",
					"password");
			for (Iterator<String> iterator = queries.iterator(); iterator.hasNext();) {
				String query = iterator.next();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				ResultSetMetaData meta = rs.getMetaData();
				// Imprimir los labels de la columna
				StringBuffer stringColumnNames = new StringBuffer();
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					stringColumnNames.append(meta.getColumnLabel(i) + ",");
				}
				StringBuffer completeFileContent = new StringBuffer();
				String columnNames = stringColumnNames.substring(0, stringColumnNames.lastIndexOf(","));
				completeFileContent.append(columnNames + "\n");
				while (rs.next()) {
					StringBuffer rowStr = new StringBuffer();
					for (int coln = 1; coln <= meta.getColumnCount(); coln++) {
						if (meta.getColumnType(coln) == Types.DATE) {
							Date date = rs.getDate(coln);
							String dateStr = sdf.format(date);
							rowStr.append(dateStr + ",");
						} else {
							rowStr.append(rs.getString(coln) + ",");
						}
					}
					String rowFinal = rowStr.substring(0, rowStr.lastIndexOf(",")) + "\n";
					completeFileContent.append(rowFinal);
				}
				completeFileContent = new StringBuffer(
						completeFileContent.substring(0, completeFileContent.lastIndexOf("\n")));
				rs.close();
				stmt.close();
				fileContentList.add(completeFileContent.toString());
			}

			conn.close();
		} catch (Exception se) {
			throw new Exception(se);
		}
		return fileContentList;

	}

}
