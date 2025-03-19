import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carta {

    private int indice;

    // metodo constructor
    public Carta(Random r) {

        indice = r.nextInt(52) + 1;
    }

    public void mostrar(JPanel pn1, int x, int y) {
        String nombreArchivo = "/imagenes/CARTA" + indice + ".jpg";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreArchivo));

        JLabel lbl = new JLabel();
        lbl.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        lbl.setIcon(imgCarta);

        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, getNombre() + " DE " + getPinta());
            }
        });

        pn1.add(lbl);
    }

    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];
    }
    

}