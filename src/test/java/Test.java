import com.bll.BLL;
import com.bll.PersonBLL;
import com.ui.components.MyTable;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        BLL bll =new PersonBLL();
        JFrame f=new JFrame();
        f.setSize(800,600);
        f.setLayout(new BorderLayout());
        var model=bll.toTable();
        MyTable t=new MyTable(model);
        JScrollPane b=new JScrollPane();
        b.setViewportView(t);
        f.add(b,BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
