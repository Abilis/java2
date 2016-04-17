package lesson3.finmanager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Сергей on 17.04.2016.
 */
public class RegistrationForm extends JFrame{

    public RegistrationForm() throws HeadlessException {
        GridLayout box = new GridLayout(2,1,5,5);
        this.setLayout(box);
        JTextField text = new JTextField();
        JLabel label = new JLabel("Регистрация нового пользователя");
        this.add(label);
        this.setLocation(700,250);
        this.add(text);


    }
}
