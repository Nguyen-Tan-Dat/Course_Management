package com.ui;

import javax.swing.*;
import java.awt.*;

public class CourseInstructorPanel extends Panel {
	public CourseInstructorPanel(){
		setLayout(new BorderLayout());
//		JTable table
	}

	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.add(new CourseInstructorPanel());
		f.setSize(1000,800);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
