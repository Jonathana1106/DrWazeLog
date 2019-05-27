package Prolog;


import org.jpl7.Query;
import org.jpl7.Term;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
public class Conexion {
    /**
     *
     * @param result1 es el resultado de la consulta de prolog con espacios
     * @return retorna un ArrayList con los lugares
     */
    private ArrayList<String>  parse(String result1){

        int x=result1.length();
        int i=0;
        String data="";
        ArrayList<String> result=new ArrayList<>();
        while(x!=i){
            String c= Character.toString(result1.charAt(i));{
                if (c.equals(" ")){
                    /*
                    caso especial de san jose
                     */
                    if (data.equals("san")){
                        data+=" ";
                    }
                    else{
                        result.add(data);
                        data="";
                    }

                }
                else{
                    data+=c;
                }
            }
            i++;
        }
        return result;

    }

    /**
     *
     * @param data el resultado de la consulta con los caracteres innecesarios
     * @return string con el resultado que se necesita
     */
    private String getResult(String data){
        int x=data.length();
        int i=0;
        String result="";
        while(x!=i){
            //elimina todos los carcteres innecesarios
            String c= Character.toString(data.charAt(i));{
                if (c.equals("(") || c.equals(")")|| c.equals("|")|| c.equals("'")|| c.equals(",")|| c.equals("[") || c.equals("]")){
                    result+="";
                }
                else{
                    result+=c;
                }
            }
            i++;
        }
        return result;

    }

    /**
     *
     * @return un ArrayList con todos los luagres definidos en el archivo de prolog
     */
    public ArrayList<String> getLugares(){
        String prog="consult('Lugares.pl')";//consulta prolog
        Query q1=new Query(prog);
        System.out.println(prog +(q1.hasSolution()? "Coneccion completada" :"Conec+cion Fallida"));
        ArrayList<String> result= new ArrayList<>();
        String lugar="lugar(X)";
        Query q2 = new Query(lugar);
        Map<String,Term>[]data= q2.allSolutions();//obtiene todas las soluciones
        for (int i=0;i<data.length;i++){
            result.add(getResult(data[i].get("X").toString()));
        }
        System.out.println(result);
        return result;
    }

    /**
     *
     * @return una Matriz de Arrays con todos los arcos disponibles entre los lugares
     */
    public ArrayList<ArrayList<String>> getArcos(){
        String prog="consult('Arcos.pl')";//consulta prolog
        Query q1=new Query(prog);
        System.out.println(prog +(q1.hasSolution()? "Coneccion completada" :"Conec+cion Fallida"));
        ArrayList<ArrayList<String>> result= new ArrayList<>();
        String arco="edge(X,Y,I)";
        Query q2 = new Query(arco);
        Map<String,Term>[]data= q2.allSolutions();
        for (int i=0;i<data.length;i++){
            ArrayList<String>temp=new ArrayList<>();//crea un array con los datos de cada arco
            temp.add(getResult(data[i].get("X").toString()));
            temp.add(getResult(data[i].get("Y").toString()));
            temp.add(getResult(data[i].get("I").toString()));
            result.add(temp);
        }
        System.out.println(result);
        return result;

    }

    /**
     *
     * @param lugar nombre del lugar que se va a agregar
     * @throws IOException en caso que haya un problema al abrir el documento
     */
    public void addLugar(String lugar) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lugares.pl",true));
        String data="\n"+"lugar"+"(["+"'"+lugar+"'"+"]).";
        writer.append(data);
        writer.close();
    }

    /**
     *
     * @param inicio lugar de inicio del camino
     * @param destino nodo final del camino
     * @param distancia valor que refleja lo largo del camino
     * @throws IOException en caso que ocurra un problema al abrir el file
     */
    public void addArco(String inicio,String destino,int distancia) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Arcos.pl",true));
        String data="\n"+"edge("+"'"+inicio+"'"+","+"'"+destino+"'"+","+distancia+").";
        writer.append(data);
        writer.close();
    }

    /**
     *
     * @param destinos un string del array de los destinos disponibles
     * @param origen el lugar origen del viaje
     * @return retorna Un Array del camino mas corto
     */
    public ArrayList<String> getCamino(String destinos, String origen){
        try {

            String prog = "consult('GLC.pl')";//consulta prolog
            Query q1 = new Query(prog);
            System.out.println(prog + (q1.hasSolution() ? "Coneccion completada" : "Conec+cion Fallida"));
            ArrayList<String> result = new ArrayList<>();
            String path = "get_whole_path(" + destinos + "," + "'" + origen + "'" + ",X,Y)";
            Query q2 = new Query(path);
            Map<String, Term> data = q2.oneSolution();//recibe un solucion
            result = parse(getResult(data.get("X").toString()));
            System.out.println(result);
            return result;
        }
        catch (Exception e){
            return null;
        }
    }
}
