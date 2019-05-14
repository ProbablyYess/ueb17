import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaExercise {

    private final Myfunction facc = i -> i == 0 ? 1 : i * this.facc.apply(i - 1);
    private final Myfunction fib = i -> i < 2 ? 1 : this.fib.apply(i - 1) + this.fib.apply(i - 2);

    static class FuckStaticClass implements Myfunction{

        @Override
        public int apply(int i) {
            return i < 2 ? 1 : apply(i - 1) + apply(i - 2);
        }
    }

    public interface FunctionalInterface extends LambdaExercise.Myfunction {
        default UnaryOperator<Integer> conditionateInput(Predicate<Integer> predicate) {
            return i -> predicate.test(i) ? this.apply(i) : 0;
        }

        default UnaryOperator<Integer> conditionateOutput(Predicate<Integer> predicate) {
            return i -> {
                int res = this.apply(i);
                return predicate.test(i) ? res : 0;
            };
        }

    }

    public void run() {
        applyAndPrint(0, 5, new Myfunction() {
            @Override
            public int apply(int i) {
                return i * i;
            }
        });



        applyAndPrint(0, 5, i -> i * i);

        applyAndPrint(0, 5, new Myfunction() {
            @Override
            public int apply(int i) {
                return i == 0 ? 1 : i * apply(i - 1);
            }
        });




        applyAndPrint(0, 5, facc);

        applyAndPrint(0,5,new FaccClass());

        //applyAndPrint(0,5,  FuckStaticClass);

        applyAndPrint(0, 5, new Myfunction() {
            @Override
            public int apply(int i) {
                return (int) Math.pow(i, i + 1);
            }
        });



        applyAndPrint(0,5, i -> (int) Math.pow(i, i+1));



        applyAndPrint(0, 5, new Myfunction() {
            @Override
            public int apply(int i) {
                return i < 2 ? 1 : apply(i - 1) + apply(i - 2);
            }
        });


        applyAndPrint(0, 5, fib);

        Predicate<Integer> even = i -> i % 2 == 0;
        Predicate<Integer> odd = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 1;
            }
        };

    }


    public interface Myfunction {
        int apply(int i);
    }

    public static void applyAndPrint(int i, int j, Myfunction fun) {
        for (int x = i; x <= j; x++) {
            System.out.println(fun.apply(x));
        }
    }
}
