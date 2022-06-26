package Principal;

import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.Vector3D;
import Motomaticas.configuracionProyecto;
import Motomaticas.motomaticas;

public class main {

	public static void main(String[] args) {
		configuracionProyecto.activarPanelVisualizar(false);
		configuracionProyecto.activarPanelNotas(false);
		configuracionProyecto.activarPanelOperaciones(true);
		configuracionProyecto.activarPanelProcesoExplicadoMatematico(true);
		configuracionProyecto.activarPanelPizzarra(false);

		motomaticas nuevoProyecto  = new motomaticas();
		nuevoProyecto.importarUnidadesMatematicas(new Vector3D(""), new UnidadNumerosRacionales());
	}

}
