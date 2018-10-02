package controlador.frase;


public class Mezclar {

    private MezclaSimbolosBuilder mezclaSimbolosBuilder;

    public void setMezclaSimbolosBuilder(MezclaSimbolosBuilder miMezcla){
        mezclaSimbolosBuilder = miMezcla;
    }

    public MezclaSimbolos getMezclaSimbolos(){
        return mezclaSimbolosBuilder.getMezclaSimbolos();
    }

    public void construirMezcla(String pAlfabeto, int pLongitud){
        mezclaSimbolosBuilder.crearNuevaMezclaSimbolos(pAlfabeto, pLongitud);
        mezclaSimbolosBuilder.construirSimbolos();
    }
}
