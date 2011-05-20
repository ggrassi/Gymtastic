package ch.hsr.gymtastic.server.presentation.editor;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class MarksEditor implements TableCellEditor {

    private JTextField textField = new JTextField();
    private List<CellEditorListener> listeners = new ArrayList<CellEditorListener>();

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	textField.setText(String.valueOf(value));
	return textField;
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
	listeners.add(l);

    }

    @Override
    public void cancelCellEditing() {
	ChangeEvent event = new ChangeEvent(this);
	for (CellEditorListener listener : listeners.toArray(new CellEditorListener[listeners.size()])) {

	    listener.editingCanceled(event);
	}
    }

    @Override
    public Object getCellEditorValue() {
	return Double.parseDouble(textField.getText());
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
	return true;
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
	listeners.remove(l);

    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
	return true;
    }

    @Override
    public boolean stopCellEditing() {
	ChangeEvent event = new ChangeEvent(this);
	for (CellEditorListener listener : listeners.toArray(new CellEditorListener[listeners.size()])) {

	    listener.editingStopped(event);
	}
	return true;
    }

}
