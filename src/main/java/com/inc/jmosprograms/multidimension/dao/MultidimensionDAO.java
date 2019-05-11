package com.inc.jmosprograms.multidimension.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inc.jmosprograms.multidimension.config.ApplicationProperties;
import com.inc.jmosprograms.multidimension.vo.ExpandItem;

/*
 * @author Juan Miguel Olguin Salguero
 *
 */
@Component
public class MultidimensionDAO {
	@Autowired
	ApplicationProperties props;

	public void executeAndSaveResultset(ArrayList<ExpandItem> queries) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Class.forName(props.getDriverClassName());
			Connection conn = DriverManager.getConnection(props.getJdbcUrl(), props.getUserName(), props.getPassword());
			for (Iterator<ExpandItem> iterator = queries.iterator(); iterator.hasNext();) {
				ExpandItem queryObj = iterator.next();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(queryObj.getEvaluatedExpression());
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
				String path = props.getProjectRoot();
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(path + "\\" + queryObj.getVariable() + "\\" + queryObj.getFileNameResultset()));
				bw.write(completeFileContent.toString());
				bw.close();
				rs.close();
				stmt.close();

			}

			conn.close();
		} catch (Exception se) {
			throw new Exception(se);
		}

	}

}
