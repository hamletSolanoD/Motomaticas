package Motomaticas;

public class configuracionProyecto {

    public static boolean panelGrafico;
    public static boolean panelPizzarra;
    public static boolean panelNotas;
    public static boolean panelOperaciones;
    public static boolean panelProcesoExplicadoMatematico;
    public static boolean panelVisualizar;


    public static boolean isPanelGraficoActivado(){
        return panelGrafico;
    }
    public static boolean isPanelPizzarraoActivado(){
        return panelPizzarra;
    }
    public static boolean isPanelNotasActivado(){
        return panelNotas;
    }
    public static boolean isPanelOperacionesActivado(){
        return panelOperaciones;
    }
    public static boolean isPanelVisualizarActivado(){
        return panelVisualizar;
    }
    public static boolean isPanelProcesoExplicadoMatematicoActivado(){
        return panelProcesoExplicadoMatematico;
    }



   
    public static void activarPanelProcesoExplicadoMatematico(boolean panelProcesoExplicadoMatematico) {
        configuracionProyecto.panelProcesoExplicadoMatematico = panelProcesoExplicadoMatematico;
    }

    public static void activarPanelPizzarra(boolean panelPizzarra) {
        configuracionProyecto.panelPizzarra = panelPizzarra;

    }

    public static void activarPanelNotas(boolean panelNotas) {
        configuracionProyecto.panelNotas = panelNotas;

    }

    public static void activarPanelOperaciones(boolean panelOperaciones) {
        configuracionProyecto.panelOperaciones = panelOperaciones;

    }

    public static void activarPanelVisualizar(boolean panelVisualizar) {
        configuracionProyecto.panelVisualizar = panelVisualizar;

    }

}
