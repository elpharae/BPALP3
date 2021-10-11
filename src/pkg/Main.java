package pkg;

import java.util.Scanner;

public class Main {

    private boolean bottled;
    private boolean alc;
    private boolean inside;
    private float price;

    private final Scanner sc = new Scanner(System.in);

    /*
    točený, alko/nealko, restaurace - 10%
    lahvový, nealko, restaurace - 10%
    točený, nealko, mimo restauraci - 15%
    točený, alko, mimo restauraci - 21%
    lahvový, alko, restaurace - 21%
    lahvový, alko, mimo restauraci - 21%
     */

    private void getData() {
        System.out.println("Točené/lahvové? y/n: ");
        this.bottled = this.sc.nextLine().charAt(0) == 'y';
        System.out.println("Alko/nealko? y/n: ");
        this.alc = this.sc.nextLine().charAt(0) == 'y';
        System.out.println("V restauraci/mimo restauraci? y/n: ");
        this.inside = this.sc.nextLine().charAt(0) == 'y';
        System.out.println("Cena bez DPH?");
        this.price = this.sc.nextFloat();
    }

    private float[] calcPrice() {
        float[] result = new float[]{this.price, 0};

        if ((!bottled && alc && !inside) || (bottled && alc && inside) || (bottled && alc && !inside)) result[0] += result[0] * 0.21f;
        else if (!bottled && !alc && !inside) result[0] += result[0] * 0.15f;
        else if ((!bottled && inside) || (bottled && !alc && inside)) result[0] += result[0] * 0.1f;

        result[1] = (float) Math.floor((double) (result[0] - this.price) * 100) / 100;

        return result;
    }

    private Main() {
        getData();

        float[] finalPrice = calcPrice();
        System.out.println("Výsledná cena je " + finalPrice[0] + " Kč. Výsledné DPH činí: " + finalPrice[1] + " Kč.");
    }

    public static void main(String[] args) {
	    new Main();
    }
}
