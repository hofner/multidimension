package com.inc.jmosprograms.multidimension.sequencer;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VariableTableModel implements TableModel {

	@Autowired
	ScriptReader reader;
	String content;

	public VariableTableModel() {

	}

	void init(String name) {
		content = reader.readFile(name);

	}

	@Override
	public int getRowCount() {
		return content.split("\n").length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return content.split("\n")[rowIndex].split("\t")[columnIndex];
	}

	/// Los demas metodos no son de mi interes implementarlos
	@Override
	public int getColumnCount() {
		return content.split("\n")[0].split("\t").length;

	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
