package lesson3.finmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Сергей on 17.04.2016.
 */
public class LoginForm extends JFrame {

    JLabel label1 = new JLabel("Имя пользователя:");
    JLabel label2 = new JLabel("Пароль:");
    JTextField user= new JTextField();
    JPasswordField passw = new JPasswordField();
    JButton login = new JButton("Логин");
    JButton registration = new JButton("Регистрация");


    public LoginForm(String title) throws HeadlessException {
        super(title);
        Container pane = this.getContentPane();
        GridLayout layout = new GridLayout(7,1,5,5);
        pane.setLayout(layout);

        Font font = new Font("Arial", Font.BOLD, 12);
        pane.setFont(font);

        label1.setFont(font);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(label1);
        user.setSize(70, 20);
        pane.add(user);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(font);
        pane.add(label2);
        passw.setSize(70, 20);
        pane.add(passw);
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setSize(20,0);
        pane.add(separator);
        login.setSize(70, 20);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);

            }
        });
        pane.add(login);
        registration.setSize(70,20);
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegistrationForm registration = new RegistrationForm();
                registration.setSize(400,300);
                registration.setVisible(true);
            }
        });
        pane.add(registration);

    }

    public static void main(String[] args) {
        LoginForm form = new LoginForm("Финансовый менеджер");
        try {
            //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
            //javax.swing.plaf.metal.MetalLookAndFeel
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        form.pack();
        form.setLocation(800, 300);
        form.setSize(200,330);
        form.setVisible(true);
    }
}
