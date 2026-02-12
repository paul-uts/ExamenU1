package Reloj;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

public class RelojLabel implements Runnable {
    private JLabel etiqueta;
    private Thread hilo;

    public RelojLabel(JLabel etiqueta) {
        this.etiqueta = etiqueta;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (true) {
            Calendar calendario = new GregorianCalendar();
            Date fechaHoraActual = new Date();
            calendario.setTime(fechaHoraActual);

            String aMpM = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
            int horaInt = calendario.get(Calendar.HOUR);
            if (horaInt == 0) horaInt = 12;
            String hora = horaInt < 10 ? "0" + horaInt : "" + horaInt;
            String minutos = calendario.get(Calendar.MINUTE) < 10 ? "0" + calendario.get(Calendar.MINUTE) : "" + calendario.get(Calendar.MINUTE);
            String segundos = calendario.get(Calendar.SECOND) < 10 ? "0" + calendario.get(Calendar.SECOND) : "" + calendario.get(Calendar.SECOND);

            String horaCompleta = hora + ":" + minutos + ":" + segundos + " " + aMpM;

            etiqueta.setText(horaCompleta);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}