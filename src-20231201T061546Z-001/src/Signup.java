import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    JTextField tfname, tfemail, tpass, tffname, tfroll;
    JButton cancel, login, register;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    Signup() {
        super("Student Register Portal");
        setSize(600, 400);
        setLocation(400, 200);

        JLabel heading = new JLabel("Student Register");
        heading.setBounds(250, 15, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel roll = new JLabel("Student's Roll");
        roll.setBounds(100, 80, 150, 20);
        add(roll);

        tfroll = new JTextField("123" + first4);
        tfroll.setBounds(240, 80, 200, 20);
        add(tfroll);

        JLabel lblname = new JLabel("Student's Name");
        lblname.setBounds(100, 120, 150, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(240, 120, 200, 20);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(100, 160, 150, 20);
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(240, 160, 200, 20);
        add(tffname);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 200, 100, 20);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(240, 200, 200, 20);
        add(tfemail);

        JLabel lblpass = new JLabel(" Create Password");
        lblpass.setBounds(100, 240, 120, 20);
        add(lblpass);

        tpass = new JTextField();
        tpass.setBounds(240, 240, 200, 20);
        add(tpass);

        login = new JButton("Register");
        login.setBounds(180, 300, 100, 25);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(380, 300, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String roll = tfroll.getText();
            String name = tfname.getText();
            String fname = tffname.getText();
            String email = tfemail.getText();
            String pass = tpass.getText();

            try {
                String query = "insert into signup values('" + roll + "','" + name + "','" + fname + "','" + email + "','" + pass + "')";
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registered successfully");
                setVisible(false);
                new Main();


                sendRegistrationEmail(email, pass,roll);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    private void sendRegistrationEmail(String userEmail, String userPassword, String userRoll) {
        String senderEmail = "swamiraj0303@gmail.com";
        String senderPassword = "vqqinnfbcjmkicvb";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Registration Confirmation");

            // Include email, password, and roll number in the email content
            String emailContent = "Thank you for registering!\n\n"
                    + "Your email: " + userEmail + "\n"
                    + "Your password: " + userPassword + "\n"
                    + "Your roll number: " + userRoll;
            message.setText(emailContent);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Registration email sent to " + userEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
