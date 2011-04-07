package ch.hsr.gymtastic.application.editor;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ch.hsr.gymtastic.domain.DeviceType;


public class DeviceTypeEditor extends DefaultCellEditor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4196594602891517644L;

	public DeviceTypeEditor(JComboBox comboBox) {
		super(comboBox);
		JComboBox cb = (JComboBox) getComponent();
		cb.setModel(new DefaultComboBoxModel(DeviceType.values()));
	}

}
