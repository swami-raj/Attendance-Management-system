import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class Show extends JFrame implements ActionListener {
    Choice  crollno;
    JButton search,print;
    JTable table;

    Show(){
        super("Record Detail Show");
        setSize(600, 400);
        setLocation(400, 200);

        JLabel heading = new JLabel("Details");
        heading.setBounds(250, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel headin=new JLabel ("Search by Roll Number");
        headin.setBounds(50,50,150,20);
        add(headin);

        crollno=new Choice();
        crollno.setBounds(200,50,150,20);
        add(crollno);

        search=new JButton("Search");
        search.setBounds(400,50,80,20);
        search.addActionListener(this);
        add(search);

        print=new JButton("Print");
        print.setBounds(500,50,80,20);
        print.addActionListener(this);
        add(print);

        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select *from record");
            while(rs.next()){
                crollno.add(rs.getString("roll"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        table=new JTable();

        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select *from record");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }


        JScrollPane jsp=new JScrollPane(table);
        jsp.setBounds(0,100,600,400);
        add(jsp);

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String query="select * from record where roll='"+crollno.getSelectedItem()+"'";
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        } else if (e.getSource()==print) {
            try{
                table.print();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Show();
    }


}
