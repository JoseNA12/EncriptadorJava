package controlador.frase;

public class MezclaConsecuDuplica extends MezclaSimbolosBuilder {


    @Override
    public void construirSimbolos() {

        String miMezcla = "";
        int min = 0;
        int max = mezclaSimbolos.getAlfabeto().length() - 1;

        for (int x = 0; x < mezclaSimbolos.getLongitud(); x++)
        {
            miMezcla += mezclaSimbolos.getAlfabeto().charAt((int) (Math.random()*((max-min)+1))+min);
        }

        mezclaSimbolos.setSimbolos(miMezcla);
    }
}