import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph {

    int size;
    private  int radius, durchmesser =0;
    private Integer AdjacencyMatrix2[][];
    private Integer distanceMatix[][];
    private Integer wegmatrix[][];
    private Integer matrixA[][];
    private int exzentrizitaet[];
    private int posUnique[];



    public int getRadius() {
        return radius;
    }
    public int getDurchmesser() {
        return durchmesser;
    }
    public Integer[][] getAdjacencyMatrix2() {
        return AdjacencyMatrix2;
    }
    public Integer[][] getDistanceMatix() {
        return distanceMatix;
    }
    public Integer[][] getWegmatrix() {
        return wegmatrix;
    }
    public int[] getExzentrizitaet() {
        return exzentrizitaet;
    }

    public Integer[][] readCSVFile()
    {
        Integer[][] myArray = null;
        String inputLine = "";
        String filelocation ="C:\\Users\\rezah\\OneDrive\\Desktop\\input_graph.csv";
        System.out.println("....");
        try{
            //setup a scanner
            Scanner scannerIn = new Scanner(new BufferedReader(new FileReader(filelocation)));
            int j=0;
            while (scannerIn.hasNextLine())
            {
                // read line in from file
                inputLine = scannerIn.nextLine().trim();
                if(inputLine.endsWith(",")) {
                    inputLine = inputLine.substring(0, inputLine.length()-1);
                }
                // split the Inputline into an array at the commas
                String[] inArray = inputLine.split(",");
                if(myArray == null) {
                    myArray = new Integer[inArray.length][inArray.length];
                }

                //copy the content of the inArray to the myArray
                for (int i =0; i < inArray.length; i++)
                {
                    if(!inArray[i].trim().isEmpty()) {
                        myArray[j][i] = Integer.parseInt(inArray[i].trim());
                    }
                }
                // Increment the row in the array
                j++;
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return myArray;
    }
    public void printGraph() {
        System.out.println("\n------------ Print AdjacencyMatrix ---------------------");
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                System.out.print(AdjacencyMatrix2[i][j]+ " ");
            }
        }
    }
    public void allesNeuSetzen() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                AdjacencyMatrix2[i][j] = 0;
                wegmatrix[i][j] = 0;
                distanceMatix[i][j] = -1;
                if (i == j) {
                    wegmatrix[i][j] = 1;
                    distanceMatix[i][j] = 0;
                }
            }
        }
    }
    public void initialize() {
        Integer[][] loadedMtrix = readCSVFile();
        size = loadedMtrix.length;
        AdjacencyMatrix2 = new Integer[size][size];
        distanceMatix = new Integer[size][size];
        wegmatrix = new Integer[size][size];
        matrixA = new Integer[size][size];
        exzentrizitaet = new int[size];
        posUnique = new int [size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                AdjacencyMatrix2[i][j] =loadedMtrix[i][j];
                distanceMatix[i][j] =loadedMtrix[i][j];
                matrixA[i][j] =loadedMtrix[i][j];
                if (i != j){
                    if (distanceMatix[i][j]==0){
                        distanceMatix[i][j]= -1;
                        wegmatrix[i][j] = 0;
                    }
                    else {
                        wegmatrix[i][j] = 1;
                    }

                }
                else {
                    wegmatrix[i][j] = 1;
                }
            }
        }
    }

    public void ermittle() {
        int anzMultipliziert = 0;
        while (anzMultipliziert < size) {
            Integer[][] multiply = multiply();
            anzMultipliziert++;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distanceMatix[i][j] < 0 && multiply[i][j] > 0) {
                        distanceMatix[i][j] = anzMultipliziert + 1;
                    } else if (wegmatrix[i][j] == 0 && multiply[i][j] > 0) {
                        wegmatrix[i][j] = 1;
                    }
                }
            }
        }
    }

    public Integer[][] multiply() {
        Integer multiply[][] = new Integer[size][size];
        int sum = 0;
        for (int row = 0; row < size ; row++) {
            for (int col = 0; col < size ; col++) {
                sum =0;
                for (int index = 0; index < size; index++) {
                        sum = sum + matrixA[row][index] * AdjacencyMatrix2[index][col];
                }
                multiply[row][col] = sum;
            }
        }
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA.length; j++){
                matrixA[i][j] = multiply[i][j];
            }
        }

        return multiply;
    }
    public void PrintDistanceMatrix()
    {
        System.out.println("\n\n------------ Print DistanceMatrix---------------------\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(distanceMatix[i][j] +" ");
            }
            System.out.println();
        }
    }

    public Integer[][] GetDistanceMatrix()
    {
        Integer[][] n = new Integer[0][0];
        System.out.println("\n\n------------ Print DistanceMatrix---------------------\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                n = new Integer[size][size];
              n[i][j] = distanceMatix[i][j] ;
            }
        }
        return  n; 
    }
    public void exzentrizitaet(){
        int max = 0;
        String info = "{ ";
        for (int i = 0; i < distanceMatix.length; i++){
            for (int j = 0; j < distanceMatix.length; j++){
                if (max < distanceMatix[i][j]){
                    max = distanceMatix[i][j];
                }
            }
            exzentrizitaet[i] = max;
            max = 0;
        }
        System.out.println("\n------------ Print Exzentrizitaet---------------------\n");

        for(int i =0; i < exzentrizitaet.length; i++ )
        {
            info += exzentrizitaet[i] + " ";

        }
        info = info+ "} \n";
        System.out.print(info);

    }

    public void radiusUndDurchmesser(){
        this.radius = 999;
        this.durchmesser =0;
        for (int i = 0; i < exzentrizitaet.length; i++){
            if (exzentrizitaet[i] < radius && exzentrizitaet[i] > -1 ){
                radius = exzentrizitaet[i];
            }
            if (exzentrizitaet[i] > durchmesser){
                this.durchmesser = exzentrizitaet[i];
            }
        }
        System.out.println("\n------------ Print Radius ---------------------\n");
        System.out.println("Radius: " +radius);
        System.out.println("\n------------ Print Durchmesser ---------------------\n");
        System.out.println("Durchmesser: " +durchmesser);
    }
    public String zentrum(){
        String info;
        int zentrum[];
        zentrum = new int[exzentrizitaet.length];
        for (int i = 0; i < exzentrizitaet.length; i++){
            if (exzentrizitaet[i] == this.radius){
                zentrum[i] = i+1;
            }
        }
        info = "{";
        for (int i = 0; i < zentrum.length; i++){
            if (zentrum[i] != 0){
                info = info + " " + zentrum[i];
            }
        }
        info = info + " " + " }";

        System.out.println("\n------------ Print Zentrum ---------------------\n");
        System.out.println(info);
        return info;
    }


    public boolean isZusammenhaengend(){
        int count = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (wegmatrix[i][j]==1){
                    count++;
                }
            }
        }
        if (count == size*size){
            return true;
        }
        return false;
    }
    public int komponentenanzahl(){
        int vergleichsArray[] = new int [size];
        int pos[]= new int [size];
        int count = 0;
        int index = 0;
        //Position ermitteln
        while (index < size){
            //Zeile in vergleichsArray speichern
            for (int j = 0; j < size; j++){
                vergleichsArray[j] = wegmatrix[index][j];
            }
            //Position speichern
            for (int i = 0; i < size; i++){
                count = 0;
                for (int j = 0; j < size; j++){
                    if (wegmatrix[i][j] == vergleichsArray[j]){
                        count++;
                        if (count == size){
                            pos[index] = i+1;
                        }
                    }
                }
            }
            index++;
        }
        //Elemente in pos in setString speichern und dadurch "Duplikate entfernen"
        Set<Integer> setString = new LinkedHashSet<Integer>();
        for(int i=0;i<pos.length;i++){
            setString.add(pos[i]);
        }
        //Für Komponentenbeschreibung //Elemente in setString in posUnique speichern
        int ind = 0;
        for (Iterator<Integer> it = setString.iterator(); it.hasNext(); ) {
            posUnique[ind] = it.next();
            ind++;
        }
        //Komponentenanzahl zurückgeben
        return setString.size();
    }

    public String komponenteAusgeben(){
        String infKompo="";
        int komponent = 1;
        for (int i = 0; i < komponentenanzahl(); i++){
            infKompo+= "K"+komponent+" { ";
            for (int a = 0; a < wegmatrix.length; a++){
                for (int b = 0; b < wegmatrix.length; b++){
                    if (posUnique[i]-1 == a){
                        if (wegmatrix[a][b]==1){
                            infKompo += b+1 + " ";
                        }
                    }
                }
            }
            infKompo += "}\n";
            komponent++;
        }
        return infKompo;
    }

    public String bruecken(){
        String str = "{";
        int anzBruecken = 0;
        int anzKompVorher = komponentenanzahl();
        int anzKompNachher = 0;

        for (int i = 0; i < size-1; i++){
            for (int j = i; j < size; j++){
                initialize(); //voriges wegmatrix neu initialisieren
                if (AdjacencyMatrix2[i][j]==1){
                    AdjacencyMatrix2[i][j]=0;
                    AdjacencyMatrix2[j][i]=0;
                    initialize();
                    ermittle();
                    anzKompNachher = komponentenanzahl();
                    AdjacencyMatrix2[i][j]=1;
                    AdjacencyMatrix2[j][i]=1;
                    if (anzKompVorher < anzKompNachher){
                        str += "[" + (i+1) + "," + (j+1) + "]";
                        anzBruecken++;
                    }
                }
            }
        }
        str += "}";
        initialize();
        ermittle();
        return str + "Anzahl :" + anzBruecken;
    }

    public String artikulation(){
        Integer[][] adjaKopie = new Integer[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                adjaKopie[i][j]= AdjacencyMatrix2[i][j];
            }
        }
        String str = "";
        str += "{";
        initialize();
        ermittle();
        int anzKompVorher = komponentenanzahl();
        int anzKompNachher = 0;
        for (int i = 0; i < size; i++){
            initialize();
            for (int j = 0; j < size; j++){
                AdjacencyMatrix2[i][j]=0;
                AdjacencyMatrix2[j][i]=0;
            }
            initialize();
            ermittle();
            anzKompNachher = komponentenanzahl();
            if (anzKompVorher+1 < anzKompNachher){
                str += " [" + (i+1) + "] ";
            }
            for (int a = 0; a < size; a++){
                for (int b = 0; b < size; b++){
                    AdjacencyMatrix2[a][b] =adjaKopie[a][b];
                }
            }
        }
        str += "}";
        System.arraycopy(adjaKopie ,0,AdjacencyMatrix2,0,size);
        initialize();
        ermittle();
        return str;
    }

}


