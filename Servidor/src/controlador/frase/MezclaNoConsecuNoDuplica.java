package controlador.frase;


public class MezclaNoConsecuNoDuplica extends MezclaSimbolosBuilder {


    @Override
    public void construirSimbolos() {

        String miMezcla = "";
        String simboloTemp = "";
        String alfabetoOriginal = mezclaSimbolos.getAlfabeto();
        int min = 0;
        int max = mezclaSimbolos.getAlfabeto().length() - 1;
        boolean puedo = false;

        for (int x = 0; x < mezclaSimbolos.getLongitud(); x++)
        {
            while (!puedo)
            {
                simboloTemp = String.valueOf(mezclaSimbolos.getAlfabeto().charAt((int) (Math.random()*((max-min)+1))+min));
                puedo = PuedoAgregar(simboloTemp, alfabetoOriginal);

                if ((mezclaSimbolos.getAlfabeto().length() == 1)) { break; } // se quedó sin opciones en el alfabeto, termine
            }

            miMezcla += simboloTemp;
            mezclaSimbolos.setAlfabeto(mezclaSimbolos.getAlfabeto().replaceAll(simboloTemp, "")); // elimine el simbolo registrado
            mezclaSimbolos.setSimbolos(miMezcla); // necesario para ir obteniendo el ultimo simbolo e ir comparando
            puedo = false;
            max--;

            if(max <= -1) { break; } // se acabaron los simbolos del alfabeto, termine
            // entra al if cuando la longitud ingresada es mayor que la cantidad de simbolos del alfabeto
        }
    }

    private boolean PuedoAgregar(String pSimboloParaAgregar, String pAlfabetoOriginal)
    {
        String simbolos = mezclaSimbolos.getSimbolos();

        int indiceSimboloAnterior = pAlfabetoOriginal.indexOf(pSimboloParaAgregar) - 1;

        if (simbolos != null)
        {
            if (indiceSimboloAnterior > -1)
            {
                if (simbolos.charAt(simbolos.length() - 1) == pAlfabetoOriginal.charAt(indiceSimboloAnterior))
                {
                    return false;
                }
                else { return true; } // el simbolo anterior no coincide con el actual
            }
            else { return true; } // en caso de escoger el primer simbolo del alfabeto
        }
        else { return true; } // el primer simbolo no requiere comprobación
    }
}
