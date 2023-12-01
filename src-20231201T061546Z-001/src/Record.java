import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class Record extends JFrame implements ActionListener {
    JButton cancel, submit,previ,back;

    JRadioButton ab, pr;
    JTextField rollNumber;

    Record(String roll) {
        super("Record Detail");
        setSize(600, 400);
        setLocation(400, 200);

        JLabel heading = new JLabel("Record Details");
        heading.setBounds(250, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        pr = new JRadioButton("Present");
        pr.setBounds(200, 80, 100, 20);
        add(pr);

        ab = new JRadioButton("Absent");
        ab.setBounds(320, 80, 100, 20);
        add(ab);

        ButtonGroup group = new ButtonGroup();
        group.add(pr);
        group.add(ab);

        JLabel headin=new JLabel ("Roll Number");
        headin.setBounds(100,130,150,20);
        add(headin);

        rollNumber = new JTextField(roll);
        rollNumber.setBounds(200, 130, 200, 30);
        rollNumber.setEditable(false);
        add(rollNumber);





        submit = new JButton("Submit");
        submit.setBounds(180, 320, 100, 25);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(280, 320, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        back = new JButton("Back");
        back.setBounds(80, 320, 100, 25);
        back.addActionListener(this);
        add(back);

        previ = new JButton("Attendence Detail");
        previ.setBounds(380, 320, 150, 25);
        previ.addActionListener(this);
        add(previ);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.GRAY);
        setVisible(true);
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String rolln = rollNumber.getText();
            String status = (pr.isSelected()) ? "Present" : "Absent";

            try {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String query = "insert into record values(current_date(),current_time(),'" + status + "','" + rolln + "')";
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Submitted");
//                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == previ) {
            setVisible(false);
            new Show();
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main();
        } else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    Record() {
        this("");
    }

    public static void main(String[] args) {
        new Record();
    }
}
