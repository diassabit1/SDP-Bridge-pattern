public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Use: java -cp out Main --demo");
        }
    }

    private static void runDemo() {
        Renderer vectorRenderer = new VectorRenderer();
        Renderer rasterRenderer = new RasterRenderer();

        int passed = 0;

        String t1 = runT1(vectorRenderer);
        System.out.println(t1);
        if (t1.startsWith("T1 PASS")) {
            passed++;
        }

        String t2 = runT2(rasterRenderer);
        System.out.println(t2);
        if (t2.startsWith("T2 PASS")) {
            passed++;
        }

        String t3 = runT3(vectorRenderer);
        System.out.println(t3);
        if (t3.startsWith("T3 PASS")) {
            passed++;
        }

        String t4 = runT4(rasterRenderer);
        System.out.println(t4);
        if (t4.startsWith("T4 PASS")) {
            passed++;
        }

        String t5 = runT5(vectorRenderer, rasterRenderer);
        System.out.println(t5);
        if (t5.startsWith("T5 PASS")) {
            passed++;
        }

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    private static String runT1(Renderer renderer) {
        Circle circle = new Circle(1, 2, renderer);
        String actual = circle.execute();
        String expected = "VECTOR circle radius=2";

        if (actual.equals(expected)) {
            return "T1 PASS | Circle + VectorRenderer | result=" + actual;
        }

        return "T1 FAIL | Circle + VectorRenderer | result=" + actual
                + " | expected=" + expected;
    }

    private static String runT2(Renderer renderer) {
        Circle circle = new Circle(1, 2, renderer);
        String actual = circle.execute();
        String expected = "RASTER circle radius=2";

        if (actual.equals(expected)) {
            return "T2 PASS | Circle + RasterRenderer | result=" + actual;
        }

        return "T2 FAIL | Circle + RasterRenderer | result=" + actual
                + " | expected=" + expected;
    }

    private static String runT3(Renderer renderer) {
        Square square = new Square(2, 3, renderer);
        String actual = square.execute();
        String expected = "VECTOR square side=3";

        if (actual.equals(expected)) {
            return "T3 PASS | Square + VectorRenderer | result=" + actual;
        }

        return "T3 FAIL | Square + VectorRenderer | result=" + actual
                + " | expected=" + expected;
    }

    private static String runT4(Renderer renderer) {
        Square square = new Square(2, 3, renderer);
        String actual = square.execute();
        String expected = "RASTER square side=3";

        if (actual.equals(expected)) {
            return "T4 PASS | Square + RasterRenderer | result=" + actual;
        }

        return "T4 FAIL | Square + RasterRenderer | result=" + actual
                + " | expected=" + expected;
    }

    private static String runT5(Renderer firstRenderer, Renderer secondRenderer) {
        Circle circle = new Circle(10, 2, firstRenderer);

        Shape originalReference = circle;

        String before = circle.execute();
        int idBefore = circle.getId();
        int radiusBefore = circle.getRadius();

        circle.setImplementation(secondRenderer);

        Shape afterReference = circle;

        String after = circle.execute();
        int idAfter = circle.getId();
        int radiusAfter = circle.getRadius();

        boolean sameObject = originalReference == afterReference;
        boolean stateUnchanged = idBefore == idAfter && radiusBefore == radiusAfter;
        boolean correctResults = before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2");

        if (sameObject && stateUnchanged && correctResults) {
            return "T5 PASS | sameObject=true | stateUnchanged=true"
                    + " | before=" + before + " | after=" + after;
        }

        return "T5 FAIL | sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged
                + " | before=" + before
                + " | after=" + after;
    }
}