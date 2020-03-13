package player.human;

import configuration.GameConfig;
import player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {
    private String codeHu;
    private String code;

    public HumanPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }


    @Override
    public String askConbinaison() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int length = GameConfig.build().getSizeCombi();
            System.out.println("Vous devez entrer un nombre contenant " + length + " chiffres.");
            String prop = sc.nextLine();
            if (prop.length() == getSizeCombi() && prop.matches("\\d{0," + length + "}")) {
                this.codeHu = prop;
                return prop;
            } else {
                System.out.println("--------------------------------------------------------------------" +
                        "\nErreur de contenu. Veuillez effectuer une nouvelle saisie." +
                        "\n--------------------------------------------------------------------");
            }
        }
    }


    @Override
    public String feedback(String proposition, List<String> userFeedback, String code) {
        Scanner sc = new Scanner(System.in);
        List<Character> userFeedbacks = new ArrayList<>();
        String test = userFeedback.get(0);
        for (int i = 0; i < getSizeCombi(); i++) {
            userFeedbacks.add(i, test.charAt(i));
        }
        boolean c = true;
        do {
            System.out.println("Voici ce qu'à trouvé la machine comme proposition pour votre code " + proposition + " (pour rappel votre code est : " + code + " )."
                    + "\nQu'en pensez-vous ?" +
                    "\n----------------------------------------------------------------------------" +
                    "\nSi la valeur donnée est plus importante que celle du code, répondez par +" +
                    "\nSi elle est inférieure, répondez par -" +
                    "\nSi enfin elle est égale, répondez par =" +
                    "\n----------------------------------------------------------------------------");
            String retour = sc.nextLine();
            if ((retour.length() == getSizeCombi()) && !(retour.matches("^+-="))) {
                for (int i = 0; i < getSizeCombi(); i++) {
                    String conditionA = Character.toString(userFeedbacks.get(i));
                    String conditionB = Character.toString(retour.charAt(i));
                    if (conditionA.equals(conditionB)) {
                        c = false;
                    } else {
                        c = true;
                        System.out.println("\n--------------------------------------------------------------------" +
                                "\nÊtes-vous sûr du résultat ?" +
                                "\n--------------------------------------------------------------------");
                        break;
                    }
                }
            } else {
                System.out.println("\n--------------------------------------------------------------------" +
                        "\nÊtes-vous sûr du résultat ? Veuillez rentrer " + getSizeCombi() + " caractère(s)" +
                        "\n--------------------------------------------------------------------");
            }
        } while (c == true);
        return null;
    }
}
