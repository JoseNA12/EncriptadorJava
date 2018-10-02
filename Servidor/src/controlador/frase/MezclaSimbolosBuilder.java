package controlador.frase;

public abstract class MezclaSimbolosBuilder {

    protected MezclaSimbolos mezclaSimbolos;

    public MezclaSimbolos getMezclaSimbolos(){
        return mezclaSimbolos;
    }

    public void crearNuevaMezclaSimbolos(String pAlfabeto, int pLongitud){
        mezclaSimbolos = new MezclaSimbolos(pAlfabeto, pLongitud);
    }

    public abstract void construirSimbolos();
}
