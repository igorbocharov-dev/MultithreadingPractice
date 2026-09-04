package Task7;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int parties = 4;
        final int [] sumsParties = new int[parties];
        CyclicBarrier cyclicBarrier = new CyclicBarrier
                (parties, () -> System.out.println("Итоговая сумма: " + Arrays.stream(sumsParties).sum()));

        int[] array1 = new int[100];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = i;
        }

        int[] array2 = new int[101];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = i;
        }

        DivideArray divideArray = new DivideArray();
        divideArray.process(array1, parties, cyclicBarrier, sumsParties);

        Arrays.fill(sumsParties, 0);

        divideArray.process(array2, parties, cyclicBarrier, sumsParties);
    }
}
