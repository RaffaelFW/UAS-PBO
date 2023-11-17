package view;

import controller.Controller;
import model.Games;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameList {
    public GameList(String email){

        Controller controller = new Controller();
        JFrame frame = new JFrame();
        frame.setTitle("Game List");
        frame.setSize(600,400);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,2));
        JLabel gameLabels = new JLabel("Games :");
        String [] gameNames = controller.listGame();
        JComboBox games = new JComboBox<>(gameNames);
        JButton transactionButton = new JButton("Transactions");
        JLabel namaGame= new JLabel("Nama Game:");
        JLabel hargaGame = new JLabel("Harga Game:");
        JLabel genreGame= new JLabel("Genre Game:");
        JLabel nama = new JLabel();
        JLabel harga = new JLabel();
        JLabel genre= new JLabel();
        JButton button = new JButton("Buy Game");

        games.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                nama.removeAll();
                harga.removeAll();
                genre.removeAll();
                nama.setText(controller.getGamesbyName(games.getSelectedItem().toString()).getName());
                harga.setText(String.valueOf(controller.getGamesbyName(games.getSelectedItem().toString()).getPrice()));
                genre.setText(controller.getGamesbyName(games.getSelectedItem().toString()).getGenre());
            }

        });
        panel.add(transactionButton);
        panel.add(gameLabels);
        panel.add(games);
        panel.add(namaGame);
        panel.add(nama);
        panel.add(hargaGame);
        panel.add(harga);
        panel.add(genreGame);
        panel.add(genre);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);

       button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailuser = email;
                String gameName = games.getSelectedItem().toString();
                if (controller.buyGames(emailuser,gameName)){
                    JOptionPane.showMessageDialog(null, "Pembelian Berhasil!");
                    frame.dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        new GameList("john.doe@example.com");

    }
}
