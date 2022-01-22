public class Activite {
    /**
     * Identifiant de l'activité (numéro)
     */
    private int id;
    /**
     * Délai en millisecondes entre chaque affichage
     */
    private int delai;
    /**
     * Nombre d'affichages
     */
    private int nbIterations;
    /**
     * Chaîne de caractères représentant l'activité (pour les traces)
     */
    private String signature;


    Activite(int id, int delai, int nbIterations) {
        this.id = id;
        this.delai = delai;
        this.nbIterations = nbIterations;
        this.signature = " ".repeat(id) + "x";
    }

    /**
     * Méthode principale de l'activité
     * Cette méthode affiche `nbIterations` fois la signature de l'activité, avec une pause de `delai` millisecondes
     * entre chaque affichage, puis elle affiche "Fin de l'activité T{id}"
     */
    public void executer() {
        for (int i = 1; i < nbIterations; i++) {
            System.out.printf("T%d\t%s\n", id, signature);
            try {
                Thread.sleep(delai);
            } catch (InterruptedException e) {}
        }
        System.out.printf("Fin de l'activite T%d\n", id);
    }
}
