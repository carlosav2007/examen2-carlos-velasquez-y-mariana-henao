import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlJugador1.setBackground(new Color(173, 216, 230));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(25, 25, 112));
        pnlJugador2.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);

        jugador1 = new Jugador();
        jugador2 = new Jugador();

    }

    Jugador jugador1, jugador2;

    private void btnRepartirClick(ActionEvent evt) {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
    }

    private void btnVerificarClick(ActionEvent evt) {
        int pestaña = tpJugadores.getSelectedIndex();
        switch (pestaña) {
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                JOptionPane.showMessageDialog(null, jugador1.getEscaleras());
                JOptionPane.showMessageDialog(null, jugador1.getPuntaje());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                JOptionPane.showMessageDialog(null, jugador2.getEscaleras());
                JOptionPane.showMessageDialog(null, jugador2.getPuntaje());
                break;
        }
    }
}