public class Statistics {
        public static float mean(float[] w) {
            return divide(sum(w), w.length);
        }

        private static float sum(float[] s) {
                float f = 0;
                for (int i = 0; i < s.length; i++) f += s[i];
                return f;
        }

        private static float divide(float a, float b) {
                if (b == 0) throw new RuntimeException("Division by zero");
                return a / b;
        }

        public static double median(double[] m) {
            int middle = m.length/2;  // subscript of middle element
            if (m.length%2 == 1) {
                // Odd number of elements -- return the middle one.
                return m[middle];
            } else {
               // Even number -- return average of middle two
               // Must cast the numbers to double before dividing.
               return (m[middle-1] + m[middle]) / 2.0;
            }
        }//end method median

}
