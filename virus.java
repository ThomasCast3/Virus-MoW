package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // appele de la fonction virus :
        // positions du/des patients0
        int[] test = { 1, 2 };
        int[] test1 = { 0, 2 };

        List patients0 = new ArrayList(); // declarer la liste des patients0

        // ajouter les positions à la liste
        patients0.add(test1);
        patients0.add(test);

        int n = 3; // declarer taille du tableau

        System.out.println(virus(patients0, n)); // appele fonction et print console pour afficher resultat

    }

    public static List<Integer> virus(List<int[]> patients0, int n) {

        // variables :
        List<Integer> listvirus = new ArrayList<>(); // list du nombres d'infecté a chaque pas de temps
        int countInfected = 0; // compteur d'infecté
        List<Patient> infectedList = new ArrayList<>(); // patient infecté
        Patient[][] monde = new Patient[n][n]; // declarer monde de taille n sur n

        for (int posX = 0; posX < n; posX++) { // pour chaque colonnes du tableau
            for (int posY = 0; posY < n; posY++) { // pour chaque lignes du tableau
                monde[posX][posY] = new Patient(); // initialiser la case du monde en lui attribuant un obj patient
                monde[posX][posY].setPosX(posX); // set la valeur de x du patient dans le monde
                monde[posX][posY].setPosY(posY); // set la valeur de y du patient dans le monde
            }
        }

        for (int i = 0; i < patients0.size(); i++) { // pour tout les patients infecter au depart (patients0)
            var infected = patients0.get(i); // recuperer les positions de l'infecté a l'index i
            int posX = infected[0]; // declarer la position x pour la premiere valeur
            int posY = infected[1]; // puis celle de y pour la seconde valeur
            monde[posX][posY].setInfected(true); // set la valeur infecté de l'obj patient a vrai
            infectedList.add(monde[posX][posY]); // et ajouter l'obj patient à la list des infectés
        }
        countInfected += patients0.size(); // compter du nombre d'infectés total
        listvirus.add(countInfected); // ajout du compte de patient initialement infecté

        while (countInfected < (n * n)) { // tant que tout le monde n'est pas infecté
            List<Patient> nonInfected = new ArrayList<>(); // patient à infecter
            for (int i = 0; i < infectedList.size(); i++) { // pour chaque patient deja infecté
                Patient infected = infectedList.get(i);
                int posX = infected.getPosX(); // recuperer sa position x et y
                int posY = infected.getPosY();
                List voisin = infected.getVoisins(n); // puis recuperer ses voisins direct
                boolean voisinD = (boolean) voisin.get(0);
                boolean voisinG = (boolean) voisin.get(1);
                boolean voisinB = (boolean) voisin.get(2);
                boolean voisinH = (boolean) voisin.get(3);
                if (voisinD == true) { // si voisin droit existe
                    Patient voisinDroite = monde[(posX + 1)][posY];
                    if (!voisinDroite.isInfected) { // si il est pas infecté
                        voisinDroite.setInfected(true); // alors l'infecter
                        nonInfected.add(voisinDroite); // l'ajouter à la list de patient à infecter
                    }
                }
                if (voisinG == true) { // si voisin gauche existe
                    Patient voisinGauche = monde[(posX - 1)][posY];
                    if (!voisinGauche.isInfected) { // si il est pas infecté
                        voisinGauche.setInfected(true);// alors l'infecter
                        nonInfected.add(voisinGauche);// l'ajouter à la list de patient à infecter
                    }
                }
                if (voisinB == true) { // si voisin bas existe
                    Patient voisinBas = monde[posX][(posY + 1)];
                    if (!voisinBas.isInfected) { // si il est pas infecté
                        voisinBas.setInfected(true);// alors l'infecter
                        nonInfected.add(voisinBas);// l'ajouter à la list de patient à infecter
                    }
                }
                if (voisinH == true) { // si voisin haut existe
                    Patient voisinHaut = monde[posX][(posY - 1)];
                    if (!voisinHaut.isInfected) { // si il est pas infecté
                        voisinHaut.setInfected(true);// alors l'infecter
                        nonInfected.add(voisinHaut);// l'ajouter à la list de patient à infecter
                    }
                }
            }
            for (int j = 0; j < nonInfected.size(); j++) { // pour tout les patients à infecter
                infectedList.add(nonInfected.get(j)); // les ajouter à la list des patients infecté
                countInfected += 1; // incrementer le compte d'infecter
            }
            listvirus.add(countInfected); // ajouter le nouveau compte d'infecter apres un nouveau pas de temps
        }
        return listvirus; // retourner la list du nb d'infecté a chaque pas de temps
    }

    static class Patient {
        // contiens la position du patient
        private int posX;
        private int posY;
        // si elle est infecté ou non (initialement a faux)
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

        public List<Boolean> getVoisins(int n) { // fonction pour connaitre les voisins direct du patient
            List<Boolean> voisins = new ArrayList<>(); // liste de chaque voisin direct
            // en fonction de x recuperer les voisins directs droite puis gauche
            if (getPosX() == 0) { // si x est sur la colonne la plus a gauche
                boolean voisinDroit = true; // il a un voisin droit
                boolean voisinGauche = false; // et pas de voisin gauche
                voisins.add(voisinDroit); // ajouter les valeurs à la list
                voisins.add(voisinGauche);
            } else if (getPosX() == n - 1) { // si x est sur la colonne la plus a droite
                boolean voisinDroit = false; // pas de voisin droit
                boolean voisinGauche = true; // et un voisin gauche
                voisins.add(voisinDroit);
                voisins.add(voisinGauche);
            } else {
                boolean voisinDroit = true; // sinon 2 voisin : gauche et droit
                boolean voisinGauche = true;
                voisins.add(voisinDroit);
                voisins.add(voisinGauche);
            }

            // en fonction de y recuperer les voisins directs bas puis haut
            if (getPosY() == 0) {
                boolean voisinBas = true;
                boolean voisinHaut = false;
                voisins.add(voisinBas);
                voisins.add(voisinHaut);
            } else if (getPosY() == n - 1) {
                boolean voisinBas = false;
                boolean voisinHaut = true;
                voisins.add(voisinBas);
                voisins.add(voisinHaut);
            } else {
                boolean voisinBas = true;
                boolean voisinHaut = true;
                voisins.add(voisinBas);
                voisins.add(voisinHaut);
            }
            return voisins; // retourner une list a 4 valeur(droite, gauche, bas, haut)
        }
    }
}
