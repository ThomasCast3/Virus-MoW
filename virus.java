package com.company;

// import java.util.Scanner;
import java.util.*;

public class virus {

    public static void main(String[] args){
        virus(Collections.singletonList(new int[]{2, 3}),3);
    }


    public static List<Integer> virus(List<int[]> patients0, int n) {

        //tableau d'objet (monde qui contient chaque patient) de taille n*n sachant que n>0
        Patient [][] monde;           //tableau d'objet
        monde = new Patient[n][n];    //instence du tableau avec une taille n par n

        int countInfected = patients0.size() ;
        ArrayList <Patient> infectedList = new ArrayList<Patient>() ;

        virus(patients0, n).add(countInfected);

        for (int x = 0; x<n; x++){
            for (int y = 0; y<n; y++){   //pour chaque patient du monde (du tableau)
             monde[x][y].setPosX(x);    // attribuer la position x dans le monde au patient correspondant
             monde[x][y].setPosY(y);    // attribuer la position y dans le monde au patient correspondant
            }
        }

        for(int i=0; i<patients0.size();i++){  //pour tout les patients infecter au depart patients0
            var infected = patients0.get(i) ;   //recuperer chaque position des infectées en fonction de i
            for (int j=0; j<infected.length;j++){
                int countCase=0;   //compter le numero de case pour
                for (int x = 0; x<n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (infected[j] == countCase) { //comparer avec la valeur de patients0
                            monde[x][y].setInfected(true);  //si le numero de case correspond alors infecter
                        }
                    countCase += 1;  //implementer a chaque fois qu'on change de case
                    }
                }
            }
                    //    monde[posX][posY].setInfected(true); //rendre leur objet patient infecté
         //   infectedList.add(monde[posX][posY]); //et les ajouter a la list d'infecter
        }

        //boucle tant que tout le monde n'est pas infecter  (pas de temps)
        while(infectedList.size() != (n*n)) {

            //pour chaque patient infecté
            for (int i = 0; i<infectedList.size(); i++) {
                var x = infectedList.get(i).getPosX();
                var y = infectedList.get(i).getPosY();
                var listVoisins = monde[x][y].getVoisins(n); //recuperer ses voisins proche
                var voisinDroit = listVoisins.get(1);
                var voisinGauche = listVoisins.get(2);
                var voisinBas = listVoisins.get(3);
                var voisinHaut = listVoisins.get(4);

                if (voisinDroit==true){  //si voisin droit existe
                    monde[x+1][y].setInfected(true); //alors l'infecter
                }
                if (voisinGauche==true){  //si voisin gauche existe
                    monde[x-1][y].setInfected(true); //alors l'infecter
                }
                if (voisinBas==true){  //si voisin bas existe
                    monde[x][y+1].setInfected(true); //alors l'infecter
                }
                if (voisinHaut==true){  //si voisin haut existe
                    monde[x][y-1].setInfected(true); //alors l'infecter
                }
            }

            for (int x = 0; x<n; x++) {
                for (int y = 0; y < n; y++) {
                    var patient1 = monde[x][y];  //pour tous les patients
                    boolean inList = false;
                    if (patient1.isInfected()){ //si il est infecté
                        for (int i = 0; i<infectedList.size(); i++){  //verifier si il est dans la list d'infecter
                            var patient2 = infectedList.get(i);
                            if (patient1.getPosX()==patient2.getPosX() && patient1.getPosY() == patient2.getPosY()){
                            inList=true;
                            }
                        }
                        if (inList==false){  //si non l'ajouter a la list
                            infectedList.add(patient1);
                            countInfected += 1;
                        }
                    }
                }
            }
                    //ajouter le nb d'infecter total a la liste virus
            virus(patients0, n).add(countInfected);
        }
        return virus(patients0, n); //return list d'infecter a un index precis

    }


}


// creer une classe patient
class Patient{

    //  contiens la position de la personne
   private int posX;
   private int posY;
    //si elle est infecté ou non
   private boolean isInfected = false;


    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }
    public boolean isInfected() {
        return isInfected;
    }

    //method recuperer ces voisin direct  list de tableau2 pour les positions des voisins

    public ArrayList<Boolean> getVoisins(int n){
        //en fonction de x recuperer les voisins directs
        if(getPosX()==0){
        boolean voisinDroit = true;
        boolean voisinGauche =false;
            getVoisins(n).add(voisinDroit);
            getVoisins(n).add(voisinGauche);
        }else if(getPosX()==n){
            boolean voisinDroit = false;
            boolean voisinGauche = true;
            getVoisins(n).add(voisinDroit);
            getVoisins(n).add(voisinGauche);
        }else {
            boolean voisinDroit = voisinDroit = true;
            boolean voisinGauche = voisinGauche = true;
            getVoisins(n).add(voisinDroit);
            getVoisins(n).add(voisinGauche);
        }

        //en fonction de y recuperer les voisins directs
        if(getPosY()==0 && getPosY()<n){
            boolean voisinBas = true;
            boolean voisinHaut = false;
            getVoisins(n).add(voisinBas);
            getVoisins(n).add(voisinHaut);
        }else if(getPosX()>0 && getPosX()==n){
            boolean voisinBas = false;
            boolean voisinHaut = true;
            getVoisins(n).add(voisinBas);
            getVoisins(n).add(voisinHaut);
        }else {
            boolean voisinBas = true;
            getVoisins(n).add(voisinBas);
            boolean voisinHaut = false;
            getVoisins(n).add(voisinHaut);
        }
        return getVoisins(n);
    }
}


