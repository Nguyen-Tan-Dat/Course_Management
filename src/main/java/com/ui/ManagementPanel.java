package com.ui;

import com.bll.BLL;
import com.dto.DTO;
import com.ui.components.ActionBar;
import com.ui.components.Field;
import com.ui.components.GoogleMaterialDesignIcons;
import com.ui.components.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagementPanel extends SeePanel {
	protected final ActionBar actionBar;

	public ManagementPanel(BLL control) {
		super(control);
		actionBar = new ActionBar(new com.ui.components.Action[]{
				new com.ui.components.Action("Thêm", GoogleMaterialDesignIcons.ADD, new Color(104, 46, 206), add()),
				new com.ui.components.Action("Cập nhật", GoogleMaterialDesignIcons.UPDATE, new Color(104, 46, 206), update()),
				new Action("Xóa", GoogleMaterialDesignIcons.DELETE, new Color(190, 45, 45), delete()),
		}, top.getBackground());
		top.add(actionBar, BorderLayout.WEST);
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
					JOptionPane.showMessageDialog(getRootPane(), "Chọn nhấp chọn dòng sau đó nhấn xóa");
					return;
				}
				int ok = JOptionPane.showConfirmDialog(getRootPane(), "Xác nhận xóa", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
				if (ok == 0) {
					String id = table.getValueAt(sl, 0).toString();
					if (control.delete(id))
						MainFrame.setView(newView());
					else JOptionPane.showMessageDialog(getRootPane(),"Không thể xóa do dữ liệu có ràng buộc");
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
					JOptionPane.showMessageDialog(getRootPane(), "Nhấn chọn dòng sau đó nhấn cập nhật");
					return;
				}
				String id = table.getValueAt(sl, 0).toString();
				String[] infoOld = new String[table.getColumnCount() - 1];
				for (int i = 1; i < table.getColumnCount(); i++) {
					infoOld[i - 1] = table.getValueAt(sl, i).toString();
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
							control.update(id, new DTO(info));
							MainFrame.windowChild.dispose();
							MainFrame.setView(newView());
						}
					}
				});
				MainFrame.openChild("Thông tin cập nhật", field, 350, 500);
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
							control.add(new DTO(info));
							MainFrame.windowChild.dispose();
							MainFrame.setView(newView());
						}
					}
				});
				MainFrame.openChild("Thông tin", field, 350, 500);
			}
		};
	}

	public boolean checkInput(String[] info) {
		return true;
	}
}
