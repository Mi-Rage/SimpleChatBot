import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String CHB_AI = "AI";
    final String BTN_ENTER = "Enter";

    JTextArea dialogue; // area for dialog
    JCheckBox ai;       // enable/disable AI
    JTextField message; // field for entering messages
    SimpleBot sbot;     // chat-bot class (in bot package)
//    SimpleAttributeSet botStyle; // style bot text

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    SimpleChatBot(){
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Создаем область с общим текстом
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);  // Перенос слов при достижении конца окна
        JScrollPane scrollBar = new JScrollPane(dialogue);

        // Нижняя понель с чекбоксом, вводом текста и кнопкой Enter
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox(CHB_AI);
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton(BTN_ENTER);
        enter.addActionListener(this);

        // Добавляем всё во всё
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        sbot = new SimpleBot();   //Подключаем бота
    }


    /**
     * Слушатель для кнопки в панели ввода
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            dialogue.append(TITLE_OF_PROGRAM.substring(0, 9) +
                            sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
