import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Main extends JFrame implements ActionListener {
    JTextField tfname, tfemail, tpass,tfroll;
    JButton cancel, login,sign;
    Main() {
        super("Student Login Portal");
        setSize(600, 400);
        setLocation(400, 200);

        JLabel heading = new JLabel("Student Login");
        heading.setBounds(250, 15, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);


        JLabel roll = new JLabel("Student's Roll");
        roll.setBounds(100, 80, 150, 20);
        add(roll);

        tfroll = new JTextField();
        tfroll.setBounds(240, 80, 200, 20);
        add(tfroll);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 120, 150, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(240, 120, 200, 20);
        add(tfname);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 160, 100, 20);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(240, 160, 200, 20);
        add(tfemail);

        JLabel lblpass = new JLabel("Password");
        lblpass.setBounds(100, 200, 100, 20);
        add(lblpass);

        tpass = new JTextField();
        tpass.setBounds(240, 200, 200, 20);
        add(tpass);

        login = new JButton("Login");
        login.setBounds(180, 300, 100,25);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(380, 300, 100,25);
        cancel.addActionListener(this);
        add(cancel);

        sign = new JButton("Signup");
        sign.setBounds(280, 300, 100,25);
        sign.addActionListener(this);
        add(sign);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.GRAY);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            String sroll=tfroll.getText();
            String sname=tfname.getText();
            String semail=tfemail.getText();
            String spass=tpass.getText();

            try {
                Conn c=new Conn();
                String query="select * from signup where roll='"+sroll+"'and name='"+sname+"' and email='"+semail+"'and pass='"+spass+"'";
                ResultSet rs= c.s.executeQuery(query);

                if(rs.next()){
                    setVisible(false);
                    String roll = rs.getString("roll");
                    new Record(roll);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                    tfname.setText("");
                    tpass.setText("");
                    tfemail.setText("");
                    tfroll.setText("");


                }
            }catch (Exception es){
                es.printStackTrace();
            }
        }
        else if(e.getSource()==sign){
            setVisible(false);
            new Signup();
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}