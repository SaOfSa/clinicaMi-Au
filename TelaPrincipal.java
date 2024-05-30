import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal {

    public static void main(String[] args) {
        

        JFrame frame = new JFrame("Clínica Veterinária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Tela Principal");
        titleLabel.setForeground(Color.BLACK); 
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 15));
        topPanel.add(titleLabel);

        
        JButton fichaButton = new JButton("Consultar Ficha");
        JButton examesButton = new JButton("Consultar Exames");

        
        fichaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        examesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
        mainPanel.add(fichaButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        mainPanel.add(examesButton);

        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);


        fichaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(frame, "Consultar Ficha não implementado.");
            }
        });

        examesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                JOptionPane.showMessageDialog(frame, "Consultar Exames não implementado.");
            }
        });

        
        frame.setVisible(true);
    }
}
