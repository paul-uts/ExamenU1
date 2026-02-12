package BarraCarga;

import Menu.Principal;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author manue
 */
public class BarraCarga extends JFrame{
    

 private JProgressBar barra;

    public BarraCarga() {
        setTitle("Cargando...");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        barra = new JProgressBar(0, 100);
        barra.setBounds(30, 30, 230, 20);
        barra.setStringPainted(true);
        add(barra);

        setVisible(true);
        cargar();
    }

    private void cargar() {
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(7);
                    barra.setValue(i);
                }
                dispose();
               
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

   
}