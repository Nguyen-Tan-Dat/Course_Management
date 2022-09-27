package com.ui;

import com.bll.PersonBL;
import com.ui.components.Field;
import com.ui.components.GoogleMaterialDesignIcons;
import com.ui.components.ItemWrap;
import com.ui.components.MyColor;
import com.utils.HashPass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingPanel extends UIPanel {

    public SettingPanel() {
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        var foreground = MyColor.text;
        var background = MyColor.white;
        var hoverBackground = MyColor.light_primary;
        var changePassword = new ItemWrap(GoogleMaterialDesignIcons.VPN_KEY, "Đổi mật khẩu", background, foreground, hoverBackground);
        var logOut = new ItemWrap(GoogleMaterialDesignIcons.ASSIGNMENT_RETURN, "Đăng xuất", background, foreground, hoverBackground);
        menu.add(changePassword);
        menu.add(logOut);
        changePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePassword();
            }
        });
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logout();
            }
        });
        setLayout(new BorderLayout());
        add(menu, BorderLayout.CENTER);
    }

    @Override
    public UIPanel newView() {
        return new SettingPanel();
    }

    public void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                getRootPane(),
                "Xác nhận đăng xuất người dùng " + MainFrame.getEmployee().getData()[0], "Chú ý",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == 0) {
            JFrame f3 = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.dispose();
//            new LoginFrame().setVisible(true);
        }
    }

    public void changePassword() {
        Field field = new Field(new String[]{
                "Mật khẩu cũ",
                "Mật khẩu mới",
                "Nhập lần nữa"
        });
        field.setAction(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var info = field.getInfo();
                if (info == null) return;
                if (HashPass.isPassHash(info[0].toCharArray(), MainFrame.getEmployee().getData()[4])) {
                    if( info[1].equals(info[2])){
                        MainFrame.getEmployee().getData()[4]=HashPass.encode(info[1].toCharArray());
                        (new PersonBL()).update(MainFrame.getEmployee_id(), MainFrame.getEmployee());
                        JOptionPane.showMessageDialog(getRootPane(),"Mật khẩu đã được thay đổi");
                        MainFrame.windowChild.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(getRootPane(),"Mật khẩu mới không trùng khới");
                    }
                }else{
                    JOptionPane.showMessageDialog(getRootPane(),"Sai mật khẩu");
                }
            }
        });
        MainFrame.openChild("Đổi mật khẩu",field, 350, 500);
    }
}
