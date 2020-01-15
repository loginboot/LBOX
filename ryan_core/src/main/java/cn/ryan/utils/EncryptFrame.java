package cn.ryan.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 * 
 * @author xiesw
 * 
 * @description Lyods加密图形化操作界面
 *
 */

public class EncryptFrame extends JFrame {

    /**
     * 默认版本标识
     */
    private static final long serialVersionUID = 1L;
    // 是否支持解密
    private static boolean isDec = false;

    public static void main(String[] args) {
        // 支持解密
        if (args.length > 0) {
            if (args[0].startsWith("-DEC")) {
                isDec = true;
            }
        }
        EncryptFrame ef = new EncryptFrame();
        ef.startup();
    }

    private JLabel jtxt;
    private JComboBox<String> jcbox;

    private JLabel pwdLabel;
    private JLabel eptLabel;

    private JTextField pwdText;
    private JTextField eptText;

    private JPanel content;

    private JButton jbtn;

    public EncryptFrame() {

    }

    public void startup() {
        init();
    }

    @SuppressWarnings("all")
    private void init() {
        this.setSize(500, 200);
        this.setTitle("Ryan Encryption");
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // JFrame打开后居中。
        this.setLocationRelativeTo(getOwner());
        this.getContentPane().add(initDraw());
        this.setVisible(true);

        System.out.println("init lyods encryption tools successful.");
    }

    private JPanel initDraw() {
        content = new JPanel(null);
        jtxt = new JLabel("Encrypt Type:");
        jtxt.setBounds(20, 12, 120, 20);
        content.add(jtxt);
        content.add(getDrawBox());

        pwdLabel = new JLabel("Type your password:");
        pwdLabel.setBounds(20, 40, 220, 20);
        content.add(pwdLabel);

        pwdText = new JPasswordField();
        pwdText.setBounds(20, 60, 460, 25);
        content.add(pwdText);

        eptLabel = new JLabel("Encrypted password(Ctrl-c to copy to clipboard):");
        eptLabel.setBounds(20, 85, 320, 20);
        content.add(eptLabel);

        eptText = new JTextField("");
        eptText.setBorder(BorderFactory.createLineBorder(Color.gray));
        eptText.setBounds(20, 105, 460, 25);
        eptText.setBackground(new Color(238, 238, 238));
        // eptText.setEnabled(false);
        eptText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                eptText.setSelectionStart(0);
                eptText.setSelectionEnd(eptText.getText().getBytes().length);
            }
        });
        eptText.setEditable(false);
        eptText.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.isControlDown() == true) && (e.getKeyCode() == KeyEvent.VK_C)) {
                    JComponent jc = (JComponent) e.getSource();
                    TransferHandler th = jc.getTransferHandler();
                    th.exportAsDrag(jc, e, TransferHandler.COPY);
                } else if ((e.isControlDown() == true) && (e.getKeyCode() == KeyEvent.VK_A)) {
                    eptText.setSelectionStart(0);
                    eptText.setSelectionEnd(eptText.getText().getBytes().length);
                }
            }
        });

        content.add(eptText);

        content.add(getBtn());

        return content;
    }

    private JComboBox<String> getDrawBox() {
        if (jcbox == null) {
            DefaultComboBoxModel<String> fruitsName = new DefaultComboBoxModel<String>();
            fruitsName.addElement("Encrypt");
            if (isDec) {
                fruitsName.addElement("Decrypt");
            }
            fruitsName.addElement("EncryptAES");
            if (isDec) {
                fruitsName.addElement("DecryptAES");
            }

            jcbox = new JComboBox<String>(fruitsName);
            jcbox.setSelectedIndex(0);

            jcbox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ex) {
                    String data = "";
                    if (jcbox.getSelectedIndex() != -1) {
                        data = (String) jcbox.getItemAt(jcbox.getSelectedIndex());
                    }
                    content.remove(pwdText);
                    if ("Encrypt".equals(data) || "EncryptAES".equals(data)) {
                        pwdText = new JPasswordField();
                        pwdLabel.setText("Type your password");
                        eptLabel.setText("Encrypted password(Ctrl-c to copy to clipboard):");
                        eptText.setText("");
                        jbtn.setText(data);
                    } else {
                        pwdText = new JTextField();
                        pwdLabel.setText("Encrypted password");
                        eptLabel.setText("Type your password(Ctrl-c to copy to clipboard):");
                        eptText.setText("");
                        jbtn.setText(data);
                    }
                    pwdText.setBounds(20, 60, 460, 25);
                    content.add(pwdText);
                    content.repaint();
                    System.out.println("JBOX change data:" + data);
                }
            });
            jcbox.setBounds(100, 10, 100, 25);
        }
        return jcbox;
    }

    private JButton getBtn() {
        if (jbtn == null) {
            jbtn = new JButton("Encrypt");

            jbtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent aet) {
                    String txt = jbtn.getText();
                    // 加密
                    if ("Encrypt".equals(txt) || "EncryptAES".equals(txt)) {
                        String kdata = pwdText.getText();
                        if (kdata != null && !kdata.trim().equals("")) {
                            kdata = kdata.trim();
                            try {
                                String pwd = "";
                                if ("EncryptAES".equals(txt)) {
                                    pwd = Encrypt.encryptAES(kdata);
                                } else {
                                    pwd = Encrypt.encodeString(kdata);
                                }
                                eptText.setText(pwd);
                                eptText.setFocusable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                alterMsg("Type your passwod info error,please check it!");
                            }
                        } else {
                            alterMsg("Type your passwod don't empty!");
                        }
                    } else {
                        String kdata = pwdText.getText();
                        if (kdata != null && !kdata.trim().equals("")) {
                            kdata = kdata.trim();
                            try {
                                String pwd = "";
                                if ("DecryptAES".equals(txt)) {
                                    pwd = Encrypt.decryptAES(kdata);
                                } else {
                                    pwd = Encrypt.decodeString(kdata);
                                }
                                eptText.setText(pwd);
                                eptText.setFocusable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                alterMsg("Encrypt passwod info error,please check it!");
                            }
                        } else {
                            alterMsg("Encrypt passwod don't empty!");
                        }
                    }
                }
            });

            jbtn.setBounds(100, 135, 300, 25);
        }

        return jbtn;
    }

    private void alterMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
