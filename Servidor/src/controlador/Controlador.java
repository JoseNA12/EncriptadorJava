package controlador;

public class Controlador implements IValidable {

    //public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();
    private IEscritura miEscritor;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    public Boolean AgregarAlfabeto(IOServidorDTO miDTO)
    {
        return alfabetosDAO.crearAlfabeto(miDTO);
    }

    public IOServidorDTO CargarAlfabetos()
    {
        System.out.println("Controlador.CargarAlfabetos()");
        return new IOServidorDTO(alfabetosDAO.recuperarAlfabetos());
    }

    public Boolean ModificarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.updateAlfabeto(miDTO);
    }

    public Boolean EliminarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.removerAlfabeto(miDTO);
    }


}
