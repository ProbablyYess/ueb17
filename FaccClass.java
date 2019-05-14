public class FaccClass implements LambdaExercise.Myfunction {

    @Override
    public int apply(int i) {
        return i < 2 ? 1 : apply(i - 1) + apply(i - 2);
    }
}
