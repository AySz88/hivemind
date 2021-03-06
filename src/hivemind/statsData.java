package hivemind;

import java.util.ArrayList;

public class statsData implements java.io.Serializable {

    double len;
    double sum;
    double mean;
    double std_dev_p;
    double std_dev_s;

    double kappa_c;
    double kappa_g;

    double kappaN_c;
    double kappaN_g;

    double deltaN_sum;
    double deltaN_mean;
    double deltaN_std_p;
    double deltaN_std_s;

    public void init(double[] d) {


        if (d.length < 2) {
            return;
        }
        double last;
        sum = 0;
        len = d.length;

        last = d[0];
        sum += last;
        double tmp = 0;
        double[] deltaN = new double[(int) len - 1];
        for (int i = 1; i < d.length; i++) {
            deltaN[i - 1] = Math.abs(d[i] - last);
            deltaN_sum += deltaN[i - 1];

            last = d[i];
            sum += last;
        }

        mean = sum / len;
        deltaN_mean = deltaN_sum / (len - 1);

        tmp = 0;
        double k = 0;
        double kN = 0;
        double tmpN = 0;
        tmp = d[0] - mean;
        tmp *= tmp;
        k += tmp;

        for (int i = 1; i < d.length; i++) {
            tmpN = (deltaN[i - 1] - deltaN_mean);

            tmpN *= tmpN;
            if (tmpN < 2) {
                if (tmpN < 1) {
                    kappaN_g++;
                } else {
                    kappaN_c++;
                }
            }

            kN += tmpN;

            tmp = d[i] - mean;

            tmp *= tmp;
            if (tmpN < 2) {
                if (tmpN < 1) {
                    kappa_g++;
                } else {
                    kappa_c++;
                }
            }
            k += tmp;
        }

        deltaN_std_p = kN / (len - 1);
        deltaN_std_p = Math.sqrt(deltaN_std_p);
        deltaN_std_s = kN / (len - 2);
        deltaN_std_s = Math.sqrt(deltaN_std_s);

        std_dev_p = k / (len);
        std_dev_p = Math.sqrt(std_dev_p);

        std_dev_s = k / (len - 1);
        std_dev_s = Math.sqrt(std_dev_s);

    }

    public statsData() {

    }

    public statsData(ArrayList<Double> d, int a, int b) {
        this();
        double[] in = new double[b - a + 1];
        int p = 0;
        for (int i = a; i <= b; i++) {
            in[p] = d.get(i);
            p++;
        }
        init(in);
    }

    public statsData(double[] d, int a, int b) {
        this();
                double[] in = new double[b - a + 1];
        int p = 0;
        for (int i = a; i <= b; i++) {
            in[p] = d[i];
            p++;
        }
        init(in);
    }

    public statsData(int[] d, int a, int b) {
        this();
               double[] in = new double[b - a + 1];
        int p = 0;
        for (int i = a; i <= b; i++) {
            in[p] = d[i];
            p++;
        }
        init(in);
    }

    public statsData(long[] d, int a, int b) {
        this();
                double[] in = new double[b - a + 1];
        int p = 0;
        for (int i = a; i <= b; i++) {
            in[p] = d[i];
            p++;
        }
        init(in);

    }

    public statsData(Double[] d, int a, int b) {
        this();
                double[] in = new double[b - a + 1];
        int p = 0;
        for (int i = a; i <= b; i++) {
            in[p] = d[i];
            p++;
        }
        init(in);

    }

}
