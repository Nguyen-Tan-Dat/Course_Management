package com.gui;

import com.gui.components.Action;
import com.gui.components.ActionBar;
import com.gui.components.Field;
import com.gui.components.GoogleMaterialDesignIcons;
import com.bll.BLL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagementPanel extends SeePanel {
	protected final ActionBar actionBar;

	public ManagementPanel(BLL control) {
		super(control);
		actionBar = new ActionBar(new Action[]{
				new Action("Reload", GoogleMaterialDesignIcons.REFRESH, new Color(104, 46, 206), reload()),
				new Action("Add", GoogleMaterialDesignIcons.ADD, new Color(104, 46, 206), add()),
				new Action("Update", GoogleMaterialDesignIcons.UPDATE, new Color(104, 46, 206), update()),
				new Action("Delete", GoogleMaterialDesignIcons.DELETE, new Color(190, 45, 45), delete()),
		}, top.getBackground());
		top.add(actionBar, BorderLayout.WEST);
	}

	private MouseAdapter reload() {
		return  new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(control.toTable());
			}
		};
	}

	@Override
	public UIPanel newView() {
		return new ManagementPanel(control);
	}

	private MouseAdapter delete() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sl = table.getSelectedRow();
				if (sl == -1) {
					JOptionPane.showMessageDialog(getRootPane(), "Chooser and Delete");
					return;
				}
				int ok = JOptionPane.showConfirmDialog(getRootPane(), "Delete", "Delete", JOptionPane.OK_CANCEL_OPTION);
				if (ok == 0) {
					String id = table.getValueAt(sl, 0).toString();
					if (control.delete(id))
						MainFrame.setView(newView());
					else JOptionPane.showMessageDialog(getRootPane(),"No delete");
				}
			}
		};
	}

	public MouseAdapter update() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sl = table.getSelectedRow();
				if (sl == -1) {
					JOptionPane.showMessageDialog(getRootPane(), "Chooser row and update");
					return;
				}
				String id = table.getValueAt(sl, 0).toString();
				String[] infoOld = new String[table.getColumnCount() - 1];
				for (int i = 1; i < table.getColumnCount(); i++) {
					String val="";
					try {
						val=table.getValueAt(sl, i).toString();
					}catch (Exception igr){}
					infoOld[i - 1] = val;
				}
				String[] names = new String[table.getColumnCount() - 1];
				for (int i = 1; i < table.getColumnCount(); i++)
					names[i - 1] = table.getColumnName(i);
				Field field = new Field(names, infoOld);
				field.setAction(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						var info = field.getInfo();
						if (info != null) {
							control.update(id, control.getDTO().getDTO(info));
							MainFrame.windowChild.dispose();
							MainFrame.setView(newView());
						}
					}
				});
				MainFrame.openChild("Update field", field, 350, 500);
			}
		};
	}

	public MouseAdapter add() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] names = new String[table.getColumnCount() - 1];
				for (int i = 1; i < table.getColumnCount(); i++)
					names[i - 1] = table.getColumnName(i);
				Field field = new Field(names);
				field.setAction(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						var info = field.getInfo();
						if (info != null && checkInput(info)) {
							control.add(control.getDTO().getDTO(info));
							MainFrame.windowChild.dispose();
							MainFrame.setView(newView());
						}
					}
				});
				MainFrame.openChild("Field", field, 350, 500);
			}
		};
	}

	public boolean checkInput(String[] info) {
		return true;
	}
}
