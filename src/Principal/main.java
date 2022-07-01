package Principal;

import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.Vector3D;
import Motomaticas.configuracionProyecto;
import Motomaticas.motomaticas;

public class main {

	public static void main(String[] args) {
		configuracionProyecto.activarPanelVisualizar(true);
		configuracionProyecto.activarPanelNotas(true);
		configuracionProyecto.activarPanelOperaciones(true);
		configuracionProyecto.activarPanelProcesoExplicadoMatematico(true);
		configuracionProyecto.activarPanelPizzarra(true);

		motomaticas nuevoProyecto  = new motomaticas();
		nuevoProyecto.importarUnidadesMatematicas(new Vector3D(""), new UnidadNumerosRacionales());
	}

}
