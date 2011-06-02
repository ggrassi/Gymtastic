package ch.hsr.gymtastic.server.presentation.editor;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ch.hsr.gymtastic.domain.DeviceType;


/**
 * The Class DeviceTypeEditor.
 */
public class DeviceTypeEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -4196594602891517644L;

	/**
	 * Instantiates a new device type editor.
	 *
	 * @param comboBox the combo box
	 */
	public DeviceTypeEditor(JComboBox comboBox) {
		super(comboBox);
		JComboBox cb = (JComboBox) getComponent();
		cb.setModel(new DefaultComboBoxModel(DeviceType.values()));
	}

}
