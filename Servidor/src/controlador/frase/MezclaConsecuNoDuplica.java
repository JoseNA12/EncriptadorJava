package controlador.frase;

public class MezclaConsecuNoDuplica extends MezclaSimbolosBuilder {


    @Override
    public void construirSimbolos() {

        String miMezcla = "";
        String simboloTemp = "";
        int min = 0;
        int max = mezclaSimbolos.getAlfabeto().length() - 1;

        for (int x = 0; x < mezclaSimbolos.getLongitud(); x++)
        {
            simboloTemp = String.valueOf(mezclaSimbolos.getAlfabeto().charAt((int) (Math.random()*((max-min)+1))+min));
            miMezcla += simboloTemp;
            mezclaSimbolos.setAlfabeto(mezclaSimbolos.getAlfabeto().replaceAll(simboloTemp, ""));
            max--;

            if(max <= -1) { break; }
        }

        mezclaSimbolos.setSimbolos(miMezcla);
    }
}