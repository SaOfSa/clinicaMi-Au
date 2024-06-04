package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class ProntuarioAnimal extends JFrame {

    // Campos de texto e outros componentes
    private JTextField campoIdAnimal, campoNome, campoEspecie, campoRaca, campoCor, campoPeso, campoCpfTutor, campoIdade;

    // Configurações do banco de dados
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_veterinaria";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "usjt";

    public ProntuarioAnimal() {
        // Configurações da janela
        setTitle("Ficha");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia a janela em tela cheia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Criação dos componentes
        initComponents();

        // Torna a janela visível
        setVisible(true);
    }

    private void initComponents() {
        // Inicializa os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton btnVoltar = new JButton("Voltar à Tela Principal");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaPrincipal(); // Abre a tela principal
                dispose(); // Fecha a tela
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);

        gbc.gridwidth = 1; // Reseta o gridwidth

        // Informações Básicas do Animal
        JLabel labelIdAnimal = new JLabel("ID do Animal:");
        labelIdAnimal.setForeground(Color.BLACK);
        campoIdAnimal = new JTextField(20);
        campoIdAnimal.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                preencherInformacoesAnimal();
            }
        });

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setForeground(Color.BLACK);
        campoNome = new JTextField(20);
        campoNome.setEditable(false);

        JLabel labelEspecie = new JLabel("Espécie:");
        labelEspecie.setForeground(Color.BLACK);
        campoEspecie = new JTextField(20);
        campoEspecie.setEditable(false);

        JLabel labelRaca = new JLabel("Raça:");
        labelRaca.setForeground(Color.BLACK);
        campoRaca = new JTextField(20);
        campoRaca.setEditable(false);

        JLabel labelCor = new JLabel("Cor/Pelagem:");
        labelCor.setForeground(Color.BLACK);
        campoCor = new JTextField(20);
        campoCor.setEditable(false);

        JLabel labelPeso = new JLabel("Peso:");
        labelPeso.setForeground(Color.BLACK);
        campoPeso = new JTextField(20);
        campoPeso.setEditable(false);

        JLabel labelCpfTutor = new JLabel("CPF do Tutor:");
        labelCpfTutor.setForeground(Color.BLACK);
        campoCpfTutor = new JTextField(20);
        campoCpfTutor.setEditable(false);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setForeground(Color.BLACK);
        campoIdade = new JTextField(20);
        campoIdade.setEditable(false);

        // Adiciona os componentes ao layout
        addComponent(gbc, labelIdAnimal, 0, 1);
        addComponent(gbc, campoIdAnimal, 1, 1);
        addComponent(gbc, labelNome, 0, 2);
        addComponent(gbc, campoNome, 1, 2);
        addComponent(gbc, labelEspecie, 0, 3);
        addComponent(gbc, campoEspecie, 1, 3);
        addComponent(gbc, labelRaca, 0, 4);
        addComponent(gbc, campoRaca, 1, 4);
        addComponent(gbc, labelCor, 0, 5);
        addComponent(gbc, campoCor, 1, 5);
        addComponent(gbc, labelPeso, 0, 6);
        addComponent(gbc, campoPeso, 1, 6);
        addComponent(gbc, labelCpfTutor, 0, 7);
        addComponent(gbc, campoCpfTutor, 1, 7);
        addComponent(gbc, labelIdade, 0, 8);
        addComponent(gbc, campoIdade, 1, 8);
        addComponent(gbc, btnVoltar, 0, 9);


        pack();
    }

    private void addComponent(GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    private void preencherInformacoesAnimal() {
        String id = campoIdAnimal.getText().trim();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM TBL_FICHA WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        campoNome.setText(rs.getString("NOME"));
                        campoEspecie.setText(rs.getString("ESPECIE"));
                        campoRaca.setText(rs.getString("RACA"));
                        campoCor.setText(rs.getString("COR_PELAGEM"));
                        campoPeso.setText(rs.getString("PESO"));
                        campoCpfTutor.setText(rs.getString("CPF_DONO"));
                        campoIdade.setText(calculateAge(rs.getDate("DATA_NASCIMENTO")));
                    } else {
                        JOptionPane.showMessageDialog(this, "Animal não encontrado.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados do animal.");
        }
    }

    private String calculateAge(java.sql.Date birthDate) {
        // Calcular a idade do animal a partir da data de nascimento
        java.util.Date currentDate = new java.util.Date();
        long ageInMillis = currentDate.getTime() - birthDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
        return String.valueOf(ageInYears);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProntuarioAnimal();
            }
        });
    }
}
