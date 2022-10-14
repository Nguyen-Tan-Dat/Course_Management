package BLL;

import com.bll.BLL;
import com.bll.PersonBLL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

class BLLTest {
    private BLL bll;

    @BeforeEach
    void setUp() {
        bll=new PersonBLL();
    }

    @Test
    void readTable() {
        System.out.println(bll);
    }

    @Test
    void add() {
    }

    @Test
    void getList() {
    }

    @Test
    void getNewID() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void searchAllAttributes() {
    }

    @Test
    void testSearchAllAttributes() {
    }

    @Test
    void testToString() {
    }

    @Test
    void toComboBox() {
        JFrame f=new JFrame();
        f.setSize(800,600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        var model=bll.toTable();
        JTable t=new JTable(model);
        JScrollPane b=new JScrollPane();
        b.add(t);
        f.add(b,BorderLayout.CENTER);
    }
}