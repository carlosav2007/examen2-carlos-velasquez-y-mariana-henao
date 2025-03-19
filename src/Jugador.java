import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pn1) {
        pn1.removeAll();
        int posicionHorizontal = MARGEN + cartas.length * DISTANCIA;
        // ciclo objetual
        for (Carta c : cartas) {
            c.mostrar(pn1, posicionHorizontal, MARGEN);
            posicionHorizontal -= DISTANCIA;
        }
        pn1.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }
        boolean hayGrupos = false;
        for (int c : contadores) {
            if (c >= 2) {
                hayGrupos = true;
                break;
            }
        }
        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes grupos: \n";
            int p = 0;
            for (int c : contadores) {
                if (c >= 2) {
                    mensaje += Grupo.values()[c] + " DE " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }
        return mensaje;
    }

    public String getEscaleras() {
        String mensajeEscalera = "No se detectaron escaleras";

        Carta[] trebol = new Carta[TOTAL_CARTAS];
        Carta[] pica = new Carta[TOTAL_CARTAS];
        Carta[] corazon = new Carta[TOTAL_CARTAS];
        Carta[] diamante = new Carta[TOTAL_CARTAS];

        int contTrebol = 0, contPica = 0, contCorazon = 0, contDiamante = 0;

        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].getPinta() == Pinta.TREBOL) {
                trebol[contTrebol] = cartas[i];
                contTrebol++;
            }
            if (cartas[i].getPinta() == Pinta.PICA) {
                pica[contPica] = cartas[i];
                contPica++;
            }
            if (cartas[i].getPinta() == Pinta.CORAZON) {
                corazon[contCorazon] = cartas[i];
                contCorazon++;
            }
            if (cartas[i].getPinta() == Pinta.DIAMANTE) {
                diamante[contDiamante] = cartas[i];
                contDiamante++;
            }
        }

        ordenarCartas(trebol, contTrebol);
        ordenarCartas(pica, contPica);
        ordenarCartas(corazon, contCorazon);
        ordenarCartas(diamante, contDiamante);

        String resultado = "";

        resultado = detectarEscalerasEnPinta(trebol, contTrebol, "Trébol");
        if (resultado.length() > 0) {
            mensajeEscalera = resultado;
        }

        resultado = detectarEscalerasEnPinta(pica, contPica, "Pica");
        if (resultado.length() > 0) {
            if (mensajeEscalera == "No se detectaron escaleras") {
                mensajeEscalera = resultado;
            } else {
                mensajeEscalera = mensajeEscalera + "\n" + resultado;
            }
        }

        resultado = detectarEscalerasEnPinta(corazon, contCorazon, "Corazón");
        if (resultado.length() > 0) {
            if (mensajeEscalera == "No se detectaron escaleras") {
                mensajeEscalera = resultado;
            } else {
                mensajeEscalera = mensajeEscalera + "\n" + resultado;
            }
        }

        resultado = detectarEscalerasEnPinta(diamante, contDiamante, "Diamante");
        if (resultado.length() > 0) {
            if (mensajeEscalera == "No se detectaron escaleras") {
                mensajeEscalera = resultado;
            } else {
                mensajeEscalera = mensajeEscalera + "\n" + resultado;
            }
        }

        return mensajeEscalera;
    }

    private String detectarEscalerasEnPinta(Carta[] cartas, int n, String pinta) {
        if (n < 3) {
            return "";
        }

        String mensajeResultado = "";
        int consecutivas = 1;

        for (int i = 1; i < n; i++) {
            if (cartas[i - 1].getNombre().ordinal() + 1 == cartas[i].getNombre().ordinal()) {
                consecutivas++;
            } else {
                if (consecutivas >= 3) {
                    mensajeResultado = mensajeResultado + "Escalera en " + pinta + ": " + mostrarCartas(cartas, i - consecutivas, i) + "\n";
                }
                consecutivas = 1;
            }
        }

        if (consecutivas >= 3) {
            mensajeResultado = mensajeResultado + "Escalera en " + pinta + ": " + mostrarCartas(cartas, n - consecutivas, n) + "\n";
        }

        boolean tieneJ = false, tieneQ = false, tieneK = false;
        for (int i = 0; i < n; i++) {
            if (cartas[i].getNombre() == NombreCarta.JACK) {
                tieneJ = true;
            }
            if (cartas[i].getNombre() == NombreCarta.QUEEN) {
                tieneQ = true;
            }
            if (cartas[i].getNombre() == NombreCarta.KING) {
                tieneK = true;
            }
        }
        if (tieneJ) {
            if (tieneQ) {
                if (tieneK) {
                    mensajeResultado = mensajeResultado + "Escalera especial en " + pinta + ": JACK, QUEEN, KING\n";
                }
            }
        }

        return mensajeResultado;
    }

    private String mostrarCartas(Carta[] cartas, int inicio, int fin) {
        String resultado = "";
        for (int i = inicio; i < fin; i++) {
            if (i > inicio) {
                resultado = resultado + ", ";
            }
            resultado = resultado + cartas[i].getNombre();
        }
        return resultado;
    }

    public void ordenarCartas(Carta[] cartas, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cartas[j].getNombre().ordinal() > cartas[j + 1].getNombre().ordinal()) {
                    Carta temp = cartas[j];
                    cartas[j] = cartas[j + 1];
                    cartas[j + 1] = temp;
                }
            }
        }
    }
    public String getPuntaje() {
        int puntaje = 0;

        for (Carta c : cartas) {
            boolean enGrupo = false;
            boolean enEscalera = false;

            for (Carta otraCarta : cartas) {
                if (otraCarta == c) {
                    continue;
                }
                if (otraCarta.getNombre() == c.getNombre()) {
                    enGrupo = true;
                    break;
                }
            }

            for (Carta otraCarta : cartas) {
                if (otraCarta == c) {
                    continue;
                }
                if (otraCarta.getPinta() == c.getPinta()) {
                    int diferencia = otraCarta.getNombre().ordinal() - c.getNombre().ordinal();
                    if (diferencia == 1) {
                        enEscalera = true;
                        break;
                    }
                    if (diferencia == -1) {
                        enEscalera = true;
                        break;
                    }
                }
            }

            if (enGrupo == false) {
                if (enEscalera == false) {
                    switch (c.getNombre()) {
                        case AS:
                        case JACK:
                        case QUEEN:
                        case KING:
                            puntaje += 10;
                            break;
                        default:
                            puntaje += c.getNombre().ordinal() + 1;
                            break;
                    }
                }
            }
        }

        return "Puntaje total: " + puntaje;
    }
}