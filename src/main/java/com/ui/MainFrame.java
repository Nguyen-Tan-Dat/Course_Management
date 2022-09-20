package com.ui;

import com.bll.*;
import com.dto.DTO;
import com.ui.components.Function;
import com.ui.components.FunctionBar;
import com.ui.components.GoogleMaterialDesignIcons;
import com.ui.components.TopBar;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainFrame extends JFrame {
    public static JPanel center;
    private static final TopBar top = new TopBar();
    private static String employee_id;
    private static DTO employee;
    public static JDialog windowChild;

    public static String getEmployee_id() {
        return employee_id;
    }

    public static DTO getEmployee() {
        return employee;
    }

    public static void setEmployee_id(String employee_id) {
        MainFrame.employee_id = employee_id;
    }

    public static void setEmployee(DTO employee) {
        MainFrame.employee = employee;
    }

    public static ArrayList<Function> functions=new ArrayList<>();
    private static final Function[] allFunction = {
            new Function("Person", GoogleMaterialDesignIcons.PEOPLE, new ManagementPanel(new PersonBL())),
            new Function("Course Instructor", GoogleMaterialDesignIcons.GROUP, new ManagementPanel(new CourseInstructorBL())),
            new Function("Department", GoogleMaterialDesignIcons.WEB, new ManagementPanel(new DepartmentBL())),
            new Function("Office Assignment", GoogleMaterialDesignIcons.ASSIGNMENT, new ManagementPanel(new OfficeAssignmentBL())),
            new Function("Online Course", GoogleMaterialDesignIcons.LANGUAGE, new ManagementPanel(new OnlineCourseBL())),
            new Function("Onsite Course", GoogleMaterialDesignIcons.CLASS, new ManagementPanel(new OnsiteCourseBL())),
            new Function("Course", GoogleMaterialDesignIcons.DEVELOPER_BOARD, new ManagementPanel(new CourseBL())),
            new Function("Student Grade", GoogleMaterialDesignIcons.GRADIENT, new ManagementPanel(new StudentGradeBL())),
    };
    private void activeFunction(){
        functions = new ArrayList<>();
        Collections.addAll(functions, allFunction);
//        functions.add(new Function("Cài đặt", GoogleMaterialDesignIcons.SETTINGS, new SettingView()));
    }
    private void activeFunction(String[] active) {
        functions = new ArrayList<>();
        for (var function : allFunction) {
            for (String s : active) {
                if (function.name().equals(s))
                    functions.add(function);
            }
        }
        functions.add(new Function("Cài đặt", GoogleMaterialDesignIcons.SETTINGS, new SettingPanel()));
    }

    public static void openChild(String title,JPanel view, int width, int height) {
        windowChild.setTitle(title);
        MainFrame.windowChild.setContentPane(view);
        MainFrame.windowChild.setSize(width, height);
        MainFrame.windowChild.setVisible(true);
    }

    public MainFrame(){
        activeFunction();
        init();
    }

    public 	MainFrame(String[] actives) {
        activeFunction(actives);
        init();
    }
    private void init(){
        windowChild = new JDialog(this);
        windowChild.setModal(true);
        windowChild.setResizable(false);
        windowChild.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        windowChild.setLocation(0, 0);
        windowChild.setSize(700, 700);
        windowChild.setLocationRelativeTo(null);
        setTitle("Hệ Thống Quản Lý Khóa Học");
        ImageIcon logo = (ImageIcon) IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SCHOOL, 100, new Color(58, 121, 199));
        setIconImage(logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1300, 700));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(0, 0));
        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setBorder(new MatteBorder(0, 0, 20, 20, getBackground()));
        var home = new HomePanel(functions);
        functions.add(0, new Function("Trang chủ", GoogleMaterialDesignIcons.HOME, home));
        setView("Trang chủ");
        center.add(home, BorderLayout.CENTER);
        getContentPane().add(center, BorderLayout.CENTER);
        FunctionBar functionBar = new FunctionBar(functions, getBackground());
        getContentPane().add(functionBar, BorderLayout.WEST);
//        top.setAccount(employee.getData()[0]);
        getContentPane().add(top, BorderLayout.NORTH);
    }
    public static void setView(PanelUI panelUI){
        center.removeAll();
        center.add(panelUI);
        center.repaint();
        center.revalidate();
    }

    public static void setView(String name) {
        PanelUI panelUI = null;
        for (var i : functions)
            if (i.name().equals(name)) {
                panelUI = i.panelUI().newView();
                break;
            }
        if (panelUI == null) return;
        center.removeAll();
        center.add(panelUI);
        center.repaint();
        center.revalidate();
        top.setFunction(name);

    }

    public static void main(String[] args) {
        new UIManager();
        MainFrame f=new MainFrame();
        f.setVisible(true);
    }
}