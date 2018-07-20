package com.mick.example.vips.models;

import static java.lang.Math.exp;

public class model1 {
    static double Logistic(double x) {
        return (1 / (1 + exp(-x)));
    }

    static double[] Softmax(double x1, double x2, double x3, double x4) {
        double inputs[] = {x1, x2, x3, x4};
        double softmax[] = {0, 0, 0, 0};
        double sum = 0;

        for (int i = 0; i < 4; i++) {
            sum += exp(inputs[i]);
        }

        for (int i = 0; i < 4; i++) {
            softmax[i] = exp(inputs[i]) / sum;
        }
        return softmax;
    }

    public double[] expression(double variable_1, double variable_2, double variable_3, double variable_4, double variable_5, double variable_6, double variable_7, double variable_8, double variable_9, double variable_10, double variable_11, double variable_12, double variable_13, double variable_14, double variable_15, double variable_16, double variable_17, double variable_18, double variable_19, double variable_20, double variable_21, double variable_22, double variable_23, double variable_24) {


        double scaled_variable_6 = 2 * (variable_6 + 4.12369) / (5.05174 + 4.12369) - 1;
        double scaled_variable_11 = 2 * (variable_11 + 12.9572) / (2.4007 + 12.9572) - 1;
        double scaled_variable_17 = 2 * (variable_17 + 90.0899) / (36.499 + 90.0899) - 1;
        double scaled_variable_19 = 2 * (variable_19 - 0.0123178) / (10.9522 - 0.0123178) - 1;
        double scaled_variable_20 = 2 * (variable_20 - 0.0139729) / (13.252 - 0.0139729) - 1;
        double scaled_variable_23 = 2 * (variable_23 - 0.00214051) / (4.72643 - 0.00214051) - 1;
        double scaled_variable_24 = 2 * (variable_24 - 0.00486575) / (2.58357 - 0.00486575) - 1;
        double y_1_1 = Logistic(2.18886
                + 7.42963 * scaled_variable_6
                - 7.26079 * scaled_variable_11
                + 1.3144 * scaled_variable_17
                - 3.68156 * scaled_variable_19
                + 2.6136 * scaled_variable_20
                - 0.960396 * scaled_variable_23
                + 4.80633 * scaled_variable_24);
        double y_1_2 = Logistic(-7.39037
                + 3.16596 * scaled_variable_6
                + 9.36756 * scaled_variable_11
                + 8.18201 * scaled_variable_17
                - 8.00009 * scaled_variable_19
                - 3.81563 * scaled_variable_20
                + 13.955 * scaled_variable_23
                + 0.319939 * scaled_variable_24);
        double y_1_3 = Logistic(-6.85614
                + 2.40033 * scaled_variable_6
                + 5.50829 * scaled_variable_11
                + 1.78455 * scaled_variable_17
                - 8.01093 * scaled_variable_19
                + 6.98504 * scaled_variable_20
                - 5.11042 * scaled_variable_23
                + 5.60481 * scaled_variable_24);
        double y_1_4 = Logistic(7.19524
                - 14.1044 * scaled_variable_6
                - 2.32102 * scaled_variable_11
                + 0.0912365 * scaled_variable_17
                + 4.64092 * scaled_variable_19
                + 5.43838 * scaled_variable_20
                - 3.94189 * scaled_variable_23
                + 7.09385 * scaled_variable_24);
        double y_1_5 = Logistic(-21.1168
                + 4.75747 * scaled_variable_6
                - 1.08658 * scaled_variable_11
                + 0.322986 * scaled_variable_17
                - 11.8629 * scaled_variable_19
                + 0.422067 * scaled_variable_20
                - 7.14782 * scaled_variable_23
                - 5.92564 * scaled_variable_24);
        double y_1_6 = Logistic(0.902942
                + 1.76546 * scaled_variable_6
                - 2.41714 * scaled_variable_11
                - 1.12607 * scaled_variable_17
                + 0.0528571 * scaled_variable_19
                + 0.627012 * scaled_variable_20
                + 0.30747 * scaled_variable_23
                - 2.8027 * scaled_variable_24);
        double y_1_7 = Logistic(6.07308
                - 6.89347 * scaled_variable_6
                + 3.83844 * scaled_variable_11
                - 15.6524 * scaled_variable_17
                + 0.635183 * scaled_variable_19
                + 5.43621 * scaled_variable_20
                - 5.12517 * scaled_variable_23
                + 1.33372 * scaled_variable_24);
        double non_probabilistic_variable_25 = Logistic(-6.09415
                - 0.881348 * y_1_1
                - 0.623724 * y_1_2
                - 4.9566 * y_1_3
                - 1.44728 * y_1_4
                + 19.1811 * y_1_5
                - 4.30891 * y_1_6
                + 0.739336 * y_1_7);
        double non_probabilistic_variable_26 = Logistic(-4.40116
                - 4.93146 * y_1_1
                - 6.92946 * y_1_2
                + 19.3918 * y_1_3
                + 17.4285 * y_1_4
                - 2.59066 * y_1_5
                - 3.38916 * y_1_6
                + 0.701866 * y_1_7);
        double non_probabilistic_variable_27 = Logistic(3.9587
                + 12.7734 * y_1_1
                - 14.931 * y_1_2
                - 1.58947 * y_1_3
                - 9.57851 * y_1_4
                - 0.508896 * y_1_5
                + 4.27347 * y_1_6
                - 12.9647 * y_1_7);
        double non_probabilistic_variable_28 = Logistic(-2.59033
                - 13.9777 * y_1_1
                + 14.1875 * y_1_2
                - 1.01882 * y_1_3
                - 13.1056 * y_1_4
                - 20.4128 * y_1_5
                - 4.35121 * y_1_6
                + 12.0074 * y_1_7);

        return Softmax(non_probabilistic_variable_25, non_probabilistic_variable_26, non_probabilistic_variable_27, non_probabilistic_variable_28);
    }
}
